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
    private Button buttonQuestion,buttonMenu,buttonChat,buttonQuestionPage,buttonLeaderBoard,buttonMateria,buttonConfig;

    private QuestionsFragment questionsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonQuestion = findViewById(R.id.buttonQuestion);
        buttonMenu = findViewById(R.id.buttonMenu);
        buttonChat = findViewById(R.id.buttonChat);
        buttonConfig = findViewById(R.id.buttonConfig);
        buttonLeaderBoard = findViewById(R.id.buttonConfig);
        buttonMateria = findViewById(R.id.buttonMateria);
        buttonQuestionPage = findViewById(R.id.buttonQuestionPage);

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
                 //mudar aqui depois
            }
        });

        buttonQuestionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, questionsFragment);
                transaction.commit();
            }
        });

        questionsFragment = new QuestionsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, questionsFragment);
        transaction.commit();
    }
}