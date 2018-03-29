package com.coba.ginanjar_1202150068_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ginanjarwidya on 25/03/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    //deklarasi variable yang akan digunakan
    private Context cntx;
    private List<ListItem> list;
    int color;

    //konstruktor


    public ListAdapter(Context cntx, List<ListItem> list, int color) {
        this.cntx = cntx;
        this.list = list;
        this.color = color;
    }

    //menentukan viewholder untuk recyclerview
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(cntx).inflate(R.layout.activity_list_item, parent, false);
        ViewHolder hldr = new ViewHolder(view);
        return hldr;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(cntx.getResources().getColor(this.color));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter
    public ListItem getData(int position){
        return list.get(position);
    }

    //untuk mengVie list pada todolist
    public void deleteData(int i){
        //remove item yang terpilih
        list.remove(i);
        //memberi notifikasi item yang di remove
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //deklarasi variable yang akan digunakan
        public TextView ToDo, Description, Priority;
        public CardView cardv;
        public ViewHolder(View itemView){
            super(itemView);

            //mengakses id text view pada layout dan juga cardview
            ToDo = itemView.findViewById(R.id.tdText);
            Description = itemView.findViewById(R.id.DescText);
            Priority = itemView.findViewById(R.id.number);
            cardv = itemView.findViewById(R.id.card);
        }
    }
}
