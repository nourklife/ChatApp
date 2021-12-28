package com.example.massanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEmail,mPassword;
    Button mLogin;
    FirebaseAuth auth;
    TextView mReset;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.useremail);
        mPassword=findViewById(R.id.userpassword);
        mLogin=findViewById(R.id.login);
        mReset=findViewById(R.id.forget);
      Toolbar toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLogin.setOnClickListener(this);
        mReset.setOnClickListener(this);
        auth=FirebaseAuth.getInstance();

    }



    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.login:
        String text_email=mEmail.getText().toString();
        String text_pass=mPassword.getText().toString();
        if (TextUtils.isEmpty(text_email) ||TextUtils.isEmpty(text_pass)){
            Toast.makeText(LoginActivity.this,"All fields are required",Toast.LENGTH_LONG).show();

        }else {
            auth.signInWithEmailAndPassword(text_email,text_pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"authentication is failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        break;
    case R.id.forget:
        Intent intent=new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
        break;
    default:
        throw new IllegalStateException("Unexpected value: " + v.getId());
}
    }
}
