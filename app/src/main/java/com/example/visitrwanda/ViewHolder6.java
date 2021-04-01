package com.example.visitrwanda;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder6 extends RecyclerView.ViewHolder {


    public ViewHolder6(@NonNull View itemView) {
        super(itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    private ViewHolder6.Clicklistener mClicklistener;

    public void setData(Context applicationContext, String name, String email, String hotel, String visitor, String room, String flight, String free, String special) {
        TextView textView = itemView.findViewById(R.id.tu);

        textView.setText("Name: " + name + "\n"  + "Email: "  + email +"\n"+ "Hotel: " + hotel + "Visitors:" +visitor +"\n" + "Room:" + room +"\n" + "Flight:" +flight +"\n" + "\n" + "Free" + free + "\n" + " Special Request:" + special);
    }

    public interface Clicklistener{
        void onItemlongClick(View view , int position);
    }

    public void setOnClickListener(ViewHolder6.Clicklistener clickListener){
        mClicklistener = (Clicklistener) clickListener;
    }

}