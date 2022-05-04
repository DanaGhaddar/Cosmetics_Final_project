package com.example.cosmetics_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class item_details extends AppCompatActivity {
TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        title=(TextView) findViewById(R.id.textView15);
        title.setText(getIntent().getStringExtra("itemname"));


    }
}