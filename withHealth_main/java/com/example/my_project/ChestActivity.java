package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChestActivity extends AppCompatActivity {
    ImageButton bench,incline,decline,dumbel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);

        bench=(ImageButton)findViewById(R.id.bench);
        bench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=0DsXTSHo3lU"));
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        incline=(ImageButton)findViewById(R.id.incline);
        incline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4HvI_mFhzVQ"));
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        decline=(ImageButton)findViewById(R.id.decline);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=jYgpC5pFb20"));
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        dumbel=(ImageButton)findViewById(R.id.dumbel);
        dumbel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ytwcHX_Yvkc"));
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
    }
}