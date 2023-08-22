package com.example.my_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

public class YogaActivity extends AppCompatActivity {
    Button btnstep,btnplank,btnboat,btnchair,btnplo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        btnplo=(Button)findViewById(R.id.btnplo);

        btnplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(YogaActivity.this);
                ad.setTitle("플로 자세");
                ad.setMessage("\n1)  바닥에 등을 대고 누워서 시작합니다.\n\n" +
                        "2)  복근에 힘을 주고, 다리를 공중으로 들어 올립니다.\n\n" +
                        "3)  다리를 머리 부분까지 넘기면서 상체를 들어서 발끗이 땅에 닿을 수 있도록 합니다. 이때 무릎은 구부러지지 않도록 최대한 펴주세요.\n\n" +
                        "4)  자세를 유지하며, 10초 이상 버틴 후, 원래 자세로 돌아옵니다.\n\n" +
                        "5)  6회 반복하여 1분 운동합니다.\n" );
                AlertDialog alertDialog=ad.create();
                alertDialog.show();
            }
        });
        btnplank=(Button)findViewById(R.id.btnplank);
        btnplank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(YogaActivity.this);
                ad.setTitle("업워드플랭크 자세");
                ad.setMessage("\n1)  엉덩이로 앉은 후, 두 다리를 뻗고, 팔과 다리로 몸을 지탱합니다.\n\n" +
                        "2)  손바닥과 발바닥으로 지탱하면서 복근과 둔근에 힘을 주고, 골반을 바닥에서 천천히 들어 올리면서 리버스 플랭크 자세를 취한다.\n\n" +
                        "3)  1분 이상 버틴다.\n");
                AlertDialog alertDialog=ad.create();
                alertDialog.show();
            }
        });
        btnchair=(Button)findViewById(R.id.btnchair);
        btnchair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(YogaActivity.this);
                ad.setTitle("체어 자세");
                ad.setMessage("\n1)  바닥에 서서 진행하며 운동하는 내내 복근에 힘을 주고 있어야 합니다.\n\n" +
                        "2)  양손을 천장 방향으로 뻗어 올리면서 동시에 무릎을 살짝 구부리고 엉덩이는 자연스럽게 의자에 앉는 포즈를 진행합니다.\n\n" +
                        "3)  자세를 유지하며, 10회 이상 호흡하고 기본자세로 돌아옵니다.\n\n" +
                        "4)  1분 동안 반복합니다.\n");
                AlertDialog alertDialog=ad.create();
                alertDialog.show();
            }
        });
        btnstep=(Button)findViewById(R.id.btnstep);
        btnstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(YogaActivity.this);
                ad.setTitle("포림드스텝 자세");
                ad.setMessage("\n1)  바닥에 엎드려서 손바닥이 어깨 밑에 위치하도록 하고, 손가락은 앞 방향을 가리키고 있는 상태로 시작합니다.\n\n" +
                        "2)  팔꿈치를 구부리며, 가슴을 바닥 쪽으로 내리면서 어깨와 팔꿈치가 일직선이 되도록 합니다.\n\n" +
                        "3)  자세를 10초 이상 버틴 후, 원래자세로 돌아옵니다.\n\n" +
                        "4)  6회 반복하여 1분 운동합니다.\n");
                AlertDialog alertDialog=ad.create();
                alertDialog.show();
            }
        });
        btnboat=(Button)findViewById(R.id.btnboat);
        btnboat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(YogaActivity.this);
                ad.setTitle("보트 자세");
                ad.setMessage("\n1)  바닥에 엉덩이를 대고 앉아 다리를 뻗습니다.\n\n" +
                        "2)  손은 엉덩이 뒤에 가져다 대고, 몸을 서서히 뒤로 젖힌 후, 다리를 공중으로 들어줍니다.\n\n" +
                        "3)  다리를 45도 각도로 만들고, V자 자세를 유지합니다.\n\n" +
                        "4)  손은 앞으로 뻗어서 복근희 힘으로 버티고, 10회 정도 호흡 후 기본자세로 돌아옵니다.\n\n" +
                        "5)  1분 동안 운동합니다.\n");
                AlertDialog alertDialog=ad.create();
                alertDialog.show();
            }
        });


    }
    }