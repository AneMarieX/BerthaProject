package com.project.berthaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.USERNAME);


        TextView listHeader = new TextView(this);
        listHeader.setText("Measurements Data ");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);
        ListView listView = findViewById(R.id.main_dates_listview);
        listView.addHeaderView(listHeader);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("https://berthabackendrestprovider.azurewebsites.net/api/data/anbo");
    }

    public void addData(View view) {
        Intent intent = new Intent(this, AddDataActivity.class);
        startActivity(intent);
    }

    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
            Gson gson = new GsonBuilder().create();
            final Data[] dates = gson.fromJson(jsonString.toString(), Data[].class);

            ListView listView = findViewById(R.id.main_dates_listview);

            DataListItemAdapter adapter = new DataListItemAdapter(getBaseContext(), R.layout.activity_data_list_item_adapter, dates);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), DataActivity.class);
                    Data data = (Data) parent.getItemAtPosition(position);
                    intent.putExtra(DataActivity.DATA, data);
                    startActivity(intent);
                }
            });

        }

        @Override
        protected void onCancelled(CharSequence message) {
            TextView messageTextView = findViewById(R.id.main_message_textview);
            messageTextView.setText(message);
            Log.e("DATA", message.toString());
        }

    }


}


