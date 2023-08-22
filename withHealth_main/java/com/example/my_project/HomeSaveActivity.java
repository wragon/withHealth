package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;

public class HomeSaveActivity extends AppCompatActivity {
    HealthAdapter adapter;
    EditText edit_title, edit_set, edit_weight;
    Button btn_save;
    String title = "";
    String set = "";
    String weight = "";
    int r_cnt = 1;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference healthref = database.getReference().child("Health");  //firebase 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_save);

        edit_title = findViewById(R.id.edit_title);
        edit_set = findViewById(R.id.edit_set);
        edit_weight = findViewById(R.id.edit_weight);
        btn_save = findViewById(R.id.btn_save);
        adapter = new HealthAdapter();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getTitle = edit_title.getText().toString();
                final String getSet = edit_set.getText().toString();
                final String getWeight = edit_weight.getText().toString();

                //hashmap 만들기
                HashMap result = new HashMap<>();
                result.put("title", getTitle);
                result.put("set", getSet);
                result.put("weight", getWeight);

                healthref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i;
                        for(i = 0; i < 100; i++){
                            if(snapshot.child("routine1").child("save" + i).exists()){

                            }
                            else{
                                break;
                            }
                        }
                        writeNewUser("save" + i, getTitle, getSet, getWeight);
                        healthref.child("routine" + r_cnt).child("save" + i).child(getTitle).child("weight").child(""+i).setValue(getWeight);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                finish();
            }
        });

    }

    private void writeNewUser(String userId, String title, String set, String weight) {
        HealthData user = new HealthData(set, weight);

        healthref.child("routine" + r_cnt).child(userId).child(title).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(HomeSaveActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(HomeSaveActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void readUser(){
        healthref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i;
                for(i = 0; i < 100; i++){
                    if(snapshot.child("routine1").child("save" + i).exists()){

                    }
                    else{
                        break;
                    }
                }
                int j = i-1;
                title = snapshot.child("routine1").child("save" + j).child("title").getValue(String.class);
                set = snapshot.child("routine1").child("save" + j).child("set").getValue(String.class);
                weight = snapshot.child("routine1").child("save" + j).child("weight").getValue(String.class);
                //adapter.addItem(title, set + "set", weight + "kg");
                Toast.makeText(HomeSaveActivity.this, set, Toast.LENGTH_SHORT).show();
                //adapter.notifyDataSetChanged();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}