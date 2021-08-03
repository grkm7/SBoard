package com.info.sboard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FavBoardDAO {

    public ArrayList<Boards> allFavBoards(Veritabani vt){
        ArrayList<Boards> favBoardsArrayList =  new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM favboards",null);

        while (c.moveToNext()){
            Boards board = new Boards(c.getInt(c.getColumnIndex("board_id")),
                    c.getString(c.getColumnIndex("board_image")),
                    c.getString(c.getColumnIndex("board_name")),
                    c.getString(c.getColumnIndex("board_color")),
                    c.getString(c.getColumnIndex("board_size")),
                    c.getString(c.getColumnIndex("board_price")));

            favBoardsArrayList.add(board);
        }
        return favBoardsArrayList;
    }
    public void addBook(Veritabani vt,String board_image,String board_name,String board_color,String board_size,String board_price){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("board_image",board_image);
        values.put("board_name",board_name);
        values.put("board_color",board_color);
        values.put("board_size",board_size);
        values.put("board_price",board_price);

        dbx.insertOrThrow("favboards",null,values);
        dbx.close();

    }
    public void deleteBook(Veritabani vt,String board_name){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("favboards","board_name=?",new String[]{board_name});
        db.close();
    }
    /*public void updateBook(DatabaseHelper dh,int book_id,String book_title,String book_author,int book_page){
        SQLiteDatabase dbx = dh.getWritableDatabase();
        ContentValues newBook = new ContentValues();

        newBook.put("book_title",book_title);
        newBook.put("book_author",book_author);
        newBook.put("book_pages",book_page);

        dbx.update("book",newBook,"book_id=?",new String[]{String.valueOf(book_id)});
        dbx.close();

    }
    public void deleteAll(DatabaseHelper dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.execSQL("delete from "+ "book");
        db.close();
    }*/
}
