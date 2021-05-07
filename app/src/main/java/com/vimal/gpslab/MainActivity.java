package com.vimal.gpslab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    CircleImageView circleImage;
    private EditText Name,Phone,Address,LicenceNo;
     RadioButton selection;
    RadioGroup group;
    Uri imageuri,imageLic,finalimg;
    Integer select;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference databaseReference;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleImage= findViewById(R.id.profile_image);
        Name=findViewById(R.id.editTextTextPersonName);
        Phone=findViewById(R.id.Phone);
        Address=findViewById(R.id.Address);
        group=findViewById(R.id.radiogrp);
        LicenceNo=findViewById(R.id.drivingLic);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Details");







    }
    public void selectImage(View view){
        choosepicture(1);
    }
    public void uploadLicence(View v){
        choosepicture(2);
    }
    public void choosepicture(int s){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
        select=s;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            if(select==1){
                imageuri=data.getData();
                circleImage.setImageURI(imageuri);
            }
            else{
                imageLic=data.getData();

            }

        }
    }


    //Send Button
    public void sendData(View v){
        UploadImage(imageLic);
        UploadImage(imageuri);
        insertData();
        String contact=Phone.getText().toString();
        Intent i=new Intent(MainActivity.this,UserDetails.class);
        i.putExtra("contactNo",contact);
        startActivity(i);
        finish();






    }

    private void insertData() {
        String name=Name.getText().toString();
        String mobile=Phone.getText().toString();
        String address=Address.getText().toString();
        selection=findViewById(group.getCheckedRadioButtonId());
        String _gender=selection.getText().toString();
        String licNo=LicenceNo.getText().toString();
        Details details=new Details(name,mobile,address,_gender,licNo);
        databaseReference.child(mobile).setValue(details);

    }

    //Upload Image
    public void UploadImage(Uri image){
        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+ randomKey);
        finalimg=image;


        riversRef.putFile(this.finalimg)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_LONG).show();

                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }
}