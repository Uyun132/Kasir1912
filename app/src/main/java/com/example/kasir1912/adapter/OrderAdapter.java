package com.example.kasir1912.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kasir1912.model.Order;
import com.example.kasir1912.R;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private Context context;
    private List<Order> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public OrderAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView   = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nProduk.setText(list.get(position).getProduk());
        holder.jumlahProduk.setText(list.get(position).getJumlah());
        holder.hasilJumlah.setText(list.get(position).getTotal());
        holder.gedung.setText(list.get(position).getGedung());
        holder.lantai.setText(list.get(position).getLantai());
        holder.ruangan.setText(list.get(position).getRuangan());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nProduk,jumlahProduk,hasilJumlah,gedung,lantai,ruangan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nProduk = itemView.findViewById(R.id.nama);
            jumlahProduk = itemView.findViewById(R.id.jumlah);
            hasilJumlah = itemView.findViewById(R.id.hasil);
            gedung = itemView.findViewById(R.id.gedung);
            lantai = itemView.findViewById(R.id.lantai);
            ruangan = itemView.findViewById(R.id.ruangan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}