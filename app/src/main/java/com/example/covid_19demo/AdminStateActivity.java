package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AdminStateActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btn_go;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_state);

        spinner = findViewById(R.id.sp_state);
        btn_go = findViewById(R.id.bt_AS_Go);

        arrayList = new ArrayList<String>();
        arrayList.add("Select option");
        arrayList.add("Andhra Pradesh");
        arrayList.add("Arunachal Pradesh");
        arrayList.add("Assam");
        arrayList.add("Bihar");
        arrayList.add("Chhattisgarh");
        arrayList.add("Goa");
        arrayList.add("Gujarat");
        arrayList.add("Haryana");
        arrayList.add("Himachal Pradesh");
        arrayList.add("Jharkhand");
        arrayList.add("Karnataka");
        arrayList.add("Kerala");
        arrayList.add("Madhya Pradesh");
        arrayList.add("Maharashtra");
        arrayList.add("Manipur");
        arrayList.add("Meghalaya");
        arrayList.add("Mizoram");
        arrayList.add("Nagaland");
        arrayList.add("Odisha");
        arrayList.add("Punjab");
        arrayList.add("Kerala");
        arrayList.add("Rajasthan");
        arrayList.add("Sikkim");
        arrayList.add("Tamil Nadu");
        arrayList.add("Telangana");
        arrayList.add("Tripura");
        arrayList.add("Uttar Pradesh");
        arrayList.add("Uttarakhand");
        arrayList.add("West Bengal");
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                data = (String) spinner.getItemAtPosition(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminStateActivity.this, AdminUpdateCasesActivity.class);
                intent.putExtra("selectDay",data);
                startActivity(intent);
                finish();
            }
        });

    }


}