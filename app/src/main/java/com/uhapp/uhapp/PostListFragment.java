package com.uhapp.uhapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class PostListFragment   extends Fragment {
    public static final String ARG_OBJECT = "object";

    public static ListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.post_list_fragment, container, false);

        final ListView listView = (ListView)rootView.findViewById(R.id.listView);


        ParseQuery<Post> query = new ParseQuery<Post>("Post");
        query.orderByDescending("likes");
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                PostListFragment.adapter = new ListAdapter(getActivity(), R.layout.post_layout, objects);
                listView.setAdapter(PostListFragment.adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int page = adapter.getItem(position).getPage();
                MainActivity.pager.setCurrentItem(0);
                PDFFragment.pdfView.jumpTo(page);
            }
        });

        return rootView;
    }
}