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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDietActivity extends AppCompatActivity {

    private TextView textView;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private EditText et_breakfast,et_lunch, et_snack, et_dinner;
    public String breakfast, lunch, snack, dinner;
    private Button btn_add;
    String sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diet);

        textView =findViewById(R.id.tv_update_diet_key);
        et_breakfast = findViewById(R.id.et_UD_BF);
        et_lunch = findViewById(R.id.et_UD_LUN);
        et_snack =findViewById(R.id.et_UD_SNK);
        et_dinner = findViewById(R.id.et_UD_DIN);
        btn_add = findViewById(R.id.bt_UD_update);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        sm = intent.getStringExtra("selectDay");
        myRef = FirebaseDatabase.getInstance().getReference("Diet");
        textView.setText(sm);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakfast = et_breakfast.getText().toString();
                lunch = et_lunch.getText().toString();
                snack = et_snack.getText().toString();
                dinner = et_dinner.getText().toString();

                Diet diet = new Diet(breakfast, lunch, snack, dinner);
                myRef.child(sm).setValue(diet);
                updateDetails(breakfast, lunch,snack,dinner);

            }
        });

    }

    private void updateDetails(final String breakfast, final String lunch, final String snack, final String dinner) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Diet diet = i.getValue(Diet.class);
                    if (diet.getBreakfast().equals(breakfast)) {
                        diet.setLunch(lunch);
                        diet.setSnack(snack);
                        diet.setDinner(dinner);
                        myRef.child(i.getKey()).setValue(diet);
                        Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(UpdateDietActivity.this,User1Activity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}