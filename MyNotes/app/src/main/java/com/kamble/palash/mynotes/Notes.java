package com.kamble.palash.mynotes;

import static android.R.attr.id;

/**
 * Created by Palash on 03/04/17.
 */

public class Notes {

    private String title,note;
    private int id;

    public Notes(String title,String note,int id){
        this.title = title;
        this.note = note;
        this.id = id;

    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
