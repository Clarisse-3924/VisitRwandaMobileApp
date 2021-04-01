package com.example.visitrwanda;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Signup extends AppCompatActivity {
    CardView agriculture, weather, disasters, air_pollution;
    DrawerLayout drawerLayout;
    ViewFlipper fli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        drawerLayout = findViewById(R.id.drawer_layout);

        int images[]={R.drawable.visit, R.drawable.visit1,R.drawable.visit2,R.drawable.visit7,R.drawable.visit10,R.drawable.visit8};
        fli = findViewById(R.id.fli);

        agriculture = findViewById(R.id.agriculture);
        air_pollution = findViewById(R.id.air_pollution);
        weather = findViewById(R.id.weather);
        disasters = findViewById(R.id.disasters);

        for(int image:images){
            flipperImages(image);
        }



        agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Nyungwe.class));
            }
        });


        air_pollution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Signup.this, akagera.class));

            }
        });

        disasters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Signup.this, Hotels.class));

            }
        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Signup.this,Historical.class));

            }
        });
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView( this);
        imageView.setBackgroundResource(image);
        fli.addView(imageView);
        fli.setFlipInterval(2500);
        fli.setAutoStart(true);

        fli.setInAnimation(this,android.R.anim.slide_in_left);
        fli.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    public void ClickMenu(View view){
        //open drawe
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        // open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawe
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        //recreate activity
        recreate();
    }
    public void ClickAdmin(View view){
        //redirect to dashborad
        Signup.redirectActivity(this,AdminLogin.class);
    }

    public void ClickTourists(View view){
        //redirect about us
        Signup.redirectActivity(this,Historical.class);
    }
    public void ClickSignUp(View view){
        //redirect about us
        Signup.redirectActivity(this,Signup.class);
    }
    public void ClickLogin(View view){
        //redirect about us
        Signup.redirectActivity(this,Login.class);
    }

    public void ClickLogout(View view){
        //close app
        logout(this);
    }

    public static void logout(final Activity activity) {
        //initialize dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Lougout");
        //set meassage
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //exit app
                System.exit(0);
            }
        });
        //negative button
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aclass) {
        //Initialize intent
        Intent intent = new Intent(activity,aclass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }
}