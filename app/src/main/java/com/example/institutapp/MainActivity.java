package com.example.institutapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //list pour storer img et nom des formations
    ArrayList<FormationModel> formationModels = new ArrayList<>();
    FormationAdapter adapter = new FormationAdapter(this, formationModels);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        RecyclerView recyclerView = findViewById(R.id.formationRecycler);

        setUpFormationModels();


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