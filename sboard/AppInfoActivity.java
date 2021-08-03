package com.info.sboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class AppInfoActivity extends AppCompatActivity {
    private String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        fragment=getIntent().getStringExtra("fragment");
        Log.e("fragment",fragment);

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        if (fragment.contains("free")){
            ft.add(R.id.fragmentTutucu,new FragmentFree());
            ft.commit();
        }


        if (fragment.contains("ios")){
            ft.replace(R.id.fragmentTutucu,new Fragmentios());
            ft.commit();
        }
    }
}