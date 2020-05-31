package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        spinner = findViewById(R.id.spinner);
        arrayList=new ArrayList<String>();
        arrayList.add("Select option");
        arrayList.add("Monday");
        arrayList.add("Tuesday");
        arrayList.add("Wednesday");
        arrayList.add("Thursday");
        arrayList.add("Friday");
        arrayList.add("Saturday");
        arrayList.add("Sunday");
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                String data = (String) spinner.getItemAtPosition(i);
                Intent intent = new Intent(DietActivity.this, UpdateDietActivity.class);
                if (data.equals("Monday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
                if (data.equals("Tuesday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
                if (data.equals("Wednesday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
                if (data.equals("Thursday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
                if (data.equals("Friday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
                if (data.equals("Saturday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
                if (data.equals("Sunday")) {
                    intent.putExtra("key",data);
                    startActivity(intent);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}