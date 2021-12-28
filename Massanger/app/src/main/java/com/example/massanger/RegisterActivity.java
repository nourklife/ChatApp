package com.example.massanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEmail,mPassword,mUsername;
    Button mRegister;
    FirebaseAuth auth;
    DatabaseReference databaseReference;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail=findViewById(R.id.useremail);
        mPassword=findViewById(R.id.userpassword);
        mRegister=findViewById(R.id.register);
        mUsername=findViewById(R.id.username);
       Toolbar toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRegister.setOnClickListener(this);
        auth=FirebaseAuth.getInstance();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                String text_email = mEmail.getText().toString();
                String text_pass = mPassword.getText().toString();
                String text_name = mUsername.getText().toString();
                if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_pass) || TextUtils.isEmpty(text_name)) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
                }else if (text_pass.length()<6){
                    Toast.makeText(RegisterActivity.this, " password must be at least 6 characters ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    register(text_name,text_email,text_pass);
                }
                break;
        }
    }
    public void register(final String username, String email, String pass){
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid=firebaseUser.getUid();
                            databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            HashMap<String, String> hashMap=new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("imageUrl","default");
                            hashMap.put("status","offline");
                            hashMap.put("search",username.toLowerCase());
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"you cant reigster with this email or password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
