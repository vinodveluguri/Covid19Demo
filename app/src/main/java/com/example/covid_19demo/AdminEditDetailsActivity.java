package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminEditDetailsActivity extends AppCompatActivity {

    private EditText et_name;
    private Button btn_update;
    private DatabaseReference myRef;
    private RadioButton radneg, radpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_details);

        et_name = findViewById(R.id.et_AED_name);
        radneg = findViewById(R.id.rad_AED_neg);
        radpos = findViewById(R.id.rad_AED_pos);
        btn_update = findViewById(R.id.bt_AED_update);
        myRef = FirebaseDatabase.getInstance().getReference("Patients");


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String positive = radpos.getText().toString();
                String negative = radneg.getText().toString();

                String uid = FirebaseAuth.getInstance().getUid();
                updateDetails(name,positive,negative);
            }
        });
    }

    public void updateDetails(final String name, final String positive, final String negative) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Patients patients = i.getValue(Patients.class);
                    if(patients.getName().equals(name) ){
                        if(radpos.isChecked()) {
                            patients.setRes(positive);
                        }
                        if(radneg.isChecked()) {
                            patients.setRes(negative);
                        }
                        myRef.child(i.getKey()).setValue(patients);
                        Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
            startActivity(new Intent(AdminEditDetailsActivity.this,User1Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
