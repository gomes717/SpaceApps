package com.example.spaceapps.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spaceapps.R;
import com.example.spaceapps.fragment.QuestionsFragment;

public class MainActivity extends AppCompatActivity {
    private Button buttonQuestion,buttonMenu;
    private QuestionsFragment questionsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonQuestion = findViewById(R.id.buttonQuestion);
        buttonMenu = findViewById(R.id.buttonMenu);

        buttonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intent);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(intent);
            }
        });

        questionsFragment = new QuestionsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameConteudo, questionsFragment);
        transaction.commit();

    }
}