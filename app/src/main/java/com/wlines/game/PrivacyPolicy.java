package com.wlines.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PrivacyPolicy extends AppCompatActivity {


    TextView policy,accept,decline;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privat_polisy);

        accept = findViewById(R.id.accept);
        decline = findViewById(R.id.decline);
        policy = findViewById(R.id.policy);

        String data = getIntent().getStringExtra("polisy");
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PrivacyPolicy.this, Policy.class);
                myIntent.putExtra("polisy", data);
                startActivity(myIntent);
            }
        });

        accept.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PrivacyPolicy.this, GameMainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        decline.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }

        });

    }
}