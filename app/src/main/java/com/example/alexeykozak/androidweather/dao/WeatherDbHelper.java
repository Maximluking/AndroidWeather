package com.example.alexeykozak.androidweather.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alexeykozak.androidweather.MainApplication;
import com.example.alexeykozak.androidweather.model.ContractClass;

public class WeatherDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "WeatherDbHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Weather.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " REAL";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_WEATHER_TABLE =
            "CREATE TABLE " + ContractClass.Weather.TABLE_NAME + " (" +

                    ContractClass.Weather.COLUMN_NAME_CURRENT_TEMP + DOUBLE_TYPE + COMMA_SEP +
                    ContractClass.Weather.COLUMN_NAME_MAX_TEMP + DOUBLE_TYPE + COMMA_SEP +
                    ContractClass.Weather.COLUMN_NAME_MIN_TEMP + DOUBLE_TYPE + COMMA_SEP +
                    ContractClass.Weather.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    ContractClass.Weather.COLUMN_NAME_ICON + DOUBLE_TYPE + COMMA_SEP +
                    " )";
    private static final String SQL_CREATE_CITIES_TABLE =
            "CREATE TABLE " + ContractClass.City.TABLE_NAME + " (" +
                    ContractClass.City.COLUMN_NAME_ID + INT_TYPE + COMMA_SEP +
                    ContractClass.City.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ContractClass.City.COLUMN_NAME_WEATHER_FK_ID + INT_TYPE +
                    " )";

    public WeatherDbHelper(String name) {
        super(MainApplication.getContext(), name, null, DATABASE_VERSION);
    }

    private void fillInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(ContractClass.City.COLUMN_NAME_NAME, "Dnipro");
        values.put(ContractClass.City.COLUMN_NAME_ID, 709930);

        db.insert(ContractClass.City.TABLE_NAME, null, values);

        values.clear();

        values.put(ContractClass.City.COLUMN_NAME_NAME, "Lviv");
        values.put(ContractClass.City.COLUMN_NAME_ID, 702550);

        db.insert(ContractClass.City.TABLE_NAME, null, values);

        values.clear();

        values.put(ContractClass.City.COLUMN_NAME_NAME, "Kiev");
        values.put(ContractClass.City.COLUMN_NAME_ID, 696050);

        db.insert(ContractClass.City.TABLE_NAME, null, values);

        values.clear();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WEATHER_TABLE);
        db.execSQL(SQL_CREATE_CITIES_TABLE);
        fillInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContractClass.Weather.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ContractClass.City.TABLE_NAME);
        onCreate(db);
    }

}
