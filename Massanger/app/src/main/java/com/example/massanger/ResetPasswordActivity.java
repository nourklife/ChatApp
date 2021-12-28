package com.example.massanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.massanger.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
EditText mSend_email;
Button mBetn_send;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSend_email=findViewById(R.id.text_send);
       mBetn_send=findViewById(R.id.Reset);
       mBetn_send.setOnClickListener(this);
       firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Reset:
                String email=mSend_email.getText().toString();
                if (email.equals("")){
                    Toast.makeText(ResetPasswordActivity.this,"please enter your email",Toast.LENGTH_LONG).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this,"please enter your email",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
                            }else {
                                String error=task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this,error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
        }
    }
}
