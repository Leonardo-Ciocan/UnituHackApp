package com.uhapp.uhapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Post> {
    public ListAdapter(Context context, int resource, List<Post> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.post_layout ,parent, false);
        }
        Log.v("cake", getItem(position).getText());

        ((TextView) convertView.findViewById(R.id.text)).setText(getItem(position).getText());
        ((TextView) convertView.findViewById(R.id.likes)).setText(getItem(position).getLikes() + " likes");
        ((TextView) convertView.findViewById(R.id.slide)).setText("At slide " + getItem(position).getPage());

        final ImageView like = (ImageView)convertView.findViewById(R.id.like);
        final View finalConvertView = convertView;
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem(position).increment("likes");
                getItem(position).saveInBackground();
                like.setImageResource(R.drawable.like);
                ((TextView) finalConvertView.findViewById(R.id.likes)).setText(getItem(position).getLikes() + " likes");
            }
        });
        return convertView;
    }
}
