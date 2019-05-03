package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;

import java.io.Serializable;
import java.util.List;

public class OcurrenceDataActivity extends AppCompatActivity {

    Button BtnSave;
    EditText EdtTitle;
    EditText EdtDescription;
    Ocurrence ocurrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(InfoActivity.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocurrence_data);

        BtnSave = (Button) findViewById(R.id.btn_save);
        EdtTitle = (EditText) findViewById(R.id.txt_title);
        EdtDescription = (EditText) findViewById(R.id.txt_description);


        Intent i = getIntent();
        Ocurrence selectedOcurrence = (Ocurrence)i.getSerializableExtra("data");
        if(selectedOcurrence != null)
            ocurrence = selectedOcurrence;

        loadData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadData() {
        if(ocurrence == null)
            return;

        EdtTitle.setText(ocurrence.Title);
        EdtDescription.setText(ocurrence.Description);
    }


    public void Save(View view) {
        if(ocurrence == null)
            ocurrence = new Ocurrence();

        ocurrence.setTitle(EdtTitle.getText().toString());
        ocurrence.setDescription(EdtDescription.getText().toString());

        if(OcurrenceListActivity.Ocurrences.contains(ocurrence)){
            OcurrenceListActivity.Ocurrences.remove(ocurrence);
            OcurrenceListActivity.Ocurrences.add(ocurrence);
        }
        else{
            OcurrenceListActivity.Ocurrences.add(ocurrence);
        }



        finish();
    }

    public static <E> boolean containsInstance(List<E> list, Class<? extends E> clazz) {
        for (E e : list) {
            if (clazz.isInstance(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();

        }

        return true;
    }
}
