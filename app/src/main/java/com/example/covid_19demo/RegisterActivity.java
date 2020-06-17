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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username, et_mobile, et_mail, et_password;
    private Button bt_signup;
    private TextView tv_signIn;
    private FirebaseAuth mAuth;
    private String username, mobnum, password,email;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = findViewById(R.id.et_register_username);
        et_mobile = findViewById(R.id.et_register_phnnumber);
        et_mail = findViewById(R.id.et_register_email);
        et_password = findViewById(R.id.et_register_password);

        bt_signup = findViewById(R.id.bt_register_signUp);
        tv_signIn = findViewById(R.id.tv_register_signIn);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Users");

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_username.getText().toString();
                mobnum = et_mobile.getText().toString();
                email = et_mail.getText().toString();
                password = et_password.getText().toString();

                if (validate(username,et_username) && validate(mobnum,et_mobile)&&
                        validate(email,et_mail) && validate(password,et_password)){
                    registerUser(email, password);
                }
            }
        });

        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, User1Activity.class));
            }
        });
    }

    private void registerUser(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(username, mobnum, email);
                            myRef.child(mAuth.getUid()).setValue(user);
                            Toast.makeText(RegisterActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterActivity.this,User1Activity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validate(String string, EditText editText) {
        if (string.isEmpty()) {
            editText.setError("Fill the contents ");
            return false;
        }
        return true;
    }
}