package com.zerone.hospitalmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zerone.hospitalmanagement.Model.PatientModel;

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
}
