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

public class Hotel extends AppCompatActivity {
    DatabaseReference reff;
    TextView imageView24;
    EditText Name,Email,Hotel,Room,visitor,flight,Arrive,Time,Departure,Dtime,Free,Special;
    Button Submit;
    BookHotel book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        Name = (EditText)findViewById(R.id.name);
        Email= (EditText)findViewById(R.id.email);
        Hotel= (EditText)findViewById(R.id.hotel);
        visitor = (EditText)findViewById(R.id.number);
        Room = (EditText)findViewById(R.id.room);
        flight = (EditText)findViewById(R.id.Number2);
        Free= (EditText)findViewById(R.id.edit);
        Special= (EditText) findViewById(R.id.request);
        imageView24 =findViewById(R.id.textView26);
        reff = FirebaseDatabase.getInstance().getReference().child("BookHotel");
        Submit = (Button) findViewById(R.id.button6);
        book=new  BookHotel();
        imageView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Hotels.class));
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setName(Name.getText().toString().trim());
                book.setEmail(Email.getText().toString().trim());
                book.setHotel(Hotel.getText().toString().trim());
                book.setVisitor(visitor.getText().toString().trim());
                book.setRoom(Room.getText().toString().trim());
                book.setFlight(flight.getText().toString().trim());
                book.setFree(Free.getText().toString().trim());
                book.setSpecial(Special.getText().toString().trim());


                String name= Name.getText().toString().trim();
                String email= Email.getText().toString().trim();
                String hotel= Hotel.getText().toString().trim();
                String visitors= visitor.getText().toString().trim();
                String room= Room.getText().toString().trim();
                String Flight= flight.getText().toString().trim();
                String free= Free.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Name.setError("Name required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Email.setError("Email required");
                    return;
                }
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()){
                    Email.setError("Enter Valid email");
                    return;
                }
                if(TextUtils.isEmpty(hotel)){
                    Hotel.setError("Hotel name required");
                    return;
                }

                if(TextUtils.isEmpty(visitors)){
                    visitor.setError("Number of Visitors required");
                    return;
                }
                if(TextUtils.isEmpty(room)){
                    Room.setError("Type of room required");
                    return;
                }
                if(TextUtils.isEmpty(Flight)){
                    flight.setError("required");
                    return;
                }

                if(TextUtils.isEmpty(free)){
                    Free.setError("required");
                    return;
                }
                if(Flight.length()!=4){
                    flight.setError("flight number must be four");
                    return;
                }

                String id = reff.push().getKey();
                reff.child(id).setValue(book);
                Toast.makeText(Hotel.this, "Data saved", Toast.LENGTH_SHORT).show();
                Toast.makeText(Hotel.this, "THANK YOU FOR BOOKING", Toast.LENGTH_SHORT).show();
            }
        });







    }
}