package com.example.visitrwanda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Gisozi extends AppCompatActivity {
    EditText Name1,email1,organization,phone1;
    Button Submit;
    DatabaseReference reff;
    Booking upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gisozi);
        Name1 = (EditText)findViewById(R.id.Name1);
        email1 = (EditText)findViewById(R.id.email1);
        organization = (EditText)findViewById(R.id.organization);
        phone1= (EditText) findViewById(R.id.phone1);

        reff = FirebaseDatabase.getInstance().getReference().child("kibeho");
        Submit = (Button) findViewById(R.id.button3);
        upload = new Booking();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload.setName(Name1.getText().toString().trim());
                upload.setEmail(email1.getText().toString().trim());
                upload.setPhone(phone1.getText().toString().trim());
                upload.setOrganization(organization.getText().toString().trim());

                String name= Name1.getText().toString().trim();
                String email= email1.getText().toString().trim();
                String phone= phone1.getText().toString().trim();
                String oragan= organization.getText().toString().trim();


                if(TextUtils.isEmpty(name)){
                    Name1.setError(" required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    email1.setError(" required");
                    return;
                }
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email1.getText().toString()).matches()){
                    email1.setError("Enter Valid email");
                }
                if(TextUtils.isEmpty(phone)){
                    phone1.setError(" required");
                    return;
                }
                if(phone.length()!=10){
                    phone1.setError("Phone number must be ten");
                    return;
                }

                if(TextUtils.isEmpty(oragan)){
                    organization.setError("required");
                    return;
                }

                String id = reff.push().getKey();
                reff.child(id).setValue(upload);
                Toast.makeText(Gisozi.this, "Data saved", Toast.LENGTH_SHORT).show();
                Toast.makeText(Gisozi.this, "THANK YOU FOR BOOKING", Toast.LENGTH_SHORT).show();
            }
        });

    }
}