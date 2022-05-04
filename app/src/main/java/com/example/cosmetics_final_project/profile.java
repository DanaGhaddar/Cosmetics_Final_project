package com.example.cosmetics_final_project;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class profile extends AppCompatActivity {
    TextView name,age,loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView) findViewById(R.id.namepr2);
        age=(TextView) findViewById(R.id.agepr2);
        loc=(TextView) findViewById(R.id.locpr2);
        name.setText(getIntent().getStringExtra("logged_user"));
        String url="http://192.168.0.109/CosmeticsApp/profile.php";
        DownloadTask t=new DownloadTask();
        t.execute(url);
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
                    if(jsonobj.getString("name").equalsIgnoreCase(getIntent().getStringExtra("logged_user"))){
                        loc.setText(jsonobj.getString("location"));
                        age.setText(jsonobj.getString("age"));

                    }
                }


            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}