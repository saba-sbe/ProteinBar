package com.example.proteinbar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqLiteOpenHelper extends SQLiteOpenHelper {

    public static final String databaseName = "goshtalo";
    public static final String tableName = "cart";
    public static final String cart_id = "id";
    public static final String cart_name = "name";
    public static final String cart_price = "price";
    public static final String cart_image = "image";
    public static final String cart_number = "number";


    static final String createTable = "CREATE TABLE " + tableName +
            "(" + cart_id + " INTEGER PRIMARY KEY, " +
            cart_name + " TEXT, " +
            cart_price + " INTEGER, " +
            cart_number + " INTEGER, " +
            cart_image + " TEXT);";

    static final String sql = "DROP TABLE IF EXISTS " + tableName;


    public MySqLiteOpenHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }
}
