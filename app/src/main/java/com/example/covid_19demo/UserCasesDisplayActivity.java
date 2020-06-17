package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserCasesDisplayActivity extends AppCompatActivity {

    private TextView tv_pos, tv_rec, tv_dead;
    private TextView tv_state;
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
            startActivity(new Intent(UserCasesDisplayActivity.this,User1Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}