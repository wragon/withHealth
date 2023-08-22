package com.example.my_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;


public class MakeGroupActivity extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView ProfileImage;

    private ImageButton makegroupButton;
    private ImageButton CancleButton;

    private EditText editgroupname;
    private EditText editgroupinfo;

    private StorageReference mStorageRef;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("GroupAll");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_group);

        ProfileImage = (ImageView)findViewById(R.id.ProfileImage);
        ImageButton findimagebutton = (ImageButton) findViewById(R.id.findprofileimage);
        editgroupname = findViewById(R.id.editGroupName);
        editgroupinfo = findViewById(R.id.EditGroupInfo);
        findimagebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference();

        //화면전환
        CancleButton = findViewById(R.id.canclebutton);
        CancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent( MakeGroupActivity.this, GroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            final Uri selectedImageUri = data.getData();
            ProfileImage.setImageURI(selectedImageUri);

            makegroupButton = findViewById(R.id.MakeGroup);
            makegroupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


//            Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                        Task<Uri> downloaduri;
                        StorageReference groupProfileRef = mStorageRef.child("images");
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int i;
                            for(i = 1; i < 100; i++){
                                if(snapshot.child("group_" + i).exists()){
                                }
                                else {
                                    break;
                                }
                            }
                            final DatabaseReference grRef = myRef.child("group_" + i);
                            grRef.child("groupname").setValue(editgroupname.getText().toString());
                            grRef.child("groupinfo").setValue(editgroupinfo.getText().toString());
                            groupProfileRef = mStorageRef.child("images/group" + i + ".jpg");
                            groupProfileRef.putFile(selectedImageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
//                                            downloaduri = groupProfileRef.getDownloadUrl();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            // ...
                                        }
                                    });
                            grRef.child("profile").setValue(groupProfileRef.getDownloadUrl().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//                        StorageReference checkRef = mStorageRef.child("images/group" + i + ".jpg");




                    //staticActivity로 전환
                    Intent i = new Intent( MakeGroupActivity.this, GroupActivity.class);
                    startActivity(i);//지정해 놓은 페이지로 화면 전환
                    Toast.makeText(getApplicationContext(), "그룹이 생성되었습니다!", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}

