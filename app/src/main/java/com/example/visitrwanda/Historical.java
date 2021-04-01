package com.example.visitrwanda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Historical extends AppCompatActivity {
    ImageView Gisozi,Ikibeho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        Gisozi= (ImageView)findViewById(R.id.imageView4);
        Ikibeho =(ImageView)findViewById(R.id.imageView6);

        Gisozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Gisozi.class));
            }
        });


        Ikibeho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getApplicationContext(),Nyanza.class));
            }
        });

    }

}