package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.henrique.projetomobile.R;
import com.henrique.projetomobile.com.henrique.projetomobile.util.ThemeColors;

import java.util.Random;

public class InfoActivity extends AppCompatActivity {

    private Switch swt_theme;
    private static boolean isDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        new ThemeColors(this);

        swt_theme = findViewById(R.id.switch1);

        swt_theme.setChecked(isDarkTheme);

        swt_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swt_theme.isChecked()){
                    isDarkTheme = true;
                    int red= new Random().nextInt(255);
                    int green= new Random().nextInt(255);
                    int blue= new Random().nextInt(255);
                    ThemeColors.setNewThemeColor(InfoActivity.this, red, green, blue);
                }
                else{
                    isDarkTheme = false;
                    getTheme().applyStyle(R.style.AppTheme, true);
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
        }

        return true;
    }
}
