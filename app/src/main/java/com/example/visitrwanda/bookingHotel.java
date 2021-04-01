package com.example.visitrwanda;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class bookingHotel extends AppCompatActivity {
    DatabaseReference reff;
    EditText Name,Email,Hotel,Room,visitor,Free,Special;
    Button Submit;
    BookHotel book;
    RecyclerView recyclerView;
    String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_hotel);
        Name = (EditText)findViewById(R.id.name);
        Email= (EditText)findViewById(R.id.email);
        Hotel= (EditText)findViewById(R.id.hotel);
        visitor = (EditText)findViewById(R.id.number);
        Room = (EditText)findViewById(R.id.room);
        Free= (EditText)findViewById(R.id.edit);
        Special= (EditText) findViewById(R.id.request);

        recyclerView = findViewById(R.id.myrecycleview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reff = FirebaseDatabase.getInstance().getReference().child("BookHotel");
        book=new  BookHotel();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<BookHotel> options =
                new FirebaseRecyclerOptions.Builder<BookHotel>()
                        .setQuery(reff,BookHotel.class)
                        .build();
        FirebaseRecyclerAdapter<BookHotel,ViewHolder6> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<BookHotel, ViewHolder6>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder6 holder, int position, @NonNull BookHotel model) {
                        holder.setData(getApplicationContext(),model.getName(),model.getEmail(),model.getHotel(),
                                model.getVisitor(),model.getRoom(),model.getFlight(),model.getFree(),model.getSpecial());

                        holder.setOnClickListener(new ViewHolder6.Clicklistener() {
                            @Override
                            public void onItemlongClick(View view, int position) {

                                name = getItem(position).getName();

                                showDeleteDataDialog(name);
                            }
                        });


                    }


                    @NonNull
                    @Override
                    public ViewHolder6 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.touristinglist,parent,false);

                        return new ViewHolder6(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    private void showDeleteDataDialog(final String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(bookingHotel.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you Sure to Delete this Data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Query query = reff.orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(bookingHotel.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}