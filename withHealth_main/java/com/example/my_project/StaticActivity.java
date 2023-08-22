package com.example.my_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StaticActivity extends AppCompatActivity {

    LineChart chart;
    ListView my_listview;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference healthref = database.getReference().child("Health");  //firebase 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        //홈버튼에서 각 버튼으로
        tipButton = findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TipActivity로 전환
                Intent i = new Intent( StaticActivity.this, TipActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeActivity로 전환
                Intent i = new Intent( StaticActivity.this, HomeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        groupButton = findViewById(R.id.groupButton);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //groupActivity로 전환
                Intent i = new Intent( StaticActivity.this, GroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileActivity로 전환
                Intent i = new Intent( StaticActivity.this, ProfileActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });


        //탭
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("Routine 1") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("Routine 2") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("Routine 3") ;
        tabHost1.addTab(ts3) ;


        //리스트뷰
        items = new ArrayList<String>();
        items.add("pullup");
        items.add("dumbbell");
        items.add("bench");
        items.add("barbell");
        adapter = new ArrayAdapter<String>(StaticActivity.this, android.R.layout.simple_list_item_single_choice, items);

        my_listview = (ListView) findViewById(R.id.my_listview);
        my_listview.setAdapter(adapter);
        my_listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



    }
    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.save:                                 // ADD 버튼 클릭시
                int pos = my_listview.getCheckedItemPosition(); // 현재 선택된 항목의 첨자(위치값) 얻기
                if (pos != ListView.INVALID_POSITION) {      // 선택된 항목이 있으면
                    Intent i = new Intent(StaticActivity.this, StaticSaveActivity.class);
                    startActivity(i); //지정해 놓은 페이지로 화면 전환

                    my_listview.clearChoices();                 // 선택 해제
                    adapter.notifyDataSetChanged();
                    // 어답터와 연결된 원본데이터의 값이 변경된을 알려 리스트뷰 목록 갱신
                }
                break;
            case R.id.show:                             // DELETE 버튼 클릭시
                chart = findViewById(R.id.chart);
                final ArrayList<Entry> values = new ArrayList<>();
                healthref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String xdata = "";
                        //data = snapshot.child("routine1").child("save2").child("bench").child("weight").child("0").getValue(String.class);
                        //Toast message = Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG);
                        //message.show();
                        long cnt = snapshot.child("routine1").child("save2").child("bench").child("weight").getChildrenCount();
                        int i;
                        for (i = 0; i < cnt; i++) {
                            xdata = snapshot.child("routine1").child("save2").child("bench").child("weight").child(""+i).getValue(String.class);
                            int val = Integer.parseInt(xdata);
                            values.add(new Entry(i, val));
                        }

                        LineDataSet set1;
                        set1 = new LineDataSet(values, "bench");

                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set1); // add the data sets

                        // create a data object with the data sets
                        LineData data = new LineData(dataSets);

                        // black lines and points
                        set1.setColor(Color.parseColor("#4D61CF"));
                        set1.setCircleColor(Color.parseColor("#4D61CF"));

                        // set data
                        chart.setData(data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                break;
        }
    }

}