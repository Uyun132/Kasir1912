package com.example.kasir1912;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditMenu extends AppCompatActivity {

    private EditText edName,edHarga,edDesk;
    private Button btEdit;

    private ProgressDialog progressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        edName = findViewById(R.id.edNama);
        edHarga = findViewById(R.id.edHarga);
        edDesk = findViewById(R.id.edDesk);
        btEdit = findViewById(R.id.addMenu);

        progressDialog = new ProgressDialog(EditMenu.this);
        progressDialog.setTitle("Loading");;
        progressDialog.setMessage("Mengambil data .... ");

        btEdit.setOnClickListener(v -> {
            if(edName.getText().length()>0 && edHarga.getText().length()>0){
                //memanggil method save data
                saveData(edName.getText().toString(),edHarga.getText().toString(),edDesk.getText().toString());
            }else {
                Toast.makeText(getApplicationContext(),"Silahkan isi semua data!",Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if(intent!=null) {
            id = intent.getStringExtra("id");
            edName.setText(intent.getStringExtra("name"));
            edHarga.setText(intent.getStringExtra("harga"));
            edDesk.setText(intent.getStringExtra("desk"));
        }
    }

    private void saveData(String nama, String harga,String desk) {
        Map<String,Object> user = new HashMap<>();
        user.put("name",nama);
        user.put("harga",harga);
        user.put("desk",desk);
        progressDialog.show();
        //jika id kosong maka akan edit data
        if(id!=null){
            //kodingan untuk edit data firestore dengan mengambil id
            db.collection("menu").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            //koding untuk menambahkan data dengn .add
            db.collection("menu")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
        }
    }
}