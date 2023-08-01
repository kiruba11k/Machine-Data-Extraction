package com.example.ssp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        int sum = getIntent().getIntExtra("CountM", 0);
        TextView sumView = findViewById(R.id.sumbm);
        sumView.setText(String.valueOf(sum));

        double average1 = getIntent().getDoubleExtra("average1", 0.0);
        double average2 = getIntent().getDoubleExtra("average2", 0.0);
        TextView avgView1 = findViewById(R.id.avgpw);
        TextView avgView2 = findViewById(R.id.avgic);
        avgView1.setText(String.valueOf(average1));
        avgView2.setText(String.valueOf(average2));

        int count = getIntent().getIntExtra("Count", 0);
        TextView countView = findViewById(R.id.cmc);
        countView.setText(String.valueOf(count));




    }
}