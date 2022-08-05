package com.wlines.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private EditText enterNick;
    public static String nick, lvl;
    private Context con;
    private boolean checkLvl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        con = this;

        RadioGroup rgLvl = (RadioGroup) findViewById(R.id.rgLvl);
        rgLvl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbEasy:
                        checkLvl = true;
                        lvl = "easy";
                        break;
                    case R.id.rbMedium:
                        checkLvl = true;
                        lvl = "medium";
                        break;
                    case R.id.rbHard:
                        checkLvl = true;
                        lvl = "hard";
                        break;
                }
            }

        });

        Button bStart = (Button) findViewById(R.id.bStart);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //enterNick = (EditText) findViewById(R.id.enterNick);
                //nick = enterNick.getText().toString();

                if(checkLvl==true) {
                    Intent intent = new Intent(SettingsActivity.this, GameActivity.class);
                    //intent.putExtra("nick", nick);
                    intent.putExtra("lvl", lvl);
                    startActivity(intent);
                    finish();
                }//else Toast.makeText(con,"Nick: 1-7 znaków. Wybierz poziom trudności.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
