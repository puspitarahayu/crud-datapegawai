package com.puspita.crud_datapegawai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "pegawai_table";
    public static final String data_1 = "ID";
    public static final String data_2 = "NAMA";
    public static final String data_3 = "NOTELP";
    public static final String data_4 = "ALAMAT";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pegawai_table(id integer primary key autoincrement," +
                "nama text not null," +
                "notelp text not null," +
                "alamat text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nama, String notelp, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(data_2, nama);
        contentValues.put(data_3, notelp);
        contentValues.put(data_4, alamat);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from pegawai_table", null);
        return res;
    }

    public boolean updateData(String id, String nama, String notelp, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(data_1, id);
        contentValues.put(data_2, nama);
        contentValues.put(data_3, notelp);
        contentValues.put(data_4, alamat);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        return true;
    }

    public int deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

}
