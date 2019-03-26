package com.zerone.hospitalmanagement;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserPreference(Context context){
        sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoginStatus(boolean loginStatus){
        editor.putBoolean("status", loginStatus);
        editor.commit();
    }

    public boolean getLoginStatus(){
        return sharedPreferences.getBoolean("status", false);
    }

    public void setDataFirstTimeEntry(boolean stutus){
        editor.putBoolean("dataInsert", stutus);
        editor.commit();
    }

    public boolean getDataFirstTimeEntry(){
        return sharedPreferences.getBoolean("dataInsert",true);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

}
