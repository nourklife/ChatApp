package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
UserData userData;
String TAG = "Main Activity";
Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData = new UserData(MainActivity.this);
       // splishActivity();

        handler=new Handler();
        handler.postDelayed(run(),3000);


    }

    private Runnable run (){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if (userData.isLogin()){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(),SignActivity.class));
                }
                finish();
            }
        };
        return run;
    }
    private void splishActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userData.isLogin()){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,SignActivity.class));
                }
                finish();
            }
        },3000);
    }
   /* Thread background = new Thread(){

        public void run() {
            try{
             sleep(3*1000);
                if (userData.isLogin()){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,SignActivity.class));
                }
            }catch (Exception e){
                Log.v(TAG,e.toString());

        }
    };*/


}
