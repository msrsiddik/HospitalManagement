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
    public static final String TABLE_DOCTOR_COL_PASS = "doctor_password";

    //Doctor Table Create Query
    public static final String CREATE_TABLE_DOCTOR = "create table "+TABLE_DOCTOR+"("+
            TABLE_DOCTOR_COL_ID+" integer primary key, "+
            TABLE_DOCTOR_COL_NAME+" text, "+
            TABLE_DOCTOR_COL_EDU+" text, "+
            TABLE_DOCTOR_COL_PRO+" text, "+
            TABLE_DOCTOR_COL_CATEGORY+" text, "+
            TABLE_DOCTOR_COL_ADDRESS+" text, "+
            TABLE_DOCTOR_COL_MOBILE+" text, "+
            TABLE_DOCTOR_COL_EMAIL+" text, "+
            TABLE_DOCTOR_COL_PASS+" text)";

    //Patient Table And Column Name
    public static final String TABLE_PATIENT = "patient_tbl";
    public static final String TABLE_PATIENT_COL_ID = "patient_id";
    public static final String TABLE_PATIENT_COL_NAME = "patient_name";
    public static final String TABLE_PATIENT_COL_GENDER = "patient_gender";
    public static final String TABLE_PATIENT_COL_AGE = "patient_age";
    public static final String TABLE_PATIENT_COL_ADDRESS = "patient_address";
    public static final String TABLE_PATIENT_COL_DOCTOR = "patients_doctor";

    //Patient Table Create Query
    public static final String CREATE_TABLE_PATIENT = "create table "+TABLE_PATIENT+"(" +
            TABLE_PATIENT_COL_ID+" integer primary key, "+
            TABLE_PATIENT_COL_NAME+" text, "+
            TABLE_PATIENT_COL_GENDER+" text, "+
            TABLE_PATIENT_COL_AGE+" text, "+
            TABLE_PATIENT_COL_ADDRESS+" text, "+
            TABLE_PATIENT_COL_DOCTOR+" text )";

    public HospitalDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DOCTOR);
        db.execSQL(CREATE_TABLE_PATIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
