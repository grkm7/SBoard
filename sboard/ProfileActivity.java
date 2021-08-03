package com.info.sboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar nav_toolbar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    private ImageView Profile_image;
    private TextView tvProfileName;
    private Button buttonPickImage;

    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Profile_image=findViewById(R.id.Profile_image);
        tvProfileName=findViewById(R.id.tvProfileName);
        buttonPickImage=findViewById(R.id.buttonPickImage);


        dl=findViewById(R.id.drawer);

        nav_toolbar=findViewById(R.id.nav_toolbar);
        nav_toolbar.setTitle(getResources().getString(R.string.SBoard_profile));
        setSupportActionBar(nav_toolbar);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        dl.addDrawerListener(abdt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        abdt.syncState();
        NavigationView navigation_view=findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(this);

        vt=new Veritabani(ProfileActivity.this);
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM user",null);
        while (c.moveToNext()){
            int id=c.getInt(c.getColumnIndex("user_id"));
            String name=c.getString(c.getColumnIndex("user_name"));
            tvProfileName.setText(name);
        }

        if (tvProfileName.getText().equals("Unknown")){
            Log.e("mesaj","okudu");

            /*vt=new Veritabani(this);
            new UserDAO().addUser(vt);//this user record is only for stakeholder
            Intent inten= new Intent(ProfileActivity.this,EditProfileActivity.class);
            startActivity(inten);*/
            new MyAsyncTaskAddName().execute();
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (abdt.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_goProfile){
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.action_profile_info){
            Intent intent = new Intent(ProfileActivity.this, infoActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.action_ios){
            Intent intent = new Intent(ProfileActivity.this, AppInfoActivity.class);
            intent.putExtra("fragment","ios");
            startActivity(intent);
        }else if (item.getItemId()==R.id.action_free){
            Intent intent = new Intent(ProfileActivity.this, AppInfoActivity.class);
            intent.putExtra("fragment","free");
            startActivity(intent);
        }
        return false;
    }
    public class MyAsyncTaskAddName extends AsyncTask<String, ProgressDialog,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            vt=new Veritabani(ProfileActivity.this);
            new UserDAO().addUser(vt);//this user record is only for stakeholder
            Intent inten= new Intent(ProfileActivity.this,EditProfileActivity.class);
            startActivity(inten);
            return true;
        }

        @Override
        protected void onProgressUpdate(ProgressDialog... values) {

            AlertDialog.Builder ad=new AlertDialog.Builder(ProfileActivity.this);
            ad.setMessage(getResources().getString(R.string.plese_wait));
            ad.create().show();

            super.onProgressUpdate(values);
        }
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)){
            dl.closeDrawer(GravityCompat.START);
        }else{
            Intent intent = new Intent(ProfileActivity.this,PageWithTabs.class);
            startActivity(intent);
        }

    }
}