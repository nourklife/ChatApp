package com.example.massanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.massanger.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button mLogin,mSign;
FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin=findViewById(R.id.login);
        mSign=findViewById(R.id.register);
        mSign.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        }



    @Override
    public void onClick(View v) {
     switch (v.getId()){
    case R.id.login:
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        break;
    case R.id.register:
        Intent intent1=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent1);
        break;
}
    }


}
