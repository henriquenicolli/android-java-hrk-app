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

public class OcurrenceDataActivity extends AppCompatActivity {

    Button BtnSave;
    EditText EdtTitle;
    EditText EdtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(InfoActivity.theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocurrence_data);

        BtnSave = (Button) findViewById(R.id.btn_save);
        EdtTitle = (EditText) findViewById(R.id.txt_title);
        EdtDescription = (EditText) findViewById(R.id.txt_description);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void Save(View view) {
        String title = EdtTitle.getText().toString();
        String description = EdtDescription.getText().toString();

        Ocurrence ocurrence = new Ocurrence();
        ocurrence.setTitle(title);
        ocurrence.setDescription(description);

        OcurrenceListActivity.Ocurrences.add(ocurrence);

        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();

        }

        return true;
    }
}
