package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignupActivity extends AppCompatActivity {
    //id전역변수
    public static Context context_id;
    public String myID;

    private Button backbtn;
    private ImageButton signupbtn;

    private EditText joinid;
    private EditText joinemail;
    private EditText joinpw;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userref = database.getReference().child("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        joinid = findViewById(R.id.joinID);
        joinemail = findViewById(R.id.joinEmail);
        joinpw = findViewById(R.id.JoinPW);

        context_id = this;

        signupbtn = findViewById(R.id.JoinButton);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i;
                        for(i = 1; i < 100; i ++){
                            if (snapshot.child("User" + i).exists()) {
                            } else {
                                break;
                            }
                        }
                        DatabaseReference User_ref = userref.child("User" + i);
                        User_ref.child("id").setValue(joinid.getText().toString());
                        User_ref.child("email").setValue(joinemail.getText().toString());
                        User_ref.child("pw").setValue(joinpw.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                Toast.makeText(getApplicationContext(), myID, Toast.LENGTH_LONG);
                Intent i = new Intent( SignupActivity.this, Login.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });


        backbtn = findViewById(R.id.subackbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tipActivity로 전환
                Intent i = new Intent( SignupActivity.this, Login.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });
    }
}