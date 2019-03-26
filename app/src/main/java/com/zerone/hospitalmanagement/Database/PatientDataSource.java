package com.zerone.hospitalmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zerone.hospitalmanagement.Model.PatientModel;

import java.util.ArrayList;
import java.util.List;

public class PatientDataSource {
    private HospitalDBHelper hospitalDBHelper;
    private SQLiteDatabase db;

    public PatientDataSource(Context context) {
        hospitalDBHelper = new HospitalDBHelper(context);
    }

    public void openDB(){
        db = hospitalDBHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    public long insertNewPatient(PatientModel patientModel){
        this.openDB();
        ContentValues values = new ContentValues();

        values.put(HospitalDBHelper.TABLE_PATIENT_COL_NAME,patientModel.getName());
        values.put(HospitalDBHelper.TABLE_PATIENT_COL_AGE,patientModel.getAge());
        values.put(HospitalDBHelper.TABLE_PATIENT_COL_GENDER,patientModel.getGender());
        values.put(HospitalDBHelper.TABLE_PATIENT_COL_ADDRESS,patientModel.getAddress());
        values.put(HospitalDBHelper.TABLE_PATIENT_COL_DOCTOR,patientModel.getDoctor());

        long insertRow = db.insert(HospitalDBHelper.TABLE_PATIENT,null,values);
        this.closeDB();

        return insertRow;
    }

    public List<PatientModel> getAllPatient(String doctorName){
        List<PatientModel> patientModelList = new ArrayList<>();
        this.openDB();

        Cursor cursor = db.rawQuery("select * from "+ HospitalDBHelper.TABLE_PATIENT+" where "+HospitalDBHelper.TABLE_PATIENT_COL_DOCTOR+" in "+"('"+doctorName+"')",null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(HospitalDBHelper.TABLE_PATIENT_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_PATIENT_COL_NAME));
                String gender = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_PATIENT_COL_GENDER));
                String age = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_PATIENT_COL_AGE));
                String doctor = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_PATIENT_COL_DOCTOR));

                patientModelList.add(new PatientModel(id,name,gender,age,null,doctor));
            }while (cursor.moveToNext());
        }

        this.closeDB();
        return patientModelList;
    }
}
