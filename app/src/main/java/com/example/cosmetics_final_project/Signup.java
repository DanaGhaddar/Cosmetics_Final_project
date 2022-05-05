package com.example.cosmetics_final_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup extends AppCompatActivity {
    Button signup;
    EditText name,age,location,password;
    TextView tologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(Button) findViewById(R.id.submitsu);
        tologin=(TextView)findViewById(R.id.textView4);
        name=(EditText)findViewById(R.id.namesu);
        age=(EditText)findViewById(R.id.agesu);
        location=(EditText)findViewById(R.id.locationsu);
        password=(EditText)findViewById(R.id.passwordsu);

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String namestr=name.getText().toString();
                String agestr=age.getText().toString();
                String locationstr=location.getText().toString();
                String pass=password.getText().toString();
                String url = "http://10.21.145.110/CosmeticsApp/signUp.php?name="+namestr+"&age="+agestr+"&location="+locationstr+"&password="+pass;
                DownloadTask task=new DownloadTask();
                task.execute(url);
            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
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