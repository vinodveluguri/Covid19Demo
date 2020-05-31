package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminChooseActivity extends AppCompatActivity {

    private Button btn_edit, btn_diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_choose);

        btn_edit = findViewById(R.id.bt_admchs_edtdet);
        btn_diet = findViewById(R.id.bt_admchs_updiet);

        btn_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminChooseActivity.this,DietActivity.class));
            }
        });
    }
}
