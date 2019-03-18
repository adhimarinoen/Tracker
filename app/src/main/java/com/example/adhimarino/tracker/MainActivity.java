package com.example.adhimarino.tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 Button mjalur3a, mjalur3b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mjalur3a = (Button) findViewById(R.id.jalur3a);
        mjalur3b = (Button) findViewById(R.id.jalur3b);

        mjalur3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),jalur3a.class);
                startActivity(i);
            }
        });

        mjalur3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(getApplicationContext(),jalur3b.class);
                startActivity(i);
            }
        });
    }
}


