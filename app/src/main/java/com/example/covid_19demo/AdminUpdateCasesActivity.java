package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminUpdateCasesActivity extends AppCompatActivity {

    private EditText et_positive, et_recovered, et_deaths, et_state;
    private Button update;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    //String state, positive, recovered, deaths;
    String sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_cases);

        et_state = findViewById(R.id.et_AUC_stateName);
        et_positive = findViewById(R.id.et_AUC_posCases);
        et_recovered = findViewById(R.id.et_AUC_recovery);
        et_deaths = findViewById(R.id.et_AUC_deaths);
        update = findViewById(R.id.bt_AUC_update);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        sm = intent.getStringExtra("selectDay");
        myRef = FirebaseDatabase.getInstance().getReference("Cases");
        //textView.setText(sm);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = et_state.getText().toString();
                String positive = et_positive.getText().toString();
                String recover = et_recovered.getText().toString();
                String death = et_deaths.getText().toString();

                Cases cases = new Cases(state,positive,recover,death);
                myRef.child(sm).setValue(cases);
                updateCases(state, positive, recover, death);

            }
        });

    }

    private void updateCases(final String state, final String positive, final String recover, final String death) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()){
                    Cases cases = i.getValue(Cases.class);
                    if(cases.getState().equals(state)){
                        cases.setPositive(positive);
                        cases.setRecovery(recover);
                        cases.setDeaths(death);

                        myRef.child(i.getKey()).setValue(cases);
                        Toast.makeText(getApplicationContext(),"Cases Updated",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
