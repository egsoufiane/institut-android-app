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

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TextView recapT, plusF = findViewById(R.id.textViewPlusF);

        plusF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        recapT = findViewById(R.id.recapTXT);
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        recapT.setText(title);

    }
}