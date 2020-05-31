package com.example.spaceapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

import com.example.spaceapps.R;

public class ConfigActivity extends AppCompatActivity {
    private Switch soundSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        soundSwitch = findViewById(R.id.soundSwitch);

    }
}