package com.example.spaceapps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.spaceapps.R;
import com.example.spaceapps.fragment.RankingFragment;
import com.example.spaceapps.fragment.MenuFragment;
import com.example.spaceapps.fragment.QuestionsFragment;
import com.example.spaceapps.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private ImageButton buttonMenu,
                    buttonChat,
                    buttonQuestionPage,
                    buttonLeaderBoard,
                    buttonConfig,
                    buttonSearch;

    private Button  buttonQuestion;

    private QuestionsFragment questionsFragment;
    private MenuFragment menuFragment;
    private RankingFragment rankingFragment;
    private SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(intent);



        //implementacao botoes
        buttonQuestion = findViewById(R.id.buttonQuestion);
        buttonMenu = findViewById(R.id.buttonMenu);
        buttonChat = findViewById(R.id.buttonChat);
        buttonConfig = findViewById(R.id.buttonConfig);
        buttonLeaderBoard = findViewById(R.id.buttonLeaderBoard);
        buttonQuestionPage = findViewById(R.id.buttonQuestionPage);
        buttonSearch = findViewById(R.id.buttonSearch);

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
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
                update();
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

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, searchFragment);
                transaction.commit();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////
        //implementacao fragmentos
        menuFragment = new MenuFragment();
        questionsFragment = new QuestionsFragment();
        rankingFragment = new RankingFragment();
        searchFragment = new SearchFragment();
        //aqui seta o fragmento que vai comecar no app
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, questionsFragment);
        transaction.commit();

    }

    private void update()
    {}
}