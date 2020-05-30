package com.example.spaceapps;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;
import java.util.Vector;

public class QuestionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Button buttonX, buttonNext;
    private RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private TextView textoQuestao;
    private int i = 1, maxQuestions = 5;
    private ProgressBar barraProgresso;
    private RadioGroup radioGroup;
    private TextToSpeech tts;
    private RadioButton aux;

    private TextView question;

    private MediaPlayer anwserCorrect;
    private MediaPlayer anwserWrong;
    private MediaPlayer finalEXercises;


    ///Season questions
    private String [] totalQuestion = {"1+1=", "2+2=", "7X10=", "80+2=", "46/23=", "1000+0=", "17-8="};
    private String [] totalAnswer = {"2", "4", "70", "82", "2", "1000", "9"};
    private String [] wrongAnswer = {"99", "8", "72%", "1888", "87662", "556"};
    private RadioButton correctAnswer;
    private  RadioButton [] options = {radioButtonA, radioButtonB, radioButtonC, radioButtonD};
    private Vector<Integer> questionsToUse = new Vector<Integer>();



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        textoQuestao = findViewById(R.id.questaoText);
        textoQuestao.setText("questao " + String.valueOf(i) + ":");

        question = findViewById(R.id.questionPlace);


        buttonX = findViewById(R.id.X);
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setText("Enter");

        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);
        radioButtonD = findViewById(R.id.radioButtonD);

        options[0] =  findViewById(R.id.radioButtonA);
        options[1] =  findViewById(R.id.radioButtonB);
        options[2] =  findViewById(R.id.radioButtonC);
        options[3] =  findViewById(R.id.radioButtonD);

        barraProgresso = findViewById(R.id.progressBar);
        barraProgresso.setMax(6);
        barraProgresso.setMin(1);
        radioGroup = findViewById(R.id.radioGroup);
        //Speak sound
        tts = new TextToSpeech(this, this);
        anwserCorrect = MediaPlayer.create(this, R.raw.correct);
        anwserWrong = MediaPlayer.create(this, R.raw.wrong);
        finalEXercises = MediaPlayer.create(this, R.raw.finalwin);

        //Generate questions
        Random generator = new Random();
        int n;
        for (int j = 0; j <= maxQuestions; j++) {
            n = generator.nextInt(totalQuestion.length);
            while (questionsToUse.contains(n))
            {
                n = generator.nextInt(totalQuestion.length);
            }
            questionsToUse.add(n);
        }
        question.setText(totalQuestion[questionsToUse.get(0)]);

        Vector<String> answers = new Vector<String>();
        int posCorrect = generator.nextInt(4);
        options[posCorrect].setText(totalAnswer[questionsToUse.get(0)]);
        answers.add(totalAnswer[questionsToUse.get(0)]);
        correctAnswer = options[posCorrect];

        for (int j = 0; j < 4; j++) {
            if (j != posCorrect)
            {
                n = generator.nextInt(wrongAnswer.length);

                while (answers.contains(wrongAnswer[n])) {
                    n = generator.nextInt(wrongAnswer.length);
                }
                answers.add(wrongAnswer[n]);
                options[j].setText(wrongAnswer[n]);
            }
        }
    }

    public void update() {
        aux = findViewById(radioGroup.getCheckedRadioButtonId());
        if (aux.getId() == correctAnswer.getId()) {
            aux.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            anwserCorrect.start(); // no need to call prepare(); create() does that for you
        } else {
            aux.setBackgroundColor(getResources().getColor(R.color.colorRedLight));
            correctAnswer.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            anwserWrong.start(); // no need to call prepare(); create() does that for you
        }
        buttonNext.setText("Next");
    }


    public void clickOption(View view) {
        aux = findViewById(radioGroup.getCheckedRadioButtonId());
        String optionSelect = aux.getText().toString();
        tts.speak(optionSelect, tts.QUEUE_FLUSH, null);
    }

    public void pressedNext(View view) {
        if (buttonNext.getText() == "Enter") {
            if (radioButtonA.isChecked() || radioButtonB.isChecked() || radioButtonC.isChecked() || radioButtonD.isChecked()) {
                update();
            }
        } else if (buttonNext.getText() == "Next") {
            i++;
            if (i > maxQuestions) {
                barraProgresso.setProgress(i);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finalEXercises.start(); // no need to call prepare(); create() does that for you
                finish();
            }else {
                aux.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                correctAnswer.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                textoQuestao.setText("questao " + String.valueOf(i) + ":");


//////////////////////////
                question.setText(totalQuestion[questionsToUse.get(i-1)]);
                Random generator = new Random();
                int n;
                Vector<String> answers = new Vector<String>();
                int posCorrect = generator.nextInt(4);
                options[posCorrect].setText(totalAnswer[questionsToUse.get(i-1)]);
                answers.add(totalAnswer[questionsToUse.get(i-1)]);
                correctAnswer = options[posCorrect];

                for (int j = 0; j < 4; j++) {
                    if (j != posCorrect)
                    {
                        n = generator.nextInt(wrongAnswer.length);

                        while (answers.contains(wrongAnswer[n])) {
                            n = generator.nextInt(wrongAnswer.length);
                        }
                        answers.add(wrongAnswer[n]);
                        options[j].setText(wrongAnswer[n]);
                    }
                }
   /////////////////////////////////                 /////////////////////////////////////
                barraProgresso.setProgress(i);
                radioGroup.clearCheck();
                buttonNext.setText("Enter");
                String questionT = question.getText().toString();
                tts.speak(questionT, tts.QUEUE_FLUSH, null);
            }
          }
        }

        public void pressedExit (View view)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        @Override
        public void onInit ( int status){
            if (status == TextToSpeech.SUCCESS) {
                String questionT = question.getText().toString();
                tts.speak(questionT, tts.QUEUE_FLUSH, null);
            }
        }
    }

