package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.MediaRouteButton;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks{
private static final int Galery=1;
private static final int Camera=2;

    EditText mEmail,mPassword;
    Button mSave,mSlect;
    UserData userData;
    ImageView mUserImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.pass);
        mUserImage=findViewById(R.id.image_user);
        mSlect=findViewById(R.id.slect);

        mSave=findViewById(R.id.save_butt);
        mSave.setOnClickListener(this);
        mSlect.setOnClickListener(this);
        userData=new UserData(EditProfileActivity.this);
        getData();
    }

    @Override
    public void onClick(View v) {
switch (v.getId()) {
    case R.id.save_butt:
        if (checkData()) {
            userData.saveData(mEmail.getText().toString(), mEmail.getText().toString(), true);
        }
        break;
    case R.id.slect:
        showSlectionDaligo();

}
    }
    void getData(){
        mEmail.setText(userData.getData().get(userData.KEY_USER_EMAIL));
        mPassword.setText(userData.getData().get(userData.KEY_PASS));
    }
    public boolean checkData(){
        if (mEmail.getText().toString().isEmpty()){
            Toast.makeText(EditProfileActivity.this,"please enter your email",Toast.LENGTH_LONG).show();
            return false;
        }
        else  if (mPassword.getText().toString().isEmpty()){
            Toast.makeText(EditProfileActivity.this,"please enter your password",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }
    }
    private void showSlectionDaligo() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

//set title
        alertDialog.setTitle("Slect Image");

        String[] DailogItems = {"Select Photo From Galery", "Select Photo From Camera"};
        alertDialog.setItems(DailogItems, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        choosePhotoFromGalery();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }




        });
        alertDialog.show();
    }

@AfterPermissionGranted(101)
    public void  choosePhotoFromGalery(){

        String[] galleryPrimision =new String[0] ;
        if (android.os.Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            galleryPrimision=new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
           if (EasyPermissions.hasPermissions(this,galleryPrimision)){
            Intent galleryIntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,Galery);
        }
            else{
                EasyPermissions.requestPermissions(this,"acess for Storage",101,galleryPrimision);
        }
    }
    @AfterPermissionGranted(123)
  public void   takePhotoFromCamera(){

        String[] galleryPrimision =new String[0] ;
        if (android.os.Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            galleryPrimision=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
        if (EasyPermissions.hasPermissions(this,galleryPrimision)){
            Intent galleryIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(galleryIntent,Camera);
        }
        else{
            EasyPermissions.requestPermissions(this,"acess for Storage",123,galleryPrimision);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==this.RESULT_CANCELED){
            return;
        }if (requestCode==Galery){
            if(data!=null){
                Uri contentUri=data.getData();
                try{
                    Bitmap bitmapGallery=MediaStore.Images.Media.getBitmap(this.getContentResolver(),contentUri);
                    mUserImage.setImageBitmap(bitmapGallery);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }else if(requestCode==Camera){
                Bitmap bitmapCamera=(Bitmap) data.getExtras().get("data");
                mUserImage.setImageBitmap(bitmapCamera);
            }
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
        if (grantResults.length>0){
            if (grantResults.toString().equals(Galery)){
                Intent galleryIntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,Galery);
            }
        }else if(grantResults.toString().equals(Camera)){
            Intent galleryIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(galleryIntent,Camera);
        }
    }


}


