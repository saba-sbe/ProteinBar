package com.example.proteinbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BasketController extends MySqLiteOpenHelper {

    MySqLiteOpenHelper mySqLiteOpenHelper;

    Context Bcontext;
    public BasketController(@Nullable Context context) {
        super(context);
        this.Bcontext = context;
    }

    public boolean create(BasketModel objectbasket) {
        mySqLiteOpenHelper = new MySqLiteOpenHelper(Bcontext);
        ContentValues values = new ContentValues();

        values.put("id", objectbasket.getId());
        values.put("name", objectbasket.getName());
        values.put("price", objectbasket.getPrice());
        values.put("number", objectbasket.getNumber());
        values.put("image", objectbasket.getPhoto());


        SQLiteDatabase db = mySqLiteOpenHelper.getWritableDatabase();

        boolean createSuccessful = db.insert(tableName, null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM " + tableName;
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public List<BasketModel> read() {

        List<BasketModel> recordsList = new ArrayList<BasketModel>();

        String sql = "SELECT * FROM cart ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String basketName = cursor.getString(cursor.getColumnIndex("name"));
                int basketPrice = Integer.parseInt(cursor.getString(cursor.getColumnIndex("price")));
                int basketNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex("number")));
                String basketImage = cursor.getString(cursor.getColumnIndex("image"));

                BasketModel objectBasket = new BasketModel(id , basketName , basketPrice , basketNumber , basketImage);

                recordsList.add(objectBasket);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public BasketModel readSingleRecord(int basketId) {

        BasketModel objectbasket = null;

        String sql = "SELECT * FROM cart WHERE id = " + basketId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int price = Integer.parseInt(cursor.getString(cursor.getColumnIndex("price")));
            int number = Integer.parseInt(cursor.getString(cursor.getColumnIndex("number")));
            String photo = cursor.getString(cursor.getColumnIndex("image"));

            objectbasket = new BasketModel(id , name ,price ,number ,photo);

        }

        cursor.close();
        db.close();

        return objectbasket;

    }

    public   boolean update(BasketModel objectbasket) {
        mySqLiteOpenHelper = new MySqLiteOpenHelper(Bcontext);

        ContentValues values = new ContentValues();

        values.put("price", objectbasket.getPrice());
        values.put("number", objectbasket.getNumber());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectbasket.getId()) };

        SQLiteDatabase db = mySqLiteOpenHelper.getWritableDatabase();

        boolean updateSuccessful = db.update("cart", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }


}
