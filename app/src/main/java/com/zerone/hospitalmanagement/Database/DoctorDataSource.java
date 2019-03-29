package com.zerone.hospitalmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zerone.hospitalmanagement.Model.DoctorModel;
import com.zerone.hospitalmanagement.Model.DoctorUserModel;

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
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_PASS, doctorModel.getDoctorPassword());

        long insertRow = db.insert(HospitalDBHelper.TABLE_DOCTOR,null,values);
        this.closeDB();

        return insertRow;
    }

    public List<DoctorUserModel> doctorUserModels(){
        List<DoctorUserModel> doctorUserModels = new ArrayList<>();
        this.openDB();

        Cursor cursor = db.rawQuery("select "+HospitalDBHelper.TABLE_DOCTOR_COL_ID+", "+HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL+", "+HospitalDBHelper.TABLE_DOCTOR_COL_PASS+" from " + HospitalDBHelper.TABLE_DOCTOR,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_ID));
                String email = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL));
                String pass = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PASS));

                doctorUserModels.add(new DoctorUserModel(id,email,pass));
            }while (cursor.moveToNext());

        }

        cursor.close();
        this.closeDB();
        return doctorUserModels;
    }

    public List<DoctorModel> getAlldoctorInfoCollectList(){
        List<DoctorModel> infoCollects = new ArrayList<>();
        this.openDB();

        Cursor cursor = db.rawQuery("select * from "+ HospitalDBHelper.TABLE_DOCTOR,null);
        if (cursor != null && cursor.getCount() > 0){
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
                String pass = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PASS));

                infoCollects.add(new DoctorModel(id,name,edu,pro,cate,add,mobile,email,pass));
            }while (cursor.moveToNext());
        }

        cursor.close();
        this.closeDB();
        return infoCollects;
    }

    public DoctorModel getDoctorInfoById(int id){
        DoctorModel doctorModel = null;
        this.openDB();
        Cursor cursor = db.rawQuery("select * from "+ HospitalDBHelper.TABLE_DOCTOR +" where "+HospitalDBHelper.TABLE_DOCTOR_COL_ID+" = "+id,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_NAME));
            String edu = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EDU));
            String pro = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PRO));
            String cate = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_CATEGORY));
            String add = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_ADDRESS));
            String mobile = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_MOBILE));
            String email = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL));
            String pass = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PASS));

            doctorModel = new DoctorModel(id,name,edu,pro,cate,add,mobile,email,pass);
        }
        cursor.close();
        this.closeDB();
        return doctorModel;
    }

    public int updateDoctorInfo(DoctorModel doctorModel){
        this.openDB();
        ContentValues values = new ContentValues();
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_NAME, doctorModel.getDoctorName());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_EDU, doctorModel.getDoctorEducation());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_PRO, doctorModel.getDoctorProfession());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_CATEGORY, doctorModel.getDoctorCategory());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_ADDRESS, doctorModel.getDoctorAddress());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_MOBILE, doctorModel.getDoctorMobile());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL, doctorModel.getDoctorEmail());
        values.put(HospitalDBHelper.TABLE_DOCTOR_COL_PASS, doctorModel.getDoctorPassword());

        final int updateRow = db.update(HospitalDBHelper.TABLE_DOCTOR,values,HospitalDBHelper.TABLE_DOCTOR_COL_ID+" = "+doctorModel.getId(),null);
        this.closeDB();

        return updateRow;
    }

    public int deleteDoctorById(int doctorId){
        this.openDB();
        final int deleteRow = db.delete(HospitalDBHelper.TABLE_DOCTOR,HospitalDBHelper.TABLE_DOCTOR_COL_ID+" = "+doctorId,null);
        this.closeDB();
        return deleteRow;
    }

    public DoctorModel getDoctorInfoByName(String doctorName){
        DoctorModel doctorModel = null;
        this.openDB();
        Cursor cursor = db.rawQuery("select * from "+ HospitalDBHelper.TABLE_DOCTOR +" where "+HospitalDBHelper.TABLE_DOCTOR_COL_NAME+" = "+"'"+doctorName+"'",null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_NAME));
            String edu = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EDU));
            String pro = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PRO));
            String cate = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_CATEGORY));
            String add = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_ADDRESS));
            String mobile = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_MOBILE));
            String email = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_EMAIL));
            String pass = cursor.getString(cursor.getColumnIndex(HospitalDBHelper.TABLE_DOCTOR_COL_PASS));

            doctorModel = new DoctorModel(name,edu,pro,cate,add,mobile,email,pass);
        }
        cursor.close();
        this.closeDB();
        return doctorModel;
    }

}
