package com.example.cosmetics_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class item_details extends AppCompatActivity {
TextView title,origin,descr,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        title=(TextView) findViewById(R.id.textView15);
        origin=(TextView) findViewById(R.id.country);
        descr=(TextView) findViewById(R.id.desc);
        price=(TextView) findViewById(R.id.price);
        title.setText(getIntent().getStringExtra("itemname"));


    }





    public class DownloadTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while( data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{

                JSONArray jsonarray = new JSONArray(s);
                for (int i=0; i< jsonarray.length();i++){
                    JSONObject jsonobj=jsonarray.getJSONObject(i);

                    if(jsonobj.getString("name").equalsIgnoreCase(getIntent().getStringExtra("itemname"))){
                        origin.setText(jsonobj.getString("origin"));
                        descr.setText(jsonobj.getString("description"));
                        price.setText(jsonobj.getString("price"));
                    }
                }


            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}