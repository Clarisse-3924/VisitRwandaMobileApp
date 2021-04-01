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

public class akagera extends AppCompatActivity {
    EditText Name1,email1,organization,phone1;
    Button Submit;
    TextView textView5;
    Akagerabooking guest;
    DatabaseReference databaseReference;
    aka upl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akagera);
        Name1 = (EditText)findViewById(R.id.Name1);
        email1 = (EditText)findViewById(R.id.email1);
        organization = (EditText)findViewById(R.id.organization);
        phone1= (EditText) findViewById(R.id.phone1);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Akagerabooking");
        Submit = (Button) findViewById(R.id.button3);
        upl = new aka();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upl.setName(Name1.getText().toString());
                upl.setEmail(email1.getText().toString());
                upl.setPhone(phone1.getText().toString());
                upl.setOrganization(organization.getText().toString());

                String name= Name1.getText().toString().trim();
                String email= email1.getText().toString().trim();
                String phone= phone1.getText().toString().trim();
                String oragan= organization.getText().toString().trim();


                if(TextUtils.isEmpty(name)){
                    Name1.setError("required");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    email1.setError("required");
                    return;
                }


                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email1.getText().toString()).matches()){
                    email1.setError("Enter Valid email");
                }

                if(TextUtils.isEmpty(phone)){
                    phone1.setError("Phone required");
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
                String id = databaseReference.push().getKey();
                databaseReference.child(id).setValue(upl);
                Toast.makeText(akagera.this, "Data saved", Toast.LENGTH_SHORT).show();
                Toast.makeText(akagera.this, "THANK YOU FOR BOOKING", Toast.LENGTH_SHORT).show();
            }
        });

    }

}