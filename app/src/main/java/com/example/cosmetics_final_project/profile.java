package com.example.cosmetics_final_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class profile extends AppCompatActivity {
    TextView name,age,loc;
    Button delete,backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView) findViewById(R.id.namepr2);
        age=(TextView) findViewById(R.id.agepr2);
        loc=(TextView) findViewById(R.id.locpr2);
        delete=(Button) findViewById(R.id.deletepurchases);
        backhome=(Button)findViewById(R.id.deletepurchases2) ;
        name.setText(getIntent().getStringExtra("logged_user"));
        String url="http://192.168.0.109/CosmeticsApp/profile.php";
        DownloadTask t=new DownloadTask();
        t.execute(url);
backhome.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent inn=new Intent(getApplicationContext(),Home.class);
        inn.putExtra("logged_user",getIntent().getStringExtra("logged_user"));
        startActivity(inn);
    }
});
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url1="http://192.168.0.109/CosmeticsApp/delete_purchases.php?name="+getIntent().getStringExtra("logged_user");
                DeleteTask task=new DeleteTask();
                task.execute(url1);
            }
        });

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






    public class DeleteTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int data = reader.read();

                while( data != -1){
                    ;
                    result = result+ reader.readLine();
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
                JSONObject json = new JSONObject(s);
                String access = json.getString("error");
                if(access.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "All you purchases have been deleted", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}