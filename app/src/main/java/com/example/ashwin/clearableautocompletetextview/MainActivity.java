package com.example.ashwin.clearableautocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView text;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (AutoCompleteTextView) findViewById(R.id.text);

        clear = (Button) findViewById(R.id.clear);

        clear.setVisibility(View.INVISIBLE);

        //close button visibility for manual typing
        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    clear.setVisibility(View.VISIBLE);
                } else {
                    clear.setVisibility(View.GONE);
                }
            }
        });

        //sample array
        String[] array = {"alpha", "beta", "cupcake", "donut", "eclair", "froyo", "gingerbread", "honeycomb", "icecreamsandwich", "jellybean", "kitkat", "lollipop", "marshmallow"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array);

        text.setThreshold(1);       //will start working from first character

        text.setAdapter(adapter);   //setting the adapter data into the AutoCompleteTextView

        //Shows drop down list on touch
        text.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                text.showDropDown();
                return false;
            }
        });

        //close button visibility for autocomplete text view selection
        text.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                clear.setVisibility(View.GONE);
            }

        });

    }

    public void clear(View view) {
        text.setText("");
        clear.setVisibility(View.GONE);
    }
}
