package com.allcodingtutorials.blogerrortest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static final  String TABLE_NAME="Userdetails";
     static final   String COLUMN_DATE="created_at";
     static final  String COLUMN_DESCRIPTION="description";
     static final  String COLUMN_NAME="name";
   static  final String DATABASE_TABLE="Userdetails";
     static final String USER_ID="_ID";

  //   static final String CREATE_DB_QUERY="CREATE TABLE "+DATABASE_TABLE +" ( " +USER_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_NAME +"TEXT NOT NULL, " +COLUMN_DESCRIPTION+ " TEXT NOT NULL);";
    static final String CREATE_DB_QUERY="CREATE TABLE "+DATABASE_TABLE +" ( " +USER_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_NAME +"TEXT NOT NULL, " +COLUMN_DESCRIPTION +"TEXT NOT NULL, " +COLUMN_DATE+ " TEXT NOT NULL);";



    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(CREATE_DB_QUERY);
    //    DB.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_DATE + " CURRENT_TIMESTAMP," + COLUMN_DESCRIPTION + " TEXT)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public String insertuserdata(String name, String date,String description)
    {




        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);



        contentValues.put("name", name);
        contentValues.put("created_at",date);
        contentValues.put("description",description);

        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return "Filed";
        }else{
            return "Successfully Inserted";
        }
    }
    public Boolean updateuserdata(String name, String contact, String dob)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}