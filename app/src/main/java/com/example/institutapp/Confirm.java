package com.example.institutapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Confirm extends AppCompatActivity {

    TextView confirmT;
    ImageView confirmI,backI;
    Button confirmB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        backI = findViewById(R.id.imageViewBack);
        confirmI = findViewById(R.id.imageViewFConfirm);
        confirmT = findViewById(R.id.textViewFConfirm);
        confirmB = findViewById(R.id.buttonFConfirm);

        DBHelper db = new DBHelper(this);


        backI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(i);
            }
        });

        Intent i = getIntent();
        String formationName = i.getStringExtra("formationName");
        String username = i.getStringExtra("username");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("image");
            confirmI.setImageResource(resId);
        }

        confirmT.setText(formationName);


        //Confirmer choix intent vers Recap

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Recap.class);
                i.putExtra("username", username);
                i.putExtra("formationName", formationName+db.getIdFormation(formationName)+db.getIdUser(username));

                //db.getIdFormation(formationName);
                //db.checkFormation(formationName);
                //db.insertEnrolled(db.getIdUser(username), db.getIdFormation(formationName));


                startActivity(i);
            }
        });


    }


}