package com.example.letsplay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputSport, inputFullName, inputAddress;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputEmail = (EditText) findViewById(R.id.email);
        inputAddress = (EditText) findViewById(R.id.address);
        inputSport = (EditText) findViewById(R.id.sport);
        inputFullName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void logMeIn(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    public void sign(View view) {

        final String email = inputEmail.getText().toString().trim();
        final String address = inputAddress.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();
        final String sport = inputSport.getText().toString().trim();
        final String fullname = inputFullName.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(getApplicationContext(), "Enter full name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sport)) {
            Toast.makeText(getApplicationContext(), "Enter sport!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        addUser(new User(fullname, email, address, sport, password));
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void addUser(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(user.mail.split("\\.", 2)[0]).setValue(user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}