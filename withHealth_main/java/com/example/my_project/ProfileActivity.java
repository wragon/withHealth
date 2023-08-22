package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {
    TextView nickname, btnheight, btnweight, textbmi;
    ImageButton editnick;
    EditText Edtname, Edtheight, Edtweight;
    View dialogView;
    Button btnsex, btnbirth,bmi;
    DatePickerDialog.OnDateSetListener callbackMethod;

    //firebase
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userref = database.getReference().child("User");


    //homebutton
    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;
    double height=0,weight=0;
    double Bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.InitializeView();
        this.InitializeListener();


        //홈버튼에서 각 버튼으로
        tipButton = findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tipActivity로 전환
                Intent i = new Intent(ProfileActivity.this, TipActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        staticButton = findViewById(R.id.staticButton);
        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent(ProfileActivity.this, StaticActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //homeActivity로 전환
                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        groupButton = findViewById(R.id.groupButton);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //groupActivity로 전환
                Intent i = new Intent(ProfileActivity.this, GroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });
        nickname = (TextView) findViewById(R.id.nickname);
        editnick = (ImageButton) findViewById(R.id.editnick);
        btnsex = (Button) findViewById(R.id.btnsex);
        btnheight = (TextView) findViewById(R.id.btnheight);
        btnweight = (TextView) findViewById(R.id.btnweight);
        btnbirth=(Button)findViewById(R.id.btnbirth);
        userref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser check = FirebaseAuth.getInstance().getCurrentUser();
                String uidck = check.getUid();
                if(snapshot.child(uidck).exists()){
                    nickname.setText(snapshot.child(uidck).child("nickName").getValue(String.class));
                    btnsex.setText(snapshot.child(uidck).child("gender").getValue(String.class));
                    btnbirth.setText(snapshot.child(uidck).child("birth").getValue(String.class));
                    btnheight.setText(String.format("%scm", snapshot.child(uidck).child("height").getValue(String.class)));
                    btnweight.setText(String.format("%skg", snapshot.child(uidck).child("weight").getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editnick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogView = (View) View.inflate(ProfileActivity.this, R.layout.prof, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(ProfileActivity.this);
                dlg.setTitle("닉네임 설정");
                dlg.setIcon(R.drawable.profile_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Edtname = (EditText) dialogView.findViewById(R.id.Edtnick);
                        nickname.setText(Edtname.getText().toString());
                        userref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();
                                if(snapshot.child(uid).exists()){
                                    userref.child(uid).child("nickName").setValue(Edtname.getText().toString());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        btnsex.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String[] items = new String[]{"남자", "여자", "표기 안함"};
                final int[] selectedindex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
                dialog.setTitle("성별을 선택하세요.");
                dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedindex[0] = i;
                    }
                });
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btnsex.setText("남자");
                        userref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();
                                if(snapshot.child(uid).exists()){
                                    userref.child(uid).child("gender").setValue(btnsex.getText().toString());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }).create().show();
            }
        });

        btnheight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogView = (View) View.inflate(ProfileActivity.this, R.layout.profile_height, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(ProfileActivity.this);
                dlg.setTitle("키를 입력하세요");
                dlg.setIcon(R.drawable.profile_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Edtheight = (EditText) dialogView.findViewById(R.id.Edtheight);
                        btnheight.setText(Edtheight.getText().toString() + "cm");
                        height=Double.parseDouble(Edtheight.getText().toString());
                        userref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();
                                if(snapshot.child(uid).exists()){
                                    userref.child(uid).child("height").setValue(Edtheight.getText().toString());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        btnweight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogView = (View) View.inflate(ProfileActivity.this, R.layout.profile_weight, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(ProfileActivity.this);
                dlg.setTitle("몸무게를 입력하세요");
                dlg.setIcon(R.drawable.profile_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Edtweight = (EditText) dialogView.findViewById(R.id.Edtweight);
                        btnweight.setText(Edtweight.getText().toString() + "kg");
                        weight=Double.parseDouble(Edtweight.getText().toString());
                        userref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();
                                if(snapshot.child(uid).exists()){
                                    userref.child(uid).child("weight").setValue(Edtweight.getText().toString());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        bmi = (Button) findViewById(R.id.bmi);
        textbmi=(TextView)findViewById(R.id.textbmi);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bmi=weight/(height/100)/(height/100);
                if (Bmi <= 15.0) {
                    textbmi.setText("당신은 심각한 저체중으로 사망위험도는 2.76입니다.");
                }
                if (Bmi > 15.0 && Bmi <= 20.0) {
                    textbmi.setText("당신은 저체중과 정상의 중간경계로 사망위험도는 1.35입니다.");
                }
                if (Bmi > 20.0 && Bmi <= 22.6) {
                    textbmi.setText("당신은 정상체중이며 사망위험도는 1.09입니다.");
                }
                if (Bmi > 22.6 && Bmi <= 30.0) {
                    textbmi.setText("단신은 정상과 과체중의 중간경계로 사망위험도는 1.03입니다.");
                }
                if (Bmi > 30.0) {
                    textbmi.setText("단신은 고도비만으로 사망위험도는 1.5입니다.");
                }
            }
        });
    }
    public void InitializeView(){
        btnbirth=(Button)findViewById(R.id.btnbirth);
    }
    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayofMonth) {
                btnbirth.setText(year + "/" + monthOfYear + "/" + dayofMonth);
                userref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();
                        if(snapshot.child(uid).exists()){
                            userref.child(uid).child("birth").setValue(btnbirth.getText().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        };
    }
        public void OnClickHandler(View view){
            DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);
            dialog.show();
        }
    }