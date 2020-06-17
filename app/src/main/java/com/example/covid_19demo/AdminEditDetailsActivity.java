package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminEditDetailsActivity extends AppCompatActivity {

    private EditText et_name, et_res;
    private Button btn_update;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_details);

        et_name = findViewById(R.id.et_AED_name);
        et_res = findViewById(R.id.et_AED_status);
        btn_update = findViewById(R.id.bt_AED_update);
        myRef = FirebaseDatabase.getInstance().getReference("Patients");


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String result = et_res.getText().toString();

                String uid = FirebaseAuth.getInstance().getUid();
                updateDetails(name, result);
            }
        });
    }

    public void updateDetails(final String name, final String result) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Patients patients = i.getValue(Patients.class);
                    if(patients.getName().equals(name) ){
                        patients.setRes(result);
                        myRef.child(i.getKey()).setValue(patients);
                        Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
