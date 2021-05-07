package com.vimal.gpslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserDetails extends AppCompatActivity {
String Mobile;
TextView name,mobile,gender,licNo,address;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        name=findViewById(R.id.UserName);
        mobile=findViewById(R.id.UserMobile);
        gender=findViewById(R.id.textView21);
        licNo=findViewById(R.id.textView9);
        address=findViewById(R.id.textView11);

        Intent in=getIntent();

        Mobile= in.getStringExtra("contactNo");
        databaseReference= FirebaseDatabase.getInstance().getReference("Details");
        Query user=databaseReference.orderByChild("mobile").equalTo(Mobile);
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name=snapshot.child(Mobile).child("name").getValue(String.class);
                String _gender=snapshot.child(Mobile).child("gender").getValue(String.class);
                String _lic=snapshot.child(Mobile).child("licno").getValue(String.class);
                String _address=snapshot.child(Mobile).child("address").getValue(String.class);
                name.setText(Name);
                mobile.setText(Mobile);
                gender.setText(_gender);
                licNo.setText(_lic);
                address.setText(_address);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}