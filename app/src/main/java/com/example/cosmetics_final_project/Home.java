package com.example.cosmetics_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView profilebtn,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profilebtn=(ImageView) findViewById(R.id.imageView19);

         image = (ImageView)findViewById(R.id.imageView13);
        image.setX(-1000);
        image.animate().translationXBy(1000).rotation(3600).setDuration(2000);




        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),profile.class);
                intent.putExtra("logged_user", getIntent().getStringExtra("logged_user"));
                startActivity(intent);
            }
        });
    }
    public void lipsticks(View view) {
        Intent i = new Intent(getApplicationContext(), product_info.class);
        i.putExtra("logged_user", getIntent().getStringExtra("logged_user"));
        i.putExtra("category","lipsticks");
        startActivity(i);
    }
    public void creams(View view) {
        Intent i = new Intent(getApplicationContext(), product_info.class);
        i.putExtra("logged_user", getIntent().getStringExtra("logged_user"));
        i.putExtra("category", "creams");
        startActivity(i);
    }
    public void powders(View view) {
        Intent i = new Intent(getApplicationContext(), product_info.class);
        i.putExtra("logged_user", getIntent().getStringExtra("logged_user"));
        i.putExtra("category", "powders");
        startActivity(i);
    }
    public void gels(View view) {
        Intent i = new Intent(getApplicationContext(), product_info.class);
        i.putExtra("logged_user", getIntent().getStringExtra("logged_user"));
        i.putExtra("category", "gels");

        startActivity(i);
    }
}