package com.vijaygaike.unitconverter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.vijaygaike.unitconverter.R;
import com.vijaygaike.unitconverter.databinding.ActivityMainBinding;

import java.sql.SQLInput;
import java.util.ArrayList;

public class ShowHistory extends Activity {
    ListView listView;
    ArrayList <String> arr = new ArrayList<>();
    ImageButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showhistory);
        //Intent intent = getIntent();

        button = findViewById(R.id.deleteButton);
        listView = findViewById(R.id.listView);
        DBHandler dbHandler = new DBHandler(this);
        function(dbHandler);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.delete_history();
                Cursor t = dbHandler.showHistory();
                StringBuilder buffer = new StringBuilder();
                ArrayList <String> nullarr = new ArrayList<>();
                //nullarr.add("");
                ArrayAdapter<String> ad1 = new ArrayAdapter<>(ShowHistory.this, android.R.layout.simple_list_item_1,nullarr);
                listView.setAdapter(ad1);
                Toast.makeText(ShowHistory.this, "History deleted...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void function(DBHandler dbHandler){
        //DBHandler dbHandler = new DBHandler(this);
        Cursor t = dbHandler.showHistory();
        if (t.getCount() == 0){
            Toast.makeText(this, "No history present...", Toast.LENGTH_SHORT).show();
            Log.d("VIJAY", "onCreate: No history present.");
        }
        StringBuilder buffer = new StringBuilder();
        while (t.moveToNext()){
            buffer.append("\n").append(t.getString(0)).append("\n");
            arr.add(buffer.toString());
            buffer.delete(0,buffer.length());
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(ad);
    }
}
