package com.uhapp.uhapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.parse.ParseUser;

public class PDFFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    static public PDFView pdfView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.pdffragment, container, false);

        pdfView = (PDFView) rootView.findViewById(R.id.pdfview);

        pdfView.fromAsset("test.pdf")
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .swipeVertical(true)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int i, int i1) {

                    }
                })
                .load();




        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialoglayout = inflater.inflate(R.layout.new_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Post post = new Post();
                        post.setPage(pdfView.getCurrentPage());
                        post.setText(((EditText) dialoglayout.findViewById(R.id.post_text)).getText().toString());
                        post.setUser(ParseUser.getCurrentUser());
                        post.saveInBackground();
                        PostListFragment.adapter.add(post);
                        dialog.dismiss();
                    }
                });
                builder.setView(dialoglayout);
                builder.show();
            }
        });

        return rootView;
    }
}