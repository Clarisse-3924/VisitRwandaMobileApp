package com.example.visitrwanda;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class bookingGisozi extends AppCompatActivity {
    EditText Name1,email1,organization,phone1;
    Button Submit;
    TextView textView5;
    Akagerabooking guest;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    Booking upload;
    String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_gisozi);
        Name1 = (EditText)findViewById(R.id.Name1);
        email1 = (EditText)findViewById(R.id.email1);
        organization = (EditText)findViewById(R.id.organization);
        phone1= (EditText) findViewById(R.id.phone1);
        upload=new Booking();

        recyclerView = findViewById(R.id.myrecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference().child("kibeho");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Booking> options =
                new FirebaseRecyclerOptions.Builder<Booking>()
                        .setQuery(databaseReference,Booking.class)
                        .build();
        FirebaseRecyclerAdapter<Booking,ViewHolder2> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Booking, ViewHolder2>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder2 holder, int position, @NonNull Booking model) {
                        holder.setData(getApplicationContext(),model.getName(),model.getOrganization()
                                ,model.getEmail(),model.getPhone());

                        holder.setOnClickListener(new ViewHolder2.Clicklistener() {
                            @Override
                            public void onItemlongClick(View view, int position) {

                                name = getItem(position).getName();

                                showDeleteDataDialog(name);
                            }
                        });


                    }


                    @NonNull
                    @Override
                    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.guestlist,parent,false);

                        return new ViewHolder2(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    private void showDeleteDataDialog(final String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(bookingGisozi.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you Sure to Delete this Data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Query query = databaseReference.orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(bookingGisozi.this, "Data deleted", Toast.LENGTH_SHORT).show();
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