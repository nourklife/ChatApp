package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {
EditText mEmail,mPassword;
Button mSave;
UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.pass);
        mSave=findViewById(R.id.start);
        mSave.setOnClickListener(this);
        userData=new UserData(SignActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                if(checkData()){
     userData.saveData(mEmail.getText().toString(),mPassword.getText().toString(),true);
                startActivity(new Intent(SignActivity.this,HomeActivity.class));}
                break;
        }





    }
     public boolean checkData(){
        if (mEmail.getText().toString().isEmpty()){
            Toast.makeText(SignActivity.this,"please enter your email",Toast.LENGTH_LONG).show();
            return false;
        }
        else  if (mPassword.getText().toString().isEmpty()){
            Toast.makeText(SignActivity.this,"please enter your password",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }
     }

}
