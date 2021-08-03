package com.info.sboard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDAO {

    /*public ArrayList<User> allUser(Veritabani vt){
        ArrayList<User> UserList =  new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM user",null);

        while (c.moveToNext()){
            User user = new User(c.getInt(c.getColumnIndex("user_id")),
                    c.getString(c.getColumnIndex("user_name")),
                    c.getString(c.getColumnIndex("user_surname")),
                    c.getString(c.getColumnIndex("user_phone")));

            UserList.add(user);
        }
        return UserList;
    }*/

    public void addUser(Veritabani vt){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_name","YourName");

        dbx.insertOrThrow("user",null,values);
        dbx.close();

    }
    public void updateUser(Veritabani dh,int user_id,String user_name){
        SQLiteDatabase dbx = dh.getWritableDatabase();
        ContentValues newUser = new ContentValues();

        newUser.put("user_name",user_name);


        dbx.update("user",newUser,"user_id=?",new String[]{String.valueOf(1)});
        dbx.close();

    }
}
