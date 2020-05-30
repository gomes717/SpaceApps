package com.example.spaceapps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Button buttonX, buttonNext;
    private RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    private TextView textoQuestao;
    private int i = 1,maxQuestions = 5;
    private ProgressBar barraProgresso;
    private RadioGroup radioGroup;
    private TextToSpeech tts;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        radioGroup = findViewById(R.id.radioGroup);

       //Speak sound
        tts = new TextToSpeech(this, this);
    }

    public void update()
    {
        textoQuestao.setText("questao " + String.valueOf(i) + ":");
        barraProgresso.setProgress(i);
        radioGroup.clearCheck();
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
        //Speak
        tts.speak("Próxima questão!", tts.QUEUE_FLUSH, null);
    }

    public void pressedExit(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS)
        {

        }
    }
}