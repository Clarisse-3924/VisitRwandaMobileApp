package com.example.visitrwanda;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setData(Context applicationContext, String name, String organization, String email, String phone) {
        TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("Name: " + name + "\n"  + "Email: "  + email +"\n"+ "organization:" +organization + "\n" + "Phone:" +phone +"\n" );

    }
    private ViewHolder.Clicklistener mClicklistener;
    public interface Clicklistener{
        void onItemlongClick(View view , int position);
    }

    public void setOnClickListener(ViewHolder.Clicklistener clickListener){
        mClicklistener = (ViewHolder.Clicklistener) clickListener;
    }

}