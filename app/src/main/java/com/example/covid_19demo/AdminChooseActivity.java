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

public class AdminChooseActivity extends AppCompatActivity {

    private TextView tv_username, tv_email, tv_mobile, tv_joinAt;
    private Button edit;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_choose);

        tv_username = findViewById(R.id.tv_AC_username);
        tv_email = findViewById(R.id.tv_AC_mail);
        tv_mobile = findViewById(R.id.tv_AC_phnnum);
        tv_joinAt = findViewById(R.id.tv_AC_joinAt);
        edit = findViewById(R.id.bt_admchs_edtdet);

        /*myRef= FirebaseDatabase.getInstance().getReference("Patients");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    if(dataSnapshot1.getKey().equals(tv_username)){
                        Patients patients = dataSnapshot1.getValue(Patients.class);
                       // tv_username.setText(patients.getName());
                        tv_email.setText(patients.getMail());
                        tv_mobile.setText(patients.getPhone());
                        tv_joinAt.setText(patients.getJoin());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void updateDetails(View view){
        startActivity(new Intent(AdminChooseActivity.this,AdminEditDetailsActivity.class));
    }*/
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminChooseActivity.this,AdminEditDetailsActivity.class));
            }
        });
    }
}