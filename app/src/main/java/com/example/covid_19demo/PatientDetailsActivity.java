package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientDetailsActivity extends AppCompatActivity {

    private TextView tv_name, tv_phnum, tv_res;
    private Button bt_diet, btn_cases;
    private DatabaseReference myRef;
    //private EditText et_date, et_mon, et_year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        tv_name = findViewById(R.id.tv_PD_patient_name);
        tv_phnum = findViewById(R.id.tv_PD_patient_phnnum);
        tv_res = findViewById(R.id.tv_PD_patient_result);
        bt_diet = findViewById(R.id.bt_PD_view_diet);
        btn_cases = findViewById(R.id.bt_PD_view_days_left);

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

        btn_cases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientDetailsActivity.this,UserCasesActivity.class));
            }
        });

    }
}
