package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private Button btn_add, btn_retrieve, btn_diet, btn_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_add = findViewById(R.id.bt_admin_add);
        btn_retrieve = findViewById(R.id.bt_admin_retrieve);
        btn_diet = findViewById(R.id.bt_admin_updiet);
        btn_state = findViewById(R.id.bt_admin_upcases);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,AddActivity.class));
            }
        });

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,AdminRetrieveActivity.class));
            }
        });

        btn_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,DietActivity.class));
            }
        });

        btn_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,AdminStateActivity.class));
            }
        });
    }
}