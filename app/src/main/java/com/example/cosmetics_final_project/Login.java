package com.example.cosmetics_final_project;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {
    Button lgn;
    EditText name,password;
    TextView tosignup;

    public static byte[] makeSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String makeHex(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lgn=(Button) findViewById(R.id.submitsu);
        tosignup=(TextView) findViewById(R.id.textView4);
        name=(EditText) findViewById(R.id.namesu);
        password=(EditText) findViewById(R.id.passwordsu);
        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.0.109/CosmeticsApp/Login.php";

                DownloadTask task = new DownloadTask();
                task.execute(url);
            }
        });

        tosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
                String check="";
                JSONArray jsonarray = new JSONArray(s);
                for (int i=0; i< jsonarray.length();i++){
                    JSONObject jsonobj=jsonarray.getJSONObject(i);
                    if(jsonobj.getString("name").equalsIgnoreCase(name.getText().toString())){
                        check = jsonobj.getString("password");
                        String hashedpass=makeHex(makeSHA(password.getText().toString()));
                        if(check.equals(hashedpass)){
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("logged_user",name.getText().toString());
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error in your password or name", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}