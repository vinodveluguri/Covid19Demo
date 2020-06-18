package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private EditText et_pname,et_pnnum,et_join,et_date,et_mail;
    private static final String TAG = "AddActivity";
    private Button bt_submit;
    String con = " ";
    private RadioButton radneg, radpos;
    private RadioGroup btngrp;
    private FirebaseDatabase db;
    private DatabaseReference myRef;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_pname = findViewById(R.id.et_add_PName);
        et_pnnum = findViewById(R.id.et_add_Phnnum);
        et_join = findViewById(R.id.et_add_joined);
        et_date = findViewById(R.id.et_add_joindate);
        et_mail = findViewById(R.id.et_add_email);
        bt_submit = findViewById(R.id.bt_add_submit);
        radneg = findViewById(R.id.rad_add_neg);
        radpos = findViewById(R.id.rad_add_pos);
        btngrp = findViewById(R.id.btngrop);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("Patients");

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_pname.getText().toString();
                String phone = et_pnnum.getText().toString();
                String join = et_join.getText().toString();
                String date = et_date.getText().toString();
                String positive = radpos.getText().toString();
                String negative = radneg.getText().toString();
                String mail = et_mail.getText().toString();

                if (name.equals(con) || phone.equals(con) || join.equals(con) || date.equals(con) || positive.equals(con)||  negative.equals(con) || mail.equals(con)) {
                    Toast.makeText(getApplicationContext(),"FILL THE TEXT FIELDS TO STORE THE DATA",Toast.LENGTH_LONG).show();
                }
                else {
                    Patients pat = new Patients();
                    pat.setMail(mail);
                    pat.setName(name);
                    pat.setPhone(phone);
                    pat.setJoin(join);
                    pat.setDate(date);

                    if(radpos.isChecked())
                        pat.setRes(positive);
                    if(radneg.isChecked())
                        pat.setRes(negative);

                    myRef.child(name).setValue(pat);
                    Toast.makeText(getApplicationContext(), "Stored Succesfully", Toast.LENGTH_LONG).show();
                }
                et_pname.setText("");
                et_pnnum.setText("");
                et_date.setText("");
                et_join.setText("");
                et_mail.setText("");
                if(btngrp!=null) {
                    btngrp.clearCheck();
                }

            }
        });

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm-dd-yyy: " + month + "-" + day + "-" + year);

                String date = month + "-" + day + "-" + year;
                et_date.setText(date);
            }
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.signup_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.signout_item){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AddActivity.this,User1Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
