package com.example.visitrwanda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText textEmail,textpass;
    TextView create;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textEmail =findViewById(R.id.email1);
        textpass =  findViewById(R.id.Pass);
        create= findViewById(R.id.textView7);
        login = findViewById(R.id.button2);
        fAuth=FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String password = textpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    textEmail.setError("email required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    textpass.setError("password is empty");
                    return;

                }
                if (password.length() < 6) {
                    textpass.setError("password must be 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //Authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "you have succesfully logged", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Signup.class));
                        } else {
                            Toast.makeText(Login.this, "error occured" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }
}