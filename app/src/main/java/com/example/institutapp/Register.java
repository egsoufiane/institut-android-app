package com.example.institutapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        EditText username = findViewById(R.id.editTextUsernameRegister);
        EditText password = findViewById(R.id.editTextTextPasswordRegister);
        EditText confirmPassword = findViewById(R.id.editTextTextConfirmPasswordRegister);
        TextView registerMessage = findViewById(R.id.registerMessage);

        TextView etLogin = findViewById(R.id.textViewRegister);
        Button btnRegister = findViewById(R.id.registerButton);


        DBHelper db = new DBHelper(this);
        /*
        String[] formationNames = getResources().getStringArray(R.array.formationNamesTXT);
        String[] formationBD = getResources().getStringArray(R.array.beginDate);
        String[] formationED = getResources().getStringArray(R.array.endDate);

        // ajouter chaque nom et img pour chaque formation à notre model

        for(int i = 0; i < formationNames.length; i++ )
            db.insertFormation(formationNames[i], formationBD[i], formationED[i]);
         */


        etLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

        //Handle register button click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String passC = confirmPassword.getText().toString();


                if(user.equals("") || pass.equals("") || passC.equals("")) {
                    registerMessage.setText("All fields must be filled");
                } else{
                    if(!pass.equals(passC)){
                        registerMessage.setText("Password and Confirm Password must be matching");
                    }else {
                        if(db.checkUsername(user)){
                            registerMessage.setText("Username already exists choose another one");

                        } else {
                            //input data into database
                            db.insertUser(user,pass);
                            Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                        }
                    }
                }

            }
        });
    }
}