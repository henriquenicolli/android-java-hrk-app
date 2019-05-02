package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.adapter.MyAdapter;
import com.henrique.projetomobile.com.henrique.projetomobile.adapter.RecyclerItemClickListener;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;

import java.util.ArrayList;
import java.util.List;

public class OcurrenceListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static List<Ocurrence> Ocurrences = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocurrence_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(Ocurrences);
        recyclerView.setAdapter(mAdapter);

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

        final Ocurrence selectedOcurrence = Ocurrences.get(position);

        final String[] options = {
                getString(R.string.menu_show),
                getString(R.string.menu_edit),
                getString(R.string.menu_remove)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(OcurrenceListActivity.this);
        builder.setTitle(getString(R.string.menu_select_options));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(options[which].equals(getString(R.string.menu_show))){
                    Toast.makeText(OcurrenceListActivity.this, "mostra" + selectedOcurrence.getTitle(), Toast.LENGTH_SHORT).show();
                }

                else if (options[which].equals(getString(R.string.menu_edit))){
                    Toast.makeText(OcurrenceListActivity.this, "edita" + selectedOcurrence.getTitle(), Toast.LENGTH_SHORT).show();
                }

                else if(options[which].equals(getString(R.string.menu_remove))){
                    remove(selectedOcurrence);
                }
            }
        });
        builder.show();


    }

    private void remove(Ocurrence selectedOcurrence) {
        Ocurrences.remove(selectedOcurrence);
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
