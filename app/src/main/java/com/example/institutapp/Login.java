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

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        EditText etUsername = findViewById(R.id.editTextUsernameLogin);
        EditText etPassword = findViewById(R.id.editTextTextPasswordLogin);
        Button btnLogin = findViewById(R.id.loginButton);
        TextView loginMessage = findViewById(R.id.loginMessage);
        TextView tvRegister = findViewById(R.id.textViewRegister);

        DBHelper db = new DBHelper(this);

        btnLogin.setOnClickListener(v -> {

            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            Intent i = new Intent(v.getContext(), MainActivity.class);
            Intent refresh = new Intent(v.getContext(), Login.class);
            /*if(etUsername.getText().toString().equals("username") && etPassword.getText().toString().equals("password")){
                v.getContext().startActivity(i);
                //finish();
            }else{
                startActivity(refresh);
                finish();
            }*/

            if(user.equals("") || pass.equals(""))
                loginMessage.setText("All fields must be entered");
            else { if(db.checkUserPass(user, pass)){
                i.putExtra("user",user);
                startActivity(i);
                finish();
            } else loginMessage.setText("Username or Password Incorrect");
            }

        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Register.class);
                v.getContext().startActivity(i);
                finish();
            }
        });




    }
}