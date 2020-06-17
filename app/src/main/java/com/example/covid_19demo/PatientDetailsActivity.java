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

    private EditText searchname;
    private TextView tv_name, tv_phnum, tv_res;
    private Button bt_diet, btn_cases,bt_search;
    private DatabaseReference myRef;
    String s;
    //private EditText et_date, et_mon, et_year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        bt_search = findViewById(R.id.bt_search);
        searchname = findViewById(R.id.et_searchname);
        tv_name = findViewById(R.id.tv_PD_patient_name);
        tv_phnum = findViewById(R.id.tv_PD_patient_phnnum);
        tv_res = findViewById(R.id.tv_PD_patient_result);
        bt_diet = findViewById(R.id.bt_PD_view_diet);
        btn_cases = findViewById(R.id.bt_PD_view_days_left);

        //Intent it = getIntent();
        //String s = it.getStringExtra("key");
        //String s = searchname.getText().toString();

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = searchname.getText().toString();

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

            }
        });

        /*Intent it = getIntent();
        s = it.getStringExtra("key");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i : dataSnapshot.getChildren()) {
                    if ((i.getKey()).equalsIgnoreCase(s)) {
                        Patients pat = i.getValue(Patients.class);
                        tv_name.setText(pat.getName());
                        tv_phnum.setText(pat.getPhone());
                        tv_res.setText(pat.getRes());
                        //tv_din.setText(cases.getDinner());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });*/

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