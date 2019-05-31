package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.adapter.LocationAdapter;
import com.henrique.projetomobile.com.henrique.projetomobile.adapter.RecyclerItemClickListener;
import com.henrique.projetomobile.com.henrique.projetomobile.database.AppDatabase;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Location;

import java.util.List;

public class LocationListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Location> locations;
    private Location selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        showAlertDialog(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // not implemented
                    }
                })
        );
    }

    private void showAlertDialog(int position) {
        selectedLocation = locations.get(position);

        final String[] options = {
                getString(R.string.menu_edit),
                getString(R.string.menu_remove),
                getString(R.string.menu_view_locations)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(LocationListActivity.this);
        builder.setTitle(getString(R.string.menu_select_options));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals(getString(R.string.menu_edit))){
                    Intent it = new Intent(LocationListActivity.this, LocationDataActivity.class);
                    it.putExtra("data",selectedLocation);
                    startActivity(it);
                }
                else if(options[which].equals(getString(R.string.menu_remove))){
                    remove(selectedLocation);
                }
                else if(options[which].equals(getString(R.string.menu_view_locations))){
                    Intent it = new Intent(LocationListActivity.this, LocationListActivity.class);
                    startActivity(it);

                }
            }
        });
        builder.show();
        
    }

    private void remove(final Location selectedLocation) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LocationListActivity.this);
        builder.setTitle(getString(R.string.menu_select_remove));
        builder.setPositiveButton(R.string.menu_remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                locations.remove(selectedLocation);
                AppDatabase.getAppDatabase(LocationListActivity.this).locationDao().remove(selectedLocation);
                mAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton(R.string.menu_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        locations = AppDatabase.getAppDatabase(LocationListActivity.this).locationDao().getAll();

        mAdapter = new LocationAdapter(locations, LocationListActivity.this);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
        }

        return true;
    }
}
