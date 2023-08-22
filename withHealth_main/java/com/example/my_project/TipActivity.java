package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TipActivity extends AppCompatActivity {

    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;
    TextView gym,running,yoga,bike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        //홈버튼에서 각 버튼으로
        staticButton = findViewById(R.id.staticButton);
        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent( TipActivity.this, StaticActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //homeActivity로 전환
                Intent i = new Intent( TipActivity.this, HomeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        groupButton = findViewById(R.id.groupButton);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //groupActivity로 전환
                Intent i = new Intent( TipActivity.this, GroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileActivity로 전환
                Intent i = new Intent( TipActivity.this, ProfileActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        gym = findViewById(R.id.gym);
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gymActivity로 전환
                Intent i = new Intent( TipActivity.this, GymActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        running = findViewById(R.id.running);
        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gymActivity로 전환
                Intent i = new Intent( TipActivity.this, RunActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        yoga = findViewById(R.id.yoga);
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gymActivity로 전환
                Intent i = new Intent( TipActivity.this, YogaActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
        bike = findViewById(R.id.bike);
        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gymActivity로 전환
                Intent i = new Intent( TipActivity.this, BikeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
    }
}