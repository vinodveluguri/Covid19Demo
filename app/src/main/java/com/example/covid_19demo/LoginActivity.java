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

public class LoginActivity extends AppCompatActivity {

    private Button bt_user_signIn;
    private TextView tv_user_signUp;
    private EditText et_mail, et_pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt_user_signIn =findViewById(R.id.bt_login_signIn);
        tv_user_signUp = findViewById(R.id.tv_main_login);
        et_mail =findViewById(R.id.et_login_username);
        et_pass = findViewById(R.id.et_login_pwd);

        mAuth = FirebaseAuth.getInstance();

        bt_user_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_mail.getText().toString();
                String password = et_pass.getText().toString();

                if(email.equals("admin@gmail.com") && password.equals("admin123")){
                    startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                }
                if (validate(email, et_mail)&&validate(password,et_pass)){
                    loginUser(email, password);
                }
            }
        });

        tv_user_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(LoginActivity.this,PatientDetailsActivity.class));/*HomeActivity*/
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validate(String string, EditText editText) {
        if (string.isEmpty()){
            editText.setError("fill the contents");
            return false;
        }
        return true;
    }
}
