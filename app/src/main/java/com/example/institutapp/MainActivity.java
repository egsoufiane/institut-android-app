package com.example.institutapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //list pour storer img et nom des formations
    ArrayList<FormationModel> formationModels = new ArrayList<>();
    FormationAdapter adapter = new FormationAdapter(this, formationModels);
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

       DBHelper db = new DBHelper(this);



        String[] formationNames = getResources().getStringArray(R.array.formationNamesTXT);
        String[] formationBD = getResources().getStringArray(R.array.beginDate);
        String[] formationED = getResources().getStringArray(R.array.endDate);

        // ajouter chaque nom et img pour chaque formation à notre model

        for(int i = 0; i < formationNames.length; i++ )
            db.insertFormation(formationNames[i], formationBD[i], formationED[i]);

        /*
        db.insertFormation("test","56546","56786");
        db.insertFormation("test","56546","56786");
        db.insertFormation("test1","56546","56786");
        */


        RecyclerView recyclerView = findViewById(R.id.formationRecycler);
        setUpFormationModels();

        //Get username and pass it to FormationAdapter
        Intent intent = getIntent();
        username = intent.getStringExtra("user");

        //Get username from Recap +Info click

        Intent intentPI = getIntent();
        username = intentPI.getStringExtra("username");

        adapter.setUsername(username);





        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //search

        EditText editTextS = findViewById(R.id.editTextSearch);
        editTextS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s){
                filter(s.toString());
            }
        });



    }

    //Filtrer la liste selon le texte passer en argument
    public void filter(String text){
        ArrayList <FormationModel> filteredFormationList = new ArrayList<>();

        // pour chaque item dans notre liste chercher si le nom de la formation contient text donner en argument
        for (FormationModel formation : formationModels){
            if(formation.getFormationName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))){
                filteredFormationList.add(formation);
            }
        }
        adapter.filterList(filteredFormationList);
    }

    private void setUpFormationModels(){
        String[] formationNames = getResources().getStringArray(R.array.formationNamesTXT);
        int [] formationImages = {R.drawable.ic_html, R.drawable.ic_css, R.drawable.ic_javascript,
        R.drawable.ic_php, R.drawable.ic_python, R.drawable.ic_java};

        // ajouter chaque nom et img pour chaque formation à notre model
        for(int i = 0; i < formationNames.length; i++ ){
            formationModels.add( new FormationModel (formationNames[i] ,formationImages[i]) );
        }
    }



}