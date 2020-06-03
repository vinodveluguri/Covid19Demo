package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    private EditText et_pname,et_pnnum,et_join,et_date,et_month,et_year,et_posneg;
    private Button bt_submit;
    private ImageView img_back;
    String con = " ";
    private FirebaseDatabase db;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        img_back = findViewById(R.id.iv_add_back);
        et_pname = findViewById(R.id.et_add_PName);
        et_pnnum = findViewById(R.id.et_add_Phnnum);
        et_join = findViewById(R.id.et_add_joined);
        et_date = findViewById(R.id.et_add_date);
        et_month = findViewById(R.id.et_add_month);
        et_year = findViewById(R.id.et_add_year);
        et_posneg = findViewById(R.id.et_add_pos_neg);
        bt_submit = findViewById(R.id.bt_add_submit);
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("Patients");

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_pname.getText().toString();
                String phone = et_pnnum.getText().toString();
                String join = et_join.getText().toString();
                String date = et_date.getText().toString();
                String month = et_month.getText().toString();
                String year = et_year.getText().toString();
                String res = et_posneg.getText().toString();

                if (name.equals(con) || phone.equals(con) || join.equals(con) || date.equals(con) || month.equals(con) || year.equals(con) || res.equals(con)) {
                    Toast.makeText(getApplicationContext(),"FILL THE TEXT FIELDS TO STORE THE DATA",Toast.LENGTH_LONG).show();
                }
                else {
                    Patients pat = new Patients();
                    pat.setName(name);
                    pat.setPhone(phone);
                    pat.setJoin(join);
                    pat.setDate(date);
                    pat.setMonth(month);
                    pat.setYear(year);
                    pat.setRes(res);

                    myRef.child(myRef.push().getKey()).setValue(pat);
                    Toast.makeText(getApplicationContext(), "Stored Succesfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this,AdminActivity.class));
            }
        });
    }
}
