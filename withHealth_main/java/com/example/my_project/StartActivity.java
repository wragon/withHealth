package com.example.my_project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.TabHost;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StartActivity extends AppCompatActivity {

    private Button mStartBtn, mStopBtn, mPauseBtn,mRestBtn;
    private TextView mTimeTextView,rest_timer,bmi_text,popup_text;
    private ImageButton sound_on,sound_off;
    private Thread timeThread = null;
    private Boolean isRunning = true;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");


    private MediaPlayer mp;
    private Dialog dialog;
    public static Context context_main;
    public AlertDialog alertconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mStartBtn = findViewById(R.id.b_play);
        mStopBtn = findViewById(R.id.b_reset);
        mPauseBtn = findViewById(R.id.b_pause);
        mRestBtn = findViewById(R.id.b_rest);
        rest_timer = findViewById(R.id.rest_timer);
        mTimeTextView = findViewById(R.id.timer_text);
        sound_off=findViewById(R.id.sound_off);
        sound_on=findViewById(R.id.sound_on);
        bmi_text=findViewById(R.id.BMI_text);
        final AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);

        mp = MediaPlayer.create(this,R.raw.song);
        mp.start();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#4ea1d3"));
        }

        TabHost tabHost2=(TabHost) findViewById(R.id.tabHost2);
        tabHost2.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost2.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.tab1) ;
        ts1.setIndicator("Tab 1") ;
        tabHost2.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost2.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.tab2) ;
        ts2.setIndicator("Tab 2") ;
        tabHost2.addTab(ts2) ;

        sound_off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                sound_on.setVisibility(View.VISIBLE);
                mp.pause();//노래도 정지
            }
        });

        sound_on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mp.start();
                view.setVisibility(View.INVISIBLE);
                sound_off.setVisibility(View.VISIBLE);
            }
        });



        mRestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRestBtn.setVisibility(View.INVISIBLE);
                rest_timer.setVisibility(View.VISIBLE);
                CountDownTimer countDownTimer = new CountDownTimer(30000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        rest_timer.setText(String.format(Locale.getDefault(),"%d",millisUntilFinished/1000L));
                    }

                    @Override
                    public void onFinish() {
                        rest_timer.setText("30");
                        rest_timer.setVisibility(View.INVISIBLE);
                        mRestBtn.setVisibility(View.VISIBLE);
                        alert_confirm.setMessage("휴식시간이 끝났습니다!");
                        final AlertDialog alert = alert_confirm.create();
                        alert.setIcon(R.drawable.ic_launcher_foreground);
                        alert.setTitle("WithHealth");
                        alert.show();
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(new Runnable()  {
                            public void run() {
                                alert.dismiss();
                            }
                        }, 1000); // 0.75초후

                    }
                }.start();

            }
        });

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setVisibility(View.GONE);
                mPauseBtn.setVisibility(View.VISIBLE);
                timeThread = new Thread(new timeThread());
                timeThread.start();
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStartBtn.setVisibility(View.VISIBLE);
                mPauseBtn.setVisibility(View.GONE);
                mTimeTextView.setText("00:00:00:00");
                timeThread.interrupt();
            }
        });

        mPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = !isRunning;
                mPauseBtn.setText(isRunning ? "일시정지" : "시작");
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent( StartActivity.this, HomeActivity.class);
        startActivity(i);//지정해 놓은 페이지로 화면 전환
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;
            int hour = (msg.arg1 / 100) / 360;
            //1000이 1초 1000*60 은 1분 1000*60*10은 10분 1000*60*60은 한시간

            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d:%02d", hour, min, sec, mSec);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            mTimeTextView.setText(result);
            DatabaseReference grRef = myRef.child(uid);
            grRef.child("laptime").setValue(mTimeTextView.getText().toString());
        }
    };
    protected void onDestroy(){ //노래가 너무 용량 차지하지 않게 하려고 만든 메소드
        super.onDestroy();

        if(mp != null){
            mp.release();
            mp=null;
        }
    }

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                mTimeTextView.setText("");
                                mTimeTextView.setText("00:00:00:00");
                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }
}