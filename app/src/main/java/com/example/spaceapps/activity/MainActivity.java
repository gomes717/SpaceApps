package com.example.spaceapps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.spaceapps.R;
import com.example.spaceapps.fragment.RankingFragment;
import com.example.spaceapps.fragment.ChatFragment;
import com.example.spaceapps.fragment.MenuFragment;
import com.example.spaceapps.fragment.QuestionsFragment;

public class MainActivity extends AppCompatActivity {
    private Button  buttonQuestion,
                    buttonMenu,
                    buttonChat,
                    buttonQuestionPage,
                    buttonLeaderBoard,
                    buttonConfig;

    private QuestionsFragment questionsFragment;
    private MenuFragment menuFragment;
    private ChatFragment chatFragment;
    private RankingFragment rankingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //implementacao botoes
        buttonQuestion = findViewById(R.id.buttonQuestion);
        buttonMenu = findViewById(R.id.buttonMenu);
        buttonChat = findViewById(R.id.buttonChat);
        buttonConfig = findViewById(R.id.buttonConfig);
        buttonLeaderBoard = findViewById(R.id.buttonLeaderBoard);
        buttonQuestionPage = findViewById(R.id.buttonQuestionPage);

        buttonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intent);
                update();
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, menuFragment);
                transaction.commit();
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

        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, chatFragment);
                transaction.commit();
            }
        });

        buttonLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, rankingFragment);
                transaction.commit();
            }
        });

        buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(intent);
                update();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////
        //implementacao fragmentos
        chatFragment = new ChatFragment();
        menuFragment = new MenuFragment();
        questionsFragment = new QuestionsFragment();
        rankingFragment = new RankingFragment();
        //aqui seta o fragmento que vai comecar no app
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, questionsFragment);
        transaction.commit();

    }


    private void update()
    {}
}