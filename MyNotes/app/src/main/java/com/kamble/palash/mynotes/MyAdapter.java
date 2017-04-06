package com.kamble.palash.mynotes;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Palash on 03/04/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Notes> notes;
    RecyclerView rv;

    public MyAdapter(Context ctx,ArrayList<Notes> notes)
    {
        //ASSIGN THEM LOCALLY
        this.c=ctx;
        this.notes=notes;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        //holder
        MyHolder holder=new MyHolder(v);

        return holder;

    }

    //BIND DATA TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.titleTxt.setText(notes.get(position).getTitle());
        holder.noteTxt.setText(notes.get(position).getNote());

        //HANDLE ITEMCLICKS
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i=new Intent(c,DetailActivity.class);

                //LOAD DATA
                i.putExtra("TITLE",notes.get(pos).getTitle());
                i.putExtra("NOTE",notes.get(pos).getNote());
                i.putExtra("ID",notes.get(pos).getId());

                //START ACTIVITY
                c.startActivity(i);

            }
        });

    }


    @Override
    public int getItemCount() {
        return notes.size();
    }


    // Swipe on delete
    public void remove(int position) {
        if (position < 0 || position >= notes.size()) {
            return;
        }
        notes.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }


    public void swap(int firstPosition, int secondPosition){
        Collections.swap(notes, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

}

