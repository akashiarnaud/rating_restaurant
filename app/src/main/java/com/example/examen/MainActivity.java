package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // get element
    // button give advice
    Button mButtonGiveAdvice;
    // button look advice
    Button mButtonLookAdvice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get those widgets

        mButtonGiveAdvice = findViewById(R.id.goToForm);
        mButtonLookAdvice = findViewById(R.id.goToList);
        // get intent of formulaire activity
        Intent formIntent = new Intent(this, FormulaireActivity.class);
        Intent listIntent = new Intent(this, ListActivity.class);
        mButtonGiveAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(formIntent);
            }
        });
        mButtonLookAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(listIntent);
            }
        });
    }
}