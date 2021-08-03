package com.info.sboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(@Nullable Context context) {
        super(context, "boards.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT);");
        db.execSQL("CREATE TABLE favboards (board_id INTEGER PRIMARY KEY AUTOINCREMENT, board_image TEXT,board_name TEXT,board_color TEXT,board_size TEXT,board_price TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favboards");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
