package com.example.facbook.UI.MAIN;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.facbook.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
TextView mCount,mToast;
Button mSet;
int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mSet=findViewById(R.id.button_count);
        mCount=findViewById(R.id.show_count);
        mSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_count:
                count++;
                mCount.setText(Integer.toString(count));
        }
    }
}
