package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BackActivity extends AppCompatActivity {
    ImageButton pullup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        pullup=(ImageButton)findViewById(R.id.pullup);
        pullup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BackActivity.this, PullupActivity.class);
                startActivity(i);
            }
        });
    }
}