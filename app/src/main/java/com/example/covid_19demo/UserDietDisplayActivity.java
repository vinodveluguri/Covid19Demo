package com.example.covid_19demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDietDisplayActivity extends AppCompatActivity {

    private TextView tv_day;
    private ImageView imgback;
    private DatabaseReference myRef;
    private TextView tv_bf, tv_lun, tv_snk, tv_din;
    String sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_diet_display);

        tv_day = findViewById(R.id.tv_UDDis_key);
        imgback = findViewById(R.id.iv_UDDis_back);
        tv_bf = findViewById(R.id.tv_UDDis_getBf);
        tv_lun = findViewById(R.id.tv_UDDis_getlunch);
        tv_snk = findViewById(R.id.tv_UDDis_getSnack);
        tv_din = findViewById(R.id.tv_UDDis_getDinner);

        sm = getIntent().getStringExtra("selectDay");
        myRef = FirebaseDatabase.getInstance().getReference("Diet");
        tv_day.setText(sm);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot i : dataSnapshot.getChildren()) {
                    if ((i.getKey()).equalsIgnoreCase(sm)) {

                        Diet diet = i.getValue(Diet.class);
                        tv_bf.setText(diet.getBreakfast());
                        tv_lun.setText(diet.getLunch());
                        tv_snk.setText(diet.getSnack());
                        tv_din.setText(diet.getDinner());
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
                startActivity(new Intent(UserDietDisplayActivity.this,UserDietDayActivity.class));
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
            startActivity(new Intent(UserDietDisplayActivity.this,User1Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
