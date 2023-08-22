package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupActivity extends AppCompatActivity {

    Button btn_g_p1;
    Button MakingGroupButton;
    Button mygroup1, mygroup2, mygroup3, mygroup4;

    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GroupAll> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private CustomAdapter_main cuadapter;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //Initialize firebase auth
        mAuth = FirebaseAuth.getInstance();

        //홈버튼에서 각 버튼으로
        tipButton = findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tipActivity로 전환
                Intent i = new Intent( GroupActivity.this, TipActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        staticButton = findViewById(R.id.staticButton);
        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent( GroupActivity.this, StaticActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //homeActivity로 전환
                Intent i = new Intent( GroupActivity.this, HomeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileActivity로 전환
                Intent i = new Intent( GroupActivity.this, ProfileActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        MakingGroupButton = findViewById(R.id.MakingGroup);
        MakingGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, MakeGroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        mygroup1 = findViewById(R.id.group_icon1);
        mygroup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup2 = findViewById(R.id.group_icon2);
        mygroup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup3 = findViewById(R.id.group_icon3);
        mygroup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup4 = findViewById(R.id.group_icon4);
        mygroup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

//        init();
//
//        getData();

//        Button.OnClickListener btnListener = new View.OnClickListener(){
//            public void onClick(View v){
//                switch (v.getId()){
//                    case R.id.group_icon3:
//                        Intent intent = new Intent(GroupActivity.this, find_group_popup1.class);
//                        startActivityForResult(intent, 1);
//
//                        break;
//                }
//            }
//        };
//        btn_g_p1 = (Button)findViewById(R.id.group_icon3);
//        btn_g_p1.setOnClickListener(btnListener);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //Group 객체를 담을 array list(어뎁터쪽으로)

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("GroupAll"); // DB테이블 연결;
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Firebase Database의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재 하지 않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){ // 반복문으로 데이터 List를 추출해냄
                    GroupAll group = snapshot.getValue(GroupAll.class); // 만들어두었던 Groupall객체에 데이터를 담는다.
                    arrayList.add(group); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰에 보낼 준비
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //DB를 가져오던 중 에러 발생시
                Log.e("GroupActivity", String.valueOf(databaseError.toException())); //에러문 출력
            }

        });

        adapter = new CustomAdapter_main(arrayList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어뎁터 연결

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cuadapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
//
//
//    private void init() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        adapter = new RecyclerAdapter();
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void getData() {
//        // 임의의 데이터입니다.
//        List<String> listTitle = Arrays.asList("국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립",
//                "국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립");
//        List<String> listContent = Arrays.asList(
//                "이 꽃은 국화입니다.",
//                "여기는 사막입니다.",
//                "이 꽃은 수국입니다.",
//                "이 동물은 해파리입니다.",
//                "이 동물은 코알라입니다.",
//                "이것은 등대입니다.",
//                "이 동물은 펭귄입니다.",
//                "이 꽃은 튤립입니다.",
//                "이 꽃은 국화입니다.",
//                "여기는 사막입니다.",
//                "이 꽃은 수국입니다.",
//                "이 동물은 해파리입니다.",
//                "이 동물은 코알라입니다.",
//                "이것은 등대입니다.",
//                "이 동물은 펭귄입니다.",
//                "이 꽃은 튤립입니다."
//        );
//        List<Integer> listResId = Arrays.asList(
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon,
//                R.drawable.home_icon
//        );
//        for (int i = 0; i < listTitle.size(); i++) {
//            // 각 List의 값들을 data 객체에 set 해줍니다.
//            Data data = new Data();
//            data.setTitle(listTitle.get(i));
//            data.setContent(listContent.get(i));
//            data.setResId(listResId.get(i));
//
//            // 각 값이 들어간 data를 adapter에 추가합니다.
//            adapter.addItem(data);
//        }
//
//        // adapter의 값이 변경되었다는 것을 알려줍니다.
//        adapter.notifyDataSetChanged();
//    }
//}