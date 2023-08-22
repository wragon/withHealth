package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class StaticSaveActivity extends AppCompatActivity {

    EditText edit_change;
    Button btn_save;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference healthref = database.getReference().child("Health");  //firebase 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_save);

        edit_change = findViewById(R.id.edit_change);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getChange = edit_change.getText().toString();

                //hashmap 만들기
                HashMap result = new HashMap<>();
                result.put("change", getChange);

                healthref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i;
                        for(i = 0; i < 100; i++){
                            if(snapshot.child("routine1").child("save2").child("bench").child("weight").child(""+i).exists()){

                            }
                            else{
                                break;
                            }
                        }
                        healthref.child("routine1").child("save2").child("bench").child("weight").child(""+i).setValue(getChange);
                        String data = snapshot.child("routine1").child("save2").child("bench").child("set").getValue(String.class);
                        Toast message = Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG);
                        message.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                finish();
            }
        });
    }
}