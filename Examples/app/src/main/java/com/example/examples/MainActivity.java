package com.example.examples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button button;
EditText mE1,mE2;
ImageButton mButton;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","Hello world");
        mE1=findViewById(R.id.ed1);
        mE2=findViewById(R.id.ed2);
        textView=findViewById(R.id.top_message);
        registerForContextMenu(mE1);
        registerForContextMenu(mE2);
         mButton = findViewById(R.id.buttonPanel);
        mButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optional_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.set:
            Toast.makeText(this,"settings",Toast.LENGTH_LONG).show();
                break;
            case R.id.status:
                Toast.makeText(this,"stauts",Toast.LENGTH_LONG).show();
                break;
            case R.id.speek:
                Toast.makeText(this,"microphone",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_ed1,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ed1:
                Toast.makeText(this,"mode ED1",Toast.LENGTH_LONG).show();
                textView.setText("NOUR");
                break;
            case R.id.ed2:
                Toast.makeText(this,"mode ED2",Toast.LENGTH_LONG).show();
                textView.setText("ESLAM");
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPanel:
                PopupMenu popup = new PopupMenu(MainActivity.this, mButton);
                popup.getMenuInflater().inflate(
                        R.menu.optional_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.set:
                                        Toast.makeText(MainActivity.this,"NOUR",Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.status:
                                        Toast.makeText(MainActivity.this,"stauts2",Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.speek:
                                        Toast.makeText(MainActivity.this,"microphone3",Toast.LENGTH_LONG).show();
                                        break;
                                }
                                return false;
                            }
// implement click listener
                        });
                popup.show();
        }

    }
}
