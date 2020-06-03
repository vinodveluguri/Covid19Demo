package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserDietDayActivity extends AppCompatActivity {
    String a = " ";
    private Button btn_mon, btn_tue, btn_wed, btn_thur, btn_fri, btn_sat, btn_sun ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_diet_day);

        btn_mon = findViewById(R.id.bt_UDDay_mon);
        btn_tue = findViewById(R.id.bt_UDDay_tue);
        btn_wed = findViewById(R.id.bt_UDDay_wed);
        btn_thur = findViewById(R.id.bt_UDDay_thus);
        btn_fri = findViewById(R.id.bt_UDDay_fri);
        btn_sat = findViewById(R.id.bt_UDDay_sat);
        btn_sun = findViewById(R.id.bt_UDDay_sun);


        btn_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "monday");
                startActivity(intent);
            }
        });

        btn_tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "tuesday");
                startActivity(intent);
            }
        });

        btn_wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "wednesday");
                startActivity(intent);
            }
        });

        btn_thur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "thursday");
                startActivity(intent);
            }
        });

        btn_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "friday");
                startActivity(intent);
            }
        });

        btn_sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "saturday");
                startActivity(intent);
            }
        });

        btn_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDietDisplayActivity.class);
                intent.putExtra("selectDay", "sunday");
                startActivity(intent);
            }
        });
    }
}
