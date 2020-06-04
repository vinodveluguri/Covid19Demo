package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientDetailsActivity extends AppCompatActivity {

    private TextView tv_name, tv_phnum, tv_res;
    private Button bt_diet;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        tv_name = findViewById(R.id.tv_PD_patient_name);
        tv_phnum = findViewById(R.id.tv_PD_patient_phnnum);
        tv_res = findViewById(R.id.tv_PD_patient_result);
        bt_diet = findViewById(R.id.bt_PD_view_diet);

        Intent it = getIntent();
        String s = it.getStringExtra("key");
        myRef = FirebaseDatabase.getInstance().getReference().child("Patients");

        myRef.child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = (String) dataSnapshot.child("name").getValue();
                String phone = (String) dataSnapshot.child("phone").getValue();
                String res = (String) dataSnapshot.child("res").getValue();
                tv_name.setText(name);
                tv_phnum.setText(phone);
                tv_res.setText(res);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bt_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientDetailsActivity.this,UserDietDayActivity.class));
            }
        });
    }


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);


        tv_joinDate = findViewById(R.id.tv_PD_patient_joinedDate);
        tv_joinAt = findViewById(R.id.tv_PD_patient_joinedAt);
        bt_diet = findViewById(R.id.bt_PD_view_diet);
        tv_headname = findViewById(R.id.tv_PD_user_name);

        myRef = FirebaseDatabase.getInstance().getReference("Patients");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()){
                    if ((i.getKey()).equals(FirebaseAuth.getInstance().getClass().getName())){
                        Patients patients = i.getValue(Patients.class);
                        tv_name.setText(patients.getName());
                        tv_phnum.setText(patients.getPhone());
                        tv_joinAt.setText(patients.getJoin());
                        tv_joinDate.setText(patients.getDate());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
}
