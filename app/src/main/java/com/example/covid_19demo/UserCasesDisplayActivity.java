package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserCasesDisplayActivity extends AppCompatActivity {

    private TextView tv_pos, tv_rec, tv_dead;
    private TextView tv_state;
    private ImageView imgback;
    private DatabaseReference myRef;
    String sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cases_display);

        tv_pos = findViewById(R.id.tv_UCD_posCase);
        tv_rec = findViewById(R.id.tv_UCD_reco);
        tv_dead = findViewById(R.id.tv_UCD_death);
        tv_state = findViewById(R.id.tv_UCD_key);
        imgback = findViewById(R.id.iv_UCD_back);

        sm = getIntent().getStringExtra("selectDay");
        myRef = FirebaseDatabase.getInstance().getReference("Cases");
        tv_state.setText(sm);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i : dataSnapshot.getChildren()) {
                    if ((i.getKey()).equalsIgnoreCase(sm)) {
                        Cases cases = i.getValue(Cases.class);
                        tv_pos.setText(cases.getPositive());
                        tv_rec.setText(cases.getRecovery());
                        tv_dead.setText(cases.getDeaths());
                        //tv_din.setText(cases.getDinner());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserCasesDisplayActivity.this,UserCasesActivity.class));
            }
        });

    }
}

