package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserData {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    public final static String KEY_APP_NAME="appname";
    public final static String KEY_USER_EMAIL="email";
    public final static String KEY_PASS="pass";
    public final static String KEY_USR_STATUS="status";
    public final static String KEY_USR_COUNTER="counter";


    public UserData(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(KEY_APP_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public  void saveData( String email,String password ,Boolean status){
        editor.putString(KEY_USER_EMAIL,email);
        editor.putString(KEY_PASS,password);
        editor.putBoolean(KEY_USR_STATUS,status);
        editor.apply();

    }
    public  void saveCounter(String counter){
        editor.putString(KEY_USR_COUNTER,counter);
        editor.apply();
    }
    public String getCount(){
        return  sharedPreferences.getString(KEY_USR_COUNTER,"0");
    }
    public HashMap<String,String>getData(){
       HashMap<String,String>  user =new HashMap<>();
       user.put(KEY_USER_EMAIL,sharedPreferences.getString(KEY_USER_EMAIL,null));
       user.put(KEY_PASS,sharedPreferences.getString(KEY_PASS,null));
       return user;

    }
    public boolean isLogin(){
         return sharedPreferences.getBoolean(KEY_USR_STATUS,false);
    }
    public void logOut(){

        editor.clear();
        editor.commit();
        Intent intent=new Intent(context,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
