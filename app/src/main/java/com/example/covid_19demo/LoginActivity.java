package com.example.covid_19demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button bt_user_signIn;
    private TextView tv_user_signUp;
    private EditText et_mail, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt_user_signIn =findViewById(R.id.bt_login_signIn);
        tv_user_signUp = findViewById(R.id.tv_main_login);
        et_mail =findViewById(R.id.et_login_username);
        et_pass = findViewById(R.id.et_login_pwd);

        bt_user_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_mail.getText().toString();
                String password = et_pass.getText().toString();
                if(email.equals("admin@gmail.com") && password.equals("admin123")){
                    startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                }
                else{
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
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
}
