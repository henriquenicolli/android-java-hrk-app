package com.henrique.projetomobile.com.henrique.projetomobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.database.AppDatabase;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Location;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;


import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private List<Location> locations;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView city;
        public TextView country;
        public TextView ocurrence;

        public MyViewHolder(View v) {
            super(v);
            city = v.findViewById(R.id.txt_location_city);
            country = v.findViewById(R.id.txt_location_country);
            ocurrence = v.findViewById(R.id.txt_ocurrence);
        }
    }

    public LocationAdapter(List<Location> myDataset, Context ctx) {
        this.locations = myDataset;
        this.context = ctx;
    }

    @Override
    public LocationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LocationAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LocationAdapter.MyViewHolder holder, int position) {
        holder.city.setText(locations.get(position).getCityName());
        holder.country.setText(locations.get(position).getCountry());

        int ocurrenceId = locations.get(position).getOcurrenceId();
        Ocurrence ocurrence = AppDatabase.getAppDatabase(context).ocurrenceDao().getById(ocurrenceId);
        holder.ocurrence.setText(ocurrence.getTitle());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

}
