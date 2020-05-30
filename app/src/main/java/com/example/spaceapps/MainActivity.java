package com.example.spaceapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{

    private ListView listQuestoes;
    private String[] respostas = {
            "a","b","c","d"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adaptador de lista
        ArrayAdapter<String> adaptadorList = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                respostas
        );

        // ativa o adaptador
        listQuestoes.setAdapter(adaptadorList);

    }
}