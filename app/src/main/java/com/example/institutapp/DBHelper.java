package com.example.institutapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final String dbName = "Institut.db";

    public DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryU = "CREATE TABLE users(idUser integer PRIMARY KEY  AUTOINCREMENT , username TEXT, password TEXT)";
        db.execSQL(queryU);
        db.execSQL("CREATE TABLE formations(idFormation integer PRIMARY KEY  AUTOINCREMENT, name TEXT UNIQUE, beginDate TEXT, endDate TEXT)");
        db.execSQL("CREATE TABLE enrolled(idUser integer , idFormation integer, FOREIGN KEY (idUser) REFERENCES users(idUser),FOREIGN KEY (idFormation) REFERENCES formations(idFormation))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists users");
        db.execSQL("DROP TABLE if exists formations");
        db.execSQL("DROP TABLE if exists enrolled");
    }


    // Handling Users Registration and Login
    public void insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUserPass(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from users where username = ? AND password = ?", new String [] {username , password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //Insert formations into dababase
    //date format yyyy-mm-dd

    //with image
    /*
    public Boolean insertFormation(String name, byte[] icon, String beginDate, String endDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("icon", icon);
        contentValues.put("beginDate", beginDate);
        contentValues.put("endDate", endDate);
        long result = db.insert("formations", null, contentValues);
        if(result == -1) return false;
        else return true;
    }*/

    public void insertFormation(String name,String beginDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("beginDate", beginDate);
        contentValues.put("endDate", endDate);
        long result = db.insert("formations", null, contentValues);
    }

    public Boolean checkFormation(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "SELECT name from formations where name = ?", new String[] {name});
        if (cursor.getCount()>0) return true;
        else return false;
    }


    //Get idUser

    public int getIdUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "SELECT idUser from users where username = ?", new String[] {username});
        cursor.moveToNext();
        return cursor.getInt(0);

    }


    //Get idFormation


        public int getIdFormation(String name){
        //ArrayList<Integer> listid = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "SELECT idFormation from formations where name = ?", new String[] {name});

            cursor.moveToNext();
            return cursor.getInt(0);

        //return cursor.getInt(cursor.getColumnIndex("idFormation"));
/*
            while (cursor.moveToNext()) {
                if(cursor.getCount()>0) {
                    break;
                }
            }*/


/*
        while (cursor.moveToNext()) {

                listid.add(cursor.getInt(0));
        }

        return listid.get(0);*/


        //return cursor.getCount();

    }


    public void insertEnrolled(int idUser, int idFormation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUser", idUser);
        contentValues.put("idFormation", idFormation);
        long result = db.insert("enrolled", null, contentValues);
    }

    public Boolean checkEnrollement(int idU,int idF){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from enrolled where idUser =" + idU + " AND  idFormation =" + idF, null);
        if (cursor.getCount() > 0) return true;
        else return false;
    }


}
