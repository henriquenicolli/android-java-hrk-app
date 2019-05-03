package com.henrique.projetomobile.com.henrique.projetomobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;


import java.util.List;

public class OcurrenceAdapter extends RecyclerView.Adapter<OcurrenceAdapter.MyViewHolder> {

    private List<Ocurrence> Ocurrences;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView observation;

        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.txt_ocurrence_title);
            observation = v.findViewById(R.id.txt_ocurrence_observation);
        }
    }

    public OcurrenceAdapter(List<Ocurrence> myDataset) {
        Ocurrences = myDataset;
    }

    @Override
    public OcurrenceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ocurrence_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(Ocurrences.get(position).Title);
        holder.observation.setText(Ocurrences.get(position).Description);
    }

    @Override
    public int getItemCount() {
        return Ocurrences.size();
    }

}
