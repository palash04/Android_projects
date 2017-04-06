package com.kamble.palash.mynotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.R.attr.name;

/**
 * Created by Palash on 03/04/17.
 */

public class DetailActivity extends AppCompatActivity {

    EditText titleTxt, noteTxt;
    // TextView dateTimeTxt;
    ImageView updateBtn;
    ImageView deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        final String title = i.getExtras().getString("TITLE");
        final String note = i.getExtras().getString("NOTE");
        //final String time = i.getExtras().getString("CURRENT_TIMESTAMP");
        final int id = i.getExtras().getInt("ID");

        updateBtn = (ImageView) findViewById(R.id.updateBtn);
        deleteBtn = (ImageView) findViewById(R.id.deleteBtn);
        titleTxt = (EditText) findViewById(R.id.titleEditTxt);
        noteTxt = (EditText) findViewById(R.id.noteEditTxt);
        // dateTimeTxt= (TextView) findViewById(dateTimeTxt);

        //ASSIGN DATA TO THOSE VIEWS
        titleTxt.setText(title);
        noteTxt.setText(note);
        // dateTimeTxt.setText(time);

        //update
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strTitile = titleTxt.getText().toString().trim();

                if(TextUtils.isEmpty(strTitile)) {
                    titleTxt.setError("Title Required");
                    return;
                }

                String strNote = noteTxt.getText().toString().trim();

                if(TextUtils.isEmpty(strNote)) {
                    noteTxt.setError("Note required");
                    return;
                }
                else {
                    update(id, titleTxt.getText().toString(), noteTxt.getText().toString());
                }
            }
        });

        //DELETE
        //update
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });
    }

    private void update(int id, String newTitle, String newNote) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        long result = db.UPDATE(id, newTitle, newNote);

        if (result > 0) {
            titleTxt.setText(newTitle);
            noteTxt.setText(newNote);
            //Snackbar.make(nameTxt,"Updated Sucesfully",Snackbar.LENGTH_LONG).show();
            Context context = getApplicationContext();
            CharSequence text = "Note Updated";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            Snackbar.make(titleTxt, "Unable to Update", Snackbar.LENGTH_SHORT).show();
        }

        db.close();
        finish();
    }

    //DELETE
    private void delete(int id) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        long result = db.Delete(id);

        if (result > 0) {
            this.finish();
            Context context = getApplicationContext();
            CharSequence text = "Note Deleted";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            Snackbar.make(titleTxt, "Unable to Update", Snackbar.LENGTH_SHORT).show();
        }

        db.close();
        finish();
    }
}