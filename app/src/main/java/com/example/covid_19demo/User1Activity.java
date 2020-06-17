package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User1Activity extends AppCompatActivity {

    private EditText et1,et2;
    private Button bt2;
    private TextView tv3;
    String email,password,ename1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        bt2 = findViewById(R.id.button2);//login
        tv3 = findViewById(R.id.textView3);//register
        mAuth = FirebaseAuth.getInstance();

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=et1.getText().toString().trim();
                password=et2.getText().toString().trim();

                if(validate(email,et1) && validate(password,et2)){
                    loginUser(email,password);
                }
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User1Activity.this,RegisterActivity.class));
            }
        });
    }

    private void loginUser(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                if(email.equals("theadmin123@gmail.com") && password.equals("admin@123")){

                    Toast.makeText(getApplicationContext(),"WELCOME TO THE ADMIN",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(User1Activity.this,AdminActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext()," WELCOME ",Toast.LENGTH_LONG).show();
                    Intent it = new Intent(User1Activity.this, PatientDetailsActivity.class);
                    startActivity(it);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(User1Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean validate(String string, EditText editText) {
        if(string.isEmpty()){
            editText.setError("Please Fill This Field...");
            return false;
        }
        return true;
    }

}