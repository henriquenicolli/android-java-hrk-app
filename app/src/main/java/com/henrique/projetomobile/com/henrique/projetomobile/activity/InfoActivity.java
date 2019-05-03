package com.henrique.projetomobile.com.henrique.projetomobile.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
    public static int theme = R.style.AppTheme;
    public static boolean isChecked;
    private static final String KEY = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        new ThemeColors(this);

        swt_theme = findViewById(R.id.switch1);
        swt_theme.setChecked(isChecked);

        swt_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swt_theme.isChecked()){
                    isChecked = true;

                    SharedPreferences.Editor editor = getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
                    editor.putBoolean(KEY, isChecked);
                    editor.apply();

                    theme = R.style.AppThemeDark;
                    Intent it = new Intent(InfoActivity.this, MainActivity.class);
                    startActivity(it);
                }
                else{
                    isChecked = false;

                    SharedPreferences.Editor editor = getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
                    editor.putBoolean(KEY, isChecked);
                    editor.apply();

                    theme = R.style.AppTheme;
                    Intent it = new Intent(InfoActivity.this, MainActivity.class);
                    startActivity(it);

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
