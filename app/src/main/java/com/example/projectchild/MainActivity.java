package com.example.projectchild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int valutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView valute;
        //valute = findViewById(R.id.valutee);
        //SharedPreferences sharedPreferences = getSharedPreferences("Reward", Context.MODE_PRIVATE);
        //valutes = sharedPreferences.getInt("valutes", 0);
        //valute.setText(String.valueOf(valutes));
        Button buteng = findViewById(R.id.buteng);
        Button butrus = findViewById(R.id.butrus);
        Button butpaint = findViewById(R.id.butpaint);
        Button butmath = findViewById(R.id.butmath);
        buteng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, English.class);
                startActivity(intent);
            }
        });
        butmath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mathem.class);
                startActivity(intent);
            }
        });
        butpaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Paintt.class);
                startActivity(intent);
            }
        });
        butrus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Russian.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("Reward", Context.MODE_PRIVATE);
        valutes = sharedPreferences.getInt("valutes", 0);
        TextView valute = findViewById(R.id.valutee);
        valute.setText(String.valueOf(valutes));
    }




}