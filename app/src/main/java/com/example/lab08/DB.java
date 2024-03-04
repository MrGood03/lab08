package com.example.lab08;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    int pos;
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "Create table notes (id int , txt text);";
        db.execSQL(sql);

    }
    public int getMaxId()
    {
        SQLiteDatabase db =getReadableDatabase();
        String sql= "select MAX(id) from notes;";
        Cursor cur =db.rawQuery(sql,null    );
        if (cur.moveToFirst()==true) return cur.getInt(0);
        return 0;

    }
    public void addNote (int id ,String stxt)
    {
        String sid = String.valueOf(id);
        SQLiteDatabase db= getWritableDatabase();
        String sql = "insert into notes values ("+ sid +", '"+stxt+"');";
        db.execSQL(sql);

    }
    public  String getNote (int id)
    {
        String sid =String.valueOf(id);
        SQLiteDatabase db=getReadableDatabase();
        String sql ="select txt from notes where id = "+sid+";";
        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst()==true) return cur.getString(0);
        return "";
    }
    public void getAllNotes (ArrayList<mynote> lst)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql= "Select id,txt from notes;";
        Cursor cur =db.rawQuery(sql,null);
        if (cur.moveToFirst()==true)
        {
            do {
                mynote n =new mynote();
                n.id =cur.getInt(0);
                n.txt= cur.getString(1);
                lst.add(n);

            }while (cur.moveToNext()==true);
        }



    }
    public void alterNote (int id, String stxt)
    {
        String sid =String.valueOf(id);
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update notes  set txt = '"+stxt+"'Where id ='"+sid+"';";
        db.execSQL(sql);
        this.close();

    }
    public void deleteNote (int id )
    {
        String sid =String.valueOf(id);
        SQLiteDatabase db = getReadableDatabase();

        String sql ="delete from notes Where id='"+sid+"';";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
