package com.pabloc6.calculator6;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AboutCalculatorActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = "DEBUG->AboutCalculatorActivity";
    private Button btn_aboutDismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_calculator);

        // Remove the UP navigation.
        // TODO - why crashes the app?
//        getActionBar().setDisplayHomeAsUpEnabled(false);

        final String TAG_LOCAL = TAG + ".onCreate";
        Log.d(TAG_LOCAL, "IN");

        btn_aboutDismiss = (Button) findViewById(R.id.btn_aboutDismiss);
        btn_aboutDismiss.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_calculator, menu);
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

    @Override
    public void onClick(View v) {
        final String TAG_LOCAL = TAG + ".onClick";
        Log.d(TAG_LOCAL, "IN");

        if (v == btn_aboutDismiss) {
            setResult(6); // TODO: Make parameter
            finish();


        }

    }

}
