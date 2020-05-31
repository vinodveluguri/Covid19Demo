package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDietActivity extends AppCompatActivity {
    private EditText et_UD_1, et_UD_2, et_UD_3;
    private TextView textView;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private Button bt_update;
    private String pref1, pref2, pref3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diet);

        bt_update = findViewById(R.id.bt_UD_update);
        et_UD_1 = findViewById(R.id.et_UD_1pref);
        et_UD_2 =findViewById(R.id.et_UD_2pref);
        et_UD_3 = findViewById(R.id.et_UD_3pref);
        textView = findViewById(R.id.tv_update_diet_key);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Diet");

        Intent intent = getIntent();
        String s = intent.getStringExtra("key");

        textView.setText(s);


        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pref1 = et_UD_1.getText().toString();
                pref2 = et_UD_2.getText().toString();
                pref3 = et_UD_3.getText().toString();

               Diet diet = new Diet(pref1, pref2, pref3);
               myRef.child(mAuth.getUid()).push().setValue(diet);
                Toast.makeText(UpdateDietActivity.this,"update Successfull",Toast.LENGTH_SHORT).show();

            }
        });
    }


}
