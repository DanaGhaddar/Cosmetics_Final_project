package com.example.cosmetics_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class product_info extends AppCompatActivity {
    ListView list;
    TextView t;
    ArrayList<String> listarray;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        list=(ListView) findViewById(R.id.myList);
        t=(TextView)findViewById(R.id.textView7) ;
        t.setText(getIntent().getStringExtra("category")+" Category");
        listarray= new ArrayList<String>();
        String url = "http://10.21.145.110/CosmeticsApp/products_info.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);


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

                    if(jsonobj.getString("category").equalsIgnoreCase(getIntent().getStringExtra("category"))){
                        listarray.add(jsonobj.getString("name"));

                    }
                }

                adapter=new ArrayAdapter<String>(getApplication(), R.layout.text_color_layout,listarray);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in=new Intent(getApplication(),item_details.class);
                        in.putExtra("itemname",  listarray.get(i));
                        in.putExtra("logged_user", getIntent().getStringExtra("logged_user"));
                        startActivity(in);

                    }
                });

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}