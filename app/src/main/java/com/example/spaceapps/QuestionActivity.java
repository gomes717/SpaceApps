package com.example.spaceapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    private Button buttonX, buttonNext;
    private RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    private TextView textoQuestao;
    private int i = 1,maxQuestions = 5;
    private ProgressBar barraProgresso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        textoQuestao = findViewById(R.id.questaoText);
        textoQuestao.setText("questao " + String.valueOf(i) + ":");
        buttonX = findViewById(R.id.X);
        buttonNext = findViewById(R.id.buttonNext);
        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);
        radioButtonD = findViewById(R.id.radioButtonD);
        barraProgresso = findViewById(R.id.progressBar);
        barraProgresso.setMax(6);
        barraProgresso.setMin(1);
    }

    public void update()
    {
        textoQuestao.setText("questao " + String.valueOf(i) + ":");
        barraProgresso.setProgress(i);

    }

    public void pressedNext(View view){

        if(i == maxQuestions)
        {
            i++;

            barraProgresso.setProgress(i);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }else if (radioButtonA.isChecked() || radioButtonB.isChecked() || radioButtonC.isChecked() || radioButtonD.isChecked()) {
            i++;
            update();
        }
    }

    public void pressedExit(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}