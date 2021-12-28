package com.example.apitask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apitask.modl.SignUp;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.textView_name)
    TextView textViewName;
    @BindView(R.id.fname)
    EditText mFname;
    @BindView(R.id.lname)
    EditText mLname;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.pass)
    EditText mPass;
    @BindView(R.id.Login)
    Button mLogin;
    @BindView(R.id.sign)
    Button mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.pass);
        mSign = findViewById(R.id.sign);
        mFname = findViewById(R.id.fname);
        mLname = findViewById(R.id.lname);
        mLogin = findViewById(R.id.Login);
        mSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login:

                break;
            case R.id.sign:
                if (vaildpasrm())
                    callRegisterApi();
                break;
        }
    }

    private void callRegisterApi() {
        Request request = WepSerivese.getRetrofit().create(Request.class);
        Call<SignUp> call = request.register(mFname.getText().toString(), mLname.getText().toString(), mEmail.getText().toString(),mPass.getText().toString());
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                Log.e("sucees", response.body().getMsg());


            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Log.e("fial", t.getMessage());
            }
        });
    }


    boolean vaildpasrm() {
        if (mFname.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "please enter uour first name", Toast.LENGTH_LONG).show();
            return false;
        } else if (mLname.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "please enter uour last name", Toast.LENGTH_LONG).show();
            return false;
        } else if (mEmail.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "please enter your email", Toast.LENGTH_LONG).show();
            return false;
        } else if (mPass.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "pleas entr your password ", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }
}
