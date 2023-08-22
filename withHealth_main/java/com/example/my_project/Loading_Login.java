package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Loading_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading__login);

    }
    public void turnpage(View v){
                //tipActivity로 전환
                Intent i = new Intent( Loading_Login.this, Login.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
}