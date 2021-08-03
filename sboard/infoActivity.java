package com.info.sboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class infoActivity extends AppCompatActivity {
    private Toolbar info_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        info_toolbar=findViewById(R.id.info_toolbar);

        setSupportActionBar(info_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}