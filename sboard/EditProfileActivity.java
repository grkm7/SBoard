package com.info.sboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {
    private EditText etProfileName;
    private Button buttonProfileUpdate;

    private String name;

    private Veritabani vt ;

    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etProfileName=findViewById(R.id.etProfileName);
        buttonProfileUpdate=findViewById(R.id.buttonProfileUpdate);

        new MyAsyncTaskReadName().execute();

        buttonProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTaskUpdateName().execute();
                BildirimAl();
            }
        });
    }
    public class MyAsyncTaskReadName extends AsyncTask<String, ProgressDialog,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            vt=new Veritabani(EditProfileActivity.this);
            SQLiteDatabase dbx = vt.getWritableDatabase();
            Cursor c = dbx.rawQuery("SELECT * FROM user",null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("user_id"));
                name = c.getString(c.getColumnIndex("user_name"));
                etProfileName.setHint(name);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(ProgressDialog... values) {

            AlertDialog.Builder ad=new AlertDialog.Builder(EditProfileActivity.this);
            ad.setMessage(getResources().getString(R.string.plese_wait));
            ad.create().show();

            super.onProgressUpdate(values);
        }
    }
    public class MyAsyncTaskUpdateName extends AsyncTask<String, ProgressDialog,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            vt=new Veritabani(EditProfileActivity.this);

            String newName=etProfileName.getText().toString().trim();
            new UserDAO().updateUser(vt,1,newName);

            Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        @Override
        protected void onProgressUpdate(ProgressDialog... values) {

            AlertDialog.Builder ad=new AlertDialog.Builder(EditProfileActivity.this);
            ad.setMessage(getResources().getString(R.string.plese_wait));
            ad.create().show();

            super.onProgressUpdate(values);
        }


    }
    public void BildirimAl(){
        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(EditProfileActivity.this,PageWithTabs.class);
        PendingIntent pi = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String kanalID="kanalID";
            String kanalAd="kanalAD";
            String kanalTanım="kanalTanım";
            int kanalOnceligi=NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel kanal=nm.getNotificationChannel(kanalID);

            if (kanal==null){
                kanal=new NotificationChannel(kanalID,kanalAd,kanalOnceligi);
                kanal.setDescription(kanalTanım);
                nm.createNotificationChannel(kanal);
            }
            builder = new NotificationCompat.Builder(this,kanalID);
            builder.setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText("Hesabınız güncellendi");
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.new_icon_foreground);
            builder.setContentIntent(pi);

        }else{
            builder = new NotificationCompat.Builder(this);
            builder.setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText(getResources().getString(R.string.account_updated));
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.new_icon_foreground);
            builder.setContentIntent(pi);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        nm.notify(1,builder.build());
    }
}