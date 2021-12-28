package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    LottieAnimationView lottieAnimationView;
    SwipeButton mSwipeButton;
    TextView mCount;
    int Counter=0;
    UserData userData;
    ImageView mOpenWindow;
    PopupWindow mPopupWindow=null;
    LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initiViews();

    }

    private void initiViews() {
        lottieAnimationView=findViewById(R.id.animationView);
        mSwipeButton=findViewById(R.id.swipe_btn);
        mOpenWindow=findViewById(R.id.open_window);
        mCount=findViewById(R.id.count);
        userData=new UserData(this);
        lottieAnimationView.pauseAnimation();
        Counter=Integer.parseInt(userData.getCount());
        mCount.setText(String.valueOf(Counter));
        mOpenWindow.setOnClickListener(this);
        lottieAnimationView.setOnClickListener(this);
        mSwipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override

            public void onStateChange(boolean active) {
                Counter=0;
                mCount.setText(String.valueOf(Counter));
                userData.saveCounter(String.valueOf(Counter));

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animationView:
                lottieAnimationView.playAnimation();
                Counter++;
                mCount.setText(String.valueOf(Counter));
                userData.saveCounter(String.valueOf(Counter));
                break;
               case R.id.open_window:
          intiPoupwindow();

        }
    }
    private PopupWindow intiPoupwindow(){
try {
    mLayoutInflater=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View layout=mLayoutInflater.inflate(R.layout.raw_layout,null);
    final TextView edtitprofile=layout.findViewById(R.id.Edit);
    edtitprofile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i =new Intent(HomeActivity.this,EditProfileActivity.class);
            startActivity(i);
        }
    });
    final TextView ourLocation=layout.findViewById(R.id.location);
    ourLocation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i =new Intent(HomeActivity.this,MapsActivity.class);
            startActivity(i);
        }
    });
    final TextView find_us=layout.findViewById(R.id.find);
    find_us.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    final TextView logOut=layout.findViewById(R.id.log);
    logOut.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
userData.logOut();
        }
    });
    layout.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
    float scale=getResources().getDisplayMetrics().density;
    mPopupWindow=new PopupWindow(layout,(int)(150*scale),(int)(190*scale),true);
    Drawable bakground=getResources().getDrawable(R.drawable.pupo_shape);
    mPopupWindow.setBackgroundDrawable(bakground);
    mPopupWindow.showAsDropDown(mOpenWindow,5,6);
}catch (Exception e){
    e.printStackTrace();
}return  mPopupWindow;


    }
}
