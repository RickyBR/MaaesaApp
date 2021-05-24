package com.example.maesaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    LoginResponse loginResponse;
    Button btn_addcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_addcard = findViewById(R.id.add_card);

        Intent intent= getIntent();
        if(intent.getExtras() != null){
            loginResponse = (LoginResponse) intent.getSerializableExtra("data");
            Log.e("TAG", "=====>" +loginResponse.getEmail());
        }

        btn_addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddCard = new Intent(HomeActivity.this, AddCardActivity.class);
                startActivity(goAddCard);
            }
        });
    }
}