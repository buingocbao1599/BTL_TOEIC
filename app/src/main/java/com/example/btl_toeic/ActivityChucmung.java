package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityChucmung extends AppCompatActivity {
    TextView txtChucMung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucmung);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        txtChucMung=findViewById(R.id.txtChucMung);
        Intent intent =getIntent();
        String chucmung = intent.getStringExtra("CHUCMUNG");
        txtChucMung.setText(chucmung);
    }
}