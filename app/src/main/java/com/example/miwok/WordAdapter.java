package com.example.miwok;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {
    private int colorResId;

    public WordAdapter(@NonNull Context context, ArrayList<word> Words,int colorResId) {
        super(context, 0, Words);
        colorResId=colorResId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        word currentWord = getItem(position);
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_word);
        TextView engTextView = (TextView) listItemView.findViewById(R.id.Eng_word);
        miwokTextView.setText(currentWord.getMiwok());
        engTextView.setText(currentWord.getEnglish());
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.iconn);
        if(currentWord.hasImage())
        { Log.v("aa","aaaaaaa");
            iconView.setImageResource(currentWord.getRes_id());
        }
        else
        {

            iconView.setVisibility(View.GONE);
        }

        View textContainer= listItemView.findViewById(R.id.textContainer);
       // int color= ContextCompat.getColor(getContext(),colorResId);
      //  textContainer.setBackgroundColor( color);

        if (this.getContext() instanceof FamilyActivity) {
            textContainer.setBackgroundColor(Color.parseColor("#379237")); }
        else if (this.getContext() instanceof ColorsActivity)
        { textContainer.setBackgroundColor(Color.parseColor("#8800A0")); }
        else if (this.getContext() instanceof PhrasesActivity)
        { textContainer.setBackgroundColor(Color.parseColor("#16AFCA")); }
        else if (this.getContext() instanceof NumbersActivity) {
            textContainer.setBackgroundColor(Color.parseColor("#FD8E09"));
        }


        return listItemView;
    }
}
