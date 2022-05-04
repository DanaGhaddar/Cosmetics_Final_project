package com.example.cosmetics_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class item_details extends AppCompatActivity {
TextView title,origin,descr,price;
Button purchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        title=(TextView) findViewById(R.id.textView15);
        origin=(TextView) findViewById(R.id.country);
        descr=(TextView) findViewById(R.id.desc);
        price=(TextView) findViewById(R.id.price);
        purchase=(Button)findViewById(R.id.button);
        title.setText(getIntent().getStringExtra("itemname"));
        String url = "http://192.168.0.109/CosmeticsApp/products_info.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);


        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url2 = "http://192.168.0.109/CosmeticsApp/purchases.php";
                PurchaseTask ptask=new PurchaseTask();
                ptask.execute(url2);
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








    public class PurchaseTask extends AsyncTask<String, Void, String> {

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
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    intent.putExtra("logged_user",name.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}