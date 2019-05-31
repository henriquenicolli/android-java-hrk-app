package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.adapter.OcurrenceAdapter;
import com.henrique.projetomobile.com.henrique.projetomobile.adapter.RecyclerItemClickListener;
import com.henrique.projetomobile.com.henrique.projetomobile.database.AppDatabase;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;

import java.util.ArrayList;
import java.util.List;

public class OcurrenceListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public List<Ocurrence> ocurrences = new ArrayList<>();
    public static Ocurrence selectedOcurrence = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(InfoActivity.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocurrence_list);

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

    @Override
    protected void onResume() {
        super.onResume();

        ocurrences = AppDatabase.getAppDatabase(OcurrenceListActivity.this).ocurrenceDao().getAll();

        mAdapter = new OcurrenceAdapter(ocurrences);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    private void showAlertDialog(int position) {
        selectedOcurrence = ocurrences.get(position);

        final String[] options = {
                getString(R.string.menu_edit),
                getString(R.string.menu_remove),
                getString(R.string.menu_add_location),
                getString(R.string.menu_view_locations)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(OcurrenceListActivity.this);
        builder.setTitle(getString(R.string.menu_select_options));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals(getString(R.string.menu_edit))){
                    Intent it = new Intent(OcurrenceListActivity.this, OcurrenceDataActivity.class);
                    it.putExtra("data",selectedOcurrence);
                    startActivity(it);
                }
                else if(options[which].equals(getString(R.string.menu_remove))){
                    remove(selectedOcurrence);
                }
                else if(options[which].equals(getString(R.string.menu_add_location))){
                    //add location to ocurrence
                    Intent it = new Intent(OcurrenceListActivity.this, LocationDataActivity.class);
                    it.putExtra("ocurenceId", selectedOcurrence.id);
                    startActivity(it);
                }
                else if(options[which].equals(getString(R.string.menu_view_locations))){
                    Intent it = new Intent(OcurrenceListActivity.this, LocationListActivity.class);
                    it.putExtra("ocurenceId", selectedOcurrence.id);
                    startActivity(it);

                }
            }
        });
        builder.show();
    }

    private void remove(final Ocurrence selectedOcurrence) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OcurrenceListActivity.this);
        builder.setTitle(getString(R.string.menu_select_remove));
        builder.setPositiveButton(R.string.menu_remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                ocurrences.remove(selectedOcurrence);
                AppDatabase.getAppDatabase(OcurrenceListActivity.this).ocurrenceDao().remove(selectedOcurrence);
                mAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton(R.string.menu_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.show();
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }

        return true;
    }
}
