package com.example.viewmodel.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewmodel.R;
import com.example.viewmodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MovieView  {

Button mButton;
TextView mTextView;
MoviePresenter moviePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mButton=findViewById(R.id.button);
         mTextView=findViewById(R.id.text);
         moviePresenter=new MoviePresenter(this);
         mButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                moviePresenter.getMovieName();
                break;
        }
    }

    @Override
    public void onGetMovieName(String name) {
        mTextView.setText(name);
    }
}
