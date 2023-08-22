package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RunActivity extends AppCompatActivity {
    ImageButton nike,trainning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);


        nike = findViewById(R.id.nike);
        nike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://static-breeze.nike.co.kr/kr/ko_kr/cmsstatic/structured-content/1151/NRC-Ready-Set-Go_Guide.pdf"));
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        trainning = findViewById(R.id.trainning);
        trainning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.marathon.pe.kr/training/beginnertraining.html"));
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
    }
}