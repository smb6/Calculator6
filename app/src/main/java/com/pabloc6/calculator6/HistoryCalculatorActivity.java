package com.pabloc6.calculator6;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HistoryCalculatorActivity extends ActionBarActivity {

    MyApplication app;
    private ListView myListView;

    private static final String TAG = "DEBUG->MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calculator);

        final String TAG_LOCAL = TAG + ".onCreate";
        Log.d(TAG_LOCAL, "IN");

        app = (MyApplication) getApplication();

        displayHistory();

//        String[] values = {"1+1=2", "0.9+1.1=2", "3*7=27"};
       /* ArrayList<String> valueArray = app.sqlUtils.getNames();
        // TODO - set 20 value to parameter
        int startIndex = 0;
        if (valueArray.size() > 20) {
            startIndex = valueArray.size() - 20;
        }

        List<String> subList = valueArray.subList(startIndex, valueArray.size());

        myListView = (ListView) findViewById(R.id.lv_history);
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, subList); // valueArray

        myListView.setAdapter(adapter);*/
    }

    public void displayHistory() {

        ArrayList<String> valueArray = app.sqlUtils.getNames();
        int startIndex = 0;
        if (valueArray.size() > 20) {
            startIndex = valueArray.size() - 20;
        }

        List<String> subList = valueArray.subList(startIndex, valueArray.size());

        myListView = (ListView) findViewById(R.id.lv_history);
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, subList); // valueArray

        myListView.setAdapter(adapter);
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
        } else if (id == R.id.i_clearHistory) {
            app.sqlUtils.deleteTable();
            Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show();
            displayHistory();
        }


        return super.onOptionsItemSelected(item);
    }
}
