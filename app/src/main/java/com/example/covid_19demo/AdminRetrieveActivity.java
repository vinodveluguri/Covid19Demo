package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminRetrieveActivity extends AppCompatActivity {

    private Button btn_getDetails;
    private ListView lv_Details;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_retrieve);

        db =FirebaseDatabase.getInstance();
        myRef = db.getReference("Patients");

        lv_Details = findViewById(R.id.lv_adminRetrieve_list);
        btn_getDetails = findViewById(R.id.bt_adminRetrieve_retrive);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<String>();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Patients patients = i.getValue(Patients.class);
                    arrayList.add(patients.getName());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                lv_Details.setAdapter(arrayAdapter);
            }
        });

    lv_Details.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminRetrieveActivity.this,AdminChooseActivity.class);
                startActivity(intent);
            }
        });

    }
}
