package com.example.visitrwanda;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Admin extends AppCompatActivity {
    CardView Kibeho, Gisozi, Hotel, Akagera, Farmer,Investors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Kibeho = findViewById(R.id.boo);
        Gisozi= findViewById(R.id.gi);
        Hotel= findViewById(R.id.hotel);
        Akagera = findViewById(R.id.akagera);
        Investors = findViewById(R.id.invest);

        Kibeho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this,bookingNyungwe.class));
            }
        });

        Gisozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, bookingGisozi.class));

            }
        });

        Hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, bookingHotel.class));

            }
        });

        Akagera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, bookingAkagera.class));

            }
        });


        Investors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, bookingNyanza.class));

            }
        });
    }
}