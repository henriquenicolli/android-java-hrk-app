package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.database.AppDatabase;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Location;
import com.henrique.projetomobile.com.henrique.projetomobile.model.Ocurrence;

public class LocationDataActivity extends AppCompatActivity {

    EditText edtCityName;
    EditText edtCountry;
    Location location;
    int idOcurrence;
    boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtCityName = findViewById(R.id.txt_city);
        edtCountry = findViewById(R.id.txt_country);

        Intent i = getIntent();
        idOcurrence = i.getIntExtra("ocurenceId", 0);
        Location selectedLocation = (Location) i.getSerializableExtra("data");

        if(selectedLocation != null)
            loadData(selectedLocation);

    }

    private void loadData(Location selectedLocation) {
        isUpdate = true;
        location = selectedLocation;

        edtCityName.setText(selectedLocation.getCityName());
        edtCountry.setText(selectedLocation.getCountry());
    }


    public void Save(View view) {
        if(location == null)
            location = new Location();

        location.setCityName(edtCityName.getText().toString());
        location.setCountry(edtCountry.getText().toString());

        if(isUpdate)
            AppDatabase.getAppDatabase(LocationDataActivity.this).locationDao().update(location);
        else{
            location.setOcurrenceId(idOcurrence);
            AppDatabase.getAppDatabase(LocationDataActivity.this).locationDao().insert(location);
        }


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
