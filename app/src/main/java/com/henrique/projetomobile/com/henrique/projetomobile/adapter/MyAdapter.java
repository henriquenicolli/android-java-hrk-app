package com.henrique.projetomobile.com.henrique.projetomobile.adapter;

import android.os.Debug;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;


import java.io.Console;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Ocurrence> Ocurrences;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public MyViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.txt_ocurrence_title);
        }
    }

    public MyAdapter(List<Ocurrence> myDataset) {
        Ocurrences = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ocurrence_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(Ocurrences.get(position).Title);
    }

    @Override
    public int getItemCount() {
        return Ocurrences.size();
    }

}
