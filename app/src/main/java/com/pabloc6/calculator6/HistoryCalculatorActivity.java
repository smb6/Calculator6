package com.pabloc6.calculator6;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class HistoryCalculatorActivity extends ActionBarActivity {

    private static final String TAG = "DEBUG->MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calculator);

        final String TAG_LOCAL = TAG + ".onCreate";
        Log.d(TAG_LOCAL, "IN");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        final String TAG_LOCAL = TAG + ".onOptionsItemSelected";
        Log.d(TAG_LOCAL, "IN");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.i_backToMain) {
            setResult(3); // TODO: Make parameter
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
