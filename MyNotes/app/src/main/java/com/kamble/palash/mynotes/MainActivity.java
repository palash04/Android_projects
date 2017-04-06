package com.kamble.palash.mynotes;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.empty;

public class MainActivity extends AppCompatActivity {

    EditText titleTxt,noteTxt;
    //TextView dateTimeTxt;
    EmptyRecyclerView rv;
    MyAdapter adapter;
    RelativeLayout emptyView;
    ArrayList<Notes> notes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        //recycler
        //rv= (RecyclerView) findViewById(R.id.myRecycler);


//
//        DBAdapter db=new DBAdapter(this);
//        if (db.isEmpty()) {
//            rv.setVisibility(View.GONE);
//            emptyView.setVisibility(View.VISIBLE);
//        }
//        else {
//            rv.setVisibility(View.VISIBLE);
//            emptyView.setVisibility(View.GONE);
//        }

        //SET ITS PROPS
        // Replaced RecyclerView with EmptyRecyclerView
        rv =(EmptyRecyclerView) findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //View emptyView = v.findViewById(R.id.noteEmptyView);
        emptyView=(RelativeLayout) findViewById(R.id.noteEmptyView);
        rv.setEmptyView(emptyView);

        //ADAPTER
        adapter=new MyAdapter(this,notes);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        retrieve();



    }


    private void showDialog()
    {
        final Dialog d=new Dialog(this);

        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);


        //layout of dialog
        d.setContentView(R.layout.custom_layout);

        titleTxt= (EditText) d.findViewById(R.id.titleEditTxt);
        noteTxt= (EditText) d.findViewById(R.id.noteEditTxt);
        //dateTimeTxt=(TextView) d.findViewById(dateTimeTxt);
        ImageView savebtn= (ImageView) d.findViewById(R.id.saveBtn);
        //Button retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);


        //ONCLICK LISTENERS
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strTitile = titleTxt.getText().toString().trim();

                if(TextUtils.isEmpty(strTitile)) {
                    titleTxt.setError("Title required");
                    return;
                }

                String strNote = noteTxt.getText().toString().trim();

                if(TextUtils.isEmpty(strNote)) {
                    noteTxt.setError("Note required ");
                    return;
                }
                else {
                    save(titleTxt.getText().toString(), noteTxt.getText().toString());
                    d.dismiss();
                }
            }

        });


        //SHOW DIALOG
        d.show();


    }


    //SAVE
    private void save(String title,String note)
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        //INSERT
        long result=db.add(title,note);

        if(result>0)
        {
            titleTxt.setText("");
            noteTxt.setText("");
            Context context = getApplicationContext();
            CharSequence text = "Inserted";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            // Snackbar.make(nameTxt,"Inserted Successfully",Snackbar.LENGTH_SHORT).show();
        }else
        {

            Snackbar.make(titleTxt,"Unable To Insert",Snackbar.LENGTH_SHORT).show();
        }

        //CLOSE
        db.close();
        //closeDialog();


        //refresh
        retrieve();

    }

    //RETRIEVE
    private void retrieve()
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        notes.clear();

        //SELECT
        Cursor c=db.getAllPlayers();

        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String title=c.getString(1);
            String note=c.getString(2);

            //CREATE PLAYER
            Notes p=new Notes(title,note,id);

            //ADD TO PLAYERS
            notes.add(p);
        }

        //SET ADAPTER TO RV
        if(!(notes.size()<1))
        {
            rv.setAdapter(adapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieve();

    }

}

