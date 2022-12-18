package com.example.institutapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Recap extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);
/*
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        DBHelper db = new DBHelper(this);

        TextView recapT, plusF = findViewById(R.id.textViewPlusF);

        recapT = findViewById(R.id.recapTXT);
        Intent i = getIntent();
        String usernamePI = i.getStringExtra("username");
        String formationName = i.getStringExtra("formationName");
        recapT.setText(formationName);

        plusF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                //i.putExtra("usernamePI", usernamePI);
                startActivity(i);
            }
        });



    }
}