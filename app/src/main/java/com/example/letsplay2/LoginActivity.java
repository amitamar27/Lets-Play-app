package com.example.letsplay2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.email);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputPassword = (EditText) findViewById(R.id.password);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void login(View view) {
        final String email = inputEmail.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        // Read from the database
        DatabaseReference myRef = mDatabase.child("users").child(email.split("\\.", 2)[0]);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.getValue(User.class);
                if(email.contentEquals(value.mail) && password.contentEquals(value.pass))
                {
                    context.startActivity(new Intent(context, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);

                }
               // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
