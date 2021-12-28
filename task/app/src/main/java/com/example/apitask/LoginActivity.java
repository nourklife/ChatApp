package com.example.apitask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apitask.modl.SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
EditText mEmail,mPassword;
Button mLogin,mSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.pass);
        mSign=findViewById(R.id.sign);
        mLogin=findViewById(R.id.Login);
        mSign.setOnClickListener(this);
        mLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Login:
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                //if (vaildpasrm())
               // callLoginApi();
                break;
            case R.id.sign:
                Intent intent1=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;

        }
    }

    private void callLoginApi() {
        Request request=WepSerivese.getRetrofit().create(Request.class);
        Call<SignUp> call=request.login(mEmail.getText().toString(),mPassword.getText().toString());
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                Log.e("sucess",response.body().getMsg());
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Log.e("suceess",t.getMessage());

            }
        });
    }

    boolean vaildpasrm(){


        if (mEmail.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this,"please enter your email",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (mPassword.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this,"pleas entr your password ",Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;
    }
}
