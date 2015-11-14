package com.uhapp.uhapp;

import android.content.DialogInterface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    int page = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "oWQn7NBtFgxM8OyvhImlre2otI1vPdNLeI3EnEM4", "BmrejXyRhfN2slojJoD0UOIfKd2uxk9AWljZbz4M");
        ParseObject.registerSubclass(Post.class);

        try {
            ParseUser.logIn("leonardo","cake");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("AFL (Live)");
        pager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

        TabLayout appBarLayout = (TabLayout) findViewById(R.id.toolbarLayout);
        appBarLayout.setupWithViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
