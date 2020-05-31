package com.example.spaceapps.activity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.spaceapps.R;
import com.example.spaceapps.activity.MainActivity;

import java.util.Locale;
import java.util.Random;
import java.util.Vector;

public class QuestionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    
    private Button buttonX, buttonNext;
    private RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private TextView textoQuestao;
    private int i = 1, maxQuestions = 8;
    private ProgressBar barraProgresso;
    private RadioGroup radioGroup;
    private TextToSpeech tts;
    private RadioButton aux;

    private TextView question;

    private MediaPlayer anwserCorrect;
    private MediaPlayer anwserWrong;
    private MediaPlayer finalEXercises;

    ///Season questions math
    private String [] totalQuestionMath = {"98+14=", "4+2+5+5=", "7+10+2+3+4+1=", "80+2+5+6+3=", "46+23=", "1000+10+33+10+2=", "17+8+16+12+12+1=",
                                            "85+65+2=", "89+2+1", "8+6+4+5+80", "1+1+2+3+5+8+13=", "25+65+3+3+8", "17+5+9=", "98+74+2+3+6=", "26+5+9="};
    private String [] totalAnswerMath = {"112", "16", "33", "96", "69", "1055", "66",
                                        "152", "92", "103", "33", "104", "31", "183", "40"};

    private String [] wrongAnswerMath = {"111", "15", "32", "95", "68", "1054", "67",
                                         "151", "91", "30", "34", "103", "29", "184", "44",

                                        "113", "16", "97", "70", "1057", "67",
                                        "153", "93", "105", "185", "41"};
    private RadioButton correctAnswer;
    private RadioButton [] options = { radioButtonA, radioButtonB, radioButtonC, radioButtonD };
    private Vector<Integer> questionsToUse = new Vector<Integer>();

    private Integer dado;
    
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

        tts.setSpeechRate((float) 1.4);

        anwserCorrect = MediaPlayer.create(this, R.raw.correct);
        anwserWrong = MediaPlayer.create(this, R.raw.wrong);
        finalEXercises = MediaPlayer.create(this, R.raw.finalwin);

        //Generate questions
        Random generator = new Random();
        int n;
        for (int j = 0; j <= maxQuestions; j++) {
            n = generator.nextInt(totalQuestionMath.length);
            while (questionsToUse.contains(n))
            {
                n = generator.nextInt(totalQuestionMath.length);
            }
            questionsToUse.add(n);
        }
        question.setText(totalQuestionMath[questionsToUse.get(0)]);

        Vector<String> answers = new Vector<String>();
        int posCorrect = generator.nextInt(4);
        options[posCorrect].setText(totalAnswerMath[questionsToUse.get(0)]);
        answers.add(totalAnswerMath[questionsToUse.get(0)]);
        correctAnswer = options[posCorrect];

        for (int j = 0; j < 4; j++) {
            if (j != posCorrect)
            {
                n = generator.nextInt(wrongAnswerMath.length);

                while (answers.contains(wrongAnswerMath[n])) {
                    n = generator.nextInt(wrongAnswerMath.length);
                }
                answers.add(wrongAnswerMath[n]);
                options[j].setText(wrongAnswerMath[n]);
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
            if (options[0].isChecked() || options[1].isChecked() || options[2].isChecked() || options[3].isChecked()) {
                update();
            }
        } else if (buttonNext.getText() == "Next") {
            i++;
            if (i > maxQuestions) {
                barraProgresso.setProgress(i);
                finalEXercises.start(); // no need to call prepare(); create() does that for you
                //////////////////////////////////////////////////////AQUI COLOCAR A NOVA INTENT
                finish();
            }else
                {
                for (int i = 0; i < options.length; i++)
                {
                    options[i].setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
                textoQuestao.setText("questao " + String.valueOf(i) + ":");

                //////////////////////////
                question.setText(totalQuestionMath[questionsToUse.get(i-1)]);
                Random generator = new Random();
                int n;
                Vector<String> answers = new Vector<String>();
                int posCorrect = generator.nextInt(4);
                options[posCorrect].setText(totalAnswerMath[questionsToUse.get(i-1)]);
                answers.add(totalAnswerMath[questionsToUse.get(i-1)]);
                correctAnswer = options[posCorrect];

                for (int j = 0; j < 4; j++) {
                    if (j != posCorrect)
                    {
                        n = generator.nextInt(wrongAnswerMath.length);

                        while (answers.contains(wrongAnswerMath[n])) {
                            n = generator.nextInt(wrongAnswerMath.length);
                        }
                        answers.add(wrongAnswerMath[n]);
                        options[j].setText(wrongAnswerMath[n]);
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

    public void pressedExit (View view) {
        finish();
        }
    @Override
    public void onInit ( int status){
            if (status == TextToSpeech.SUCCESS) {

                    tts.setLanguage(Locale.US);
                    String questionT = question.getText().toString();
                    tts.speak(questionT, tts.QUEUE_FLUSH, null);


            }
        }
    }

