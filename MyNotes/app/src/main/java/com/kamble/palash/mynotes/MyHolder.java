package com.kamble.palash.mynotes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Palash on 03/04/17.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView titleTxt,noteTxt;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);
        titleTxt= (TextView) itemView.findViewById(R.id.titleTxt);
        noteTxt= (TextView) itemView.findViewById(R.id.noteTxt);

        itemView.setOnClickListener(this);
    }

    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
