package com.matthajus.fara_spevnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPassword;

    private Set<String> names = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.loginEditText);
        userPassword = findViewById(R.id.passwordEditText);

        names.add("Marek");
        names.add("Matus");
        names.add("Martin");
        names.add("Igor");
        names.add("Peter");
        names.add("Tina");
        names.add("Adka");
        names.add("Patka");
        names.add("Mima");
        names.add("Katka");
        names.add("Veronika");


    }

    public void clickOnLoginButton(View view) {
        if (names.contains(userName.getText().toString()) && userPassword.getText().toString().equals("fara")) {
            //Toast.makeText(this, "Funguje", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Zlé prihlasovacie meno alebo heslo!", Toast.LENGTH_SHORT).show();
        }
    }
}