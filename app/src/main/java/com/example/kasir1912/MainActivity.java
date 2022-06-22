package com.example.kasir1912;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.kasir1912.model.Menu;
import com.example.kasir1912.adapter.MenuAdapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton btnAdd;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button Orderan;

    private List<Menu> list = new ArrayList<>();
    private MenuAdapter menuAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ini isinya menu, tapi bisa di CRUD
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_order);
        btnAdd = findViewById(R.id.btn_add);
//        Orderan = findViewById(R.id.btOrder);

//        Orderan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");;
        progressDialog.setMessage("Mengambil data .... ");
        menuAdapter = new MenuAdapter(getApplicationContext(),list);
        menuAdapter.setDialog(new MenuAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            //Melemparkan data ke class selanjutnya
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), EditMenu.class);
                                intent.putExtra("id",list.get(pos).getId());
                                intent.putExtra("name",list.get(pos).getName());
                                intent.putExtra("harga",list.get(pos).getHarga());
                                intent.putExtra("desk",list.get(pos).getDesc());
                                startActivity(intent);
                                break;
                            case 1:
                                //memanggil class delete data
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(menuAdapter);
        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),EditMenu.class));
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnMenu){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

        }if(item.getItemId() == R.id.mnOrder){
            Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();
        db.collection("menu")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task){
                        list.clear();
                        if(task.isSuccessful()){
                            //mengambil data dari collection
                            for (QueryDocumentSnapshot document : task.getResult()){
                                //data apa aja yg mau diambil dari colletcion
                                Menu user = new Menu(document.getString("name"),document.getString("desk"),document.getString("harga"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                            menuAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data Gagal Diambil!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void deleteData(String id) {
        progressDialog.show();
        db.collection("menu").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Gagal Dihapus!",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}