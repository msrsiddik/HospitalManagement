package com.zerone.hospitalmanagement.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class HospitalDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "hospital_db";
    public static final int DB_VERSION = 1;

    //Doctor Table And Column Name
    public static final String TABLE_DOCTOR = "doctor_tbl";
    public static final String TABLE_DOCTOR_COL_ID = "doctor_id";
    public static final String TABLE_DOCTOR_COL_NAME = "doctor_name";
    public static final String TABLE_DOCTOR_COL_EDU = "doctor_education";
    public static final String TABLE_DOCTOR_COL_PRO = "doctor_profession";
    public static final String TABLE_DOCTOR_COL_CATEGORY = "doctor_category";
    public static final String TABLE_DOCTOR_COL_ADDRESS = "doctor_address";
    public static final String TABLE_DOCTOR_COL_MOBILE = "doctor_mobile";
    public static final String TABLE_DOCTOR_COL_EMAIL = "doctor_email";

    //Doctor Table Create Query
    public static final String CREATE_TABLE_DOCTOR = "create table "+TABLE_DOCTOR+"("+
            TABLE_DOCTOR_COL_ID+" integer primary key, "+
            TABLE_DOCTOR_COL_NAME+" text, "+
            TABLE_DOCTOR_COL_EDU+" text, "+
            TABLE_DOCTOR_COL_PRO+" text, "+
            TABLE_DOCTOR_COL_CATEGORY+" text, "+
            TABLE_DOCTOR_COL_ADDRESS+" text, "+
            TABLE_DOCTOR_COL_MOBILE+" text, "+
            TABLE_DOCTOR_COL_EMAIL+" text)";

    public HospitalDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DOCTOR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
