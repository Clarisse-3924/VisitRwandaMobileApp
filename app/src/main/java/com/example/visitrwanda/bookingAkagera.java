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

public class bookingAkagera extends AppCompatActivity {
    EditText Name1,email1,visitor,organization,phone1;
    Button Submit;
    TextView textView5;
    Akagerabooking guest;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    aka upload;
    String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_akagera);

        Name1 = (EditText)findViewById(R.id.Name1);
        email1 = (EditText)findViewById(R.id.email1);
        organization = (EditText)findViewById(R.id.organization);
        phone1= (EditText) findViewById(R.id.phone1);
        upload=new aka();

        recyclerView = findViewById(R.id.myrecycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Akagerabooking");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<aka> options =
                new FirebaseRecyclerOptions.Builder<aka>()
                        .setQuery(databaseReference,aka.class)
                        .build();
        FirebaseRecyclerAdapter<aka,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<aka, ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull aka model) {
                        holder.setData(getApplicationContext(),model.getName(),model.getOrganization()
                                ,model.getEmail(),model.getPhone());

                        holder.setOnClickListener(new ViewHolder.Clicklistener() {
                            @Override
                            public void onItemlongClick(View view, int position) {

                                name = getItem(position).getName();

                                showDeleteDataDialog(name);
                            }
                        });


                    }


                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.row,parent,false);

                        return new ViewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    private void showDeleteDataDialog(final String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(bookingAkagera.this);
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
                        Toast.makeText(bookingAkagera.this, "Data deleted", Toast.LENGTH_SHORT).show();
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
