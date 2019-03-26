package com.zerone.hospitalmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zerone.hospitalmanagement.Model.DoctorModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorDataSource {
    private HospitalDBHelper hospitalDBHelper;
    private SQLiteDatabase db;

    public DoctorDataSource(Context context) {
        hospitalDBHelper = new HospitalDBHelper(context);
    }

    public void openDB(){
        db = hospitalDBHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    public long insertNewDoctor(DoctorModel doctorModel){
        this.openDB();
        ContentValues values = new ContentValues();
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_NAME, doctorModel.getDoctorName());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_EDU, doctorModel.getDoctorEducation());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_PRO, doctorModel.getDoctorProfession());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_CATEGORY, doctorModel.getDoctorCategory());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_ADDRESS, doctorModel.getDoctorAddress());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_MOBILE, doctorModel.getDoctorMobile());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL, doctorModel.getDoctorEmail());

        long insertRow = db.insert(HospitalDBHelper.TABLE_DOCTOR,null,values);
        this.closeDB();

        return insertRow;
    }

    public List<DoctorModel> getAlldoctorInfoCollectList(){
        List<DoctorModel> infoCollects = new ArrayList<>();
        this.openDB();

        Cursor cursor = db.rawQuery("select * from "+ HospitalDBHelper.TABLE_DOCTOR,null);
        if (cursor != null && cursor.getCount() >0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_NAME));
                String edu = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EDU));
                String pro = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PRO));
                String cate = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_CATEGORY));
                String add = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_ADDRESS));
                String mobile = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_MOBILE));
                String email = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL));

                infoCollects.add(new DoctorModel(id,name,edu,pro,cate,add,mobile,email));
            }while (cursor.moveToNext());
        }

        cursor.close();
        this.closeDB();
        return infoCollects;
    }
}
