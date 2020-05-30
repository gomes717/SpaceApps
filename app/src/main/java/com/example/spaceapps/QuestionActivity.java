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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        textoQuestao = findViewById(R.id.questaoText);
        textoQuestao.setText("questao " + String.valueOf(i) + ":");

        question = findViewById(R.id.questionPlace);
        question.setText("1+1=");

        buttonX = findViewById(R.id.X);
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setText("Enter");
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
        anwserCorrect = MediaPlayer.create(this, R.raw.correct);
        anwserWrong = MediaPlayer.create(this, R.raw.wrong);
        finalEXercises = MediaPlayer.create(this, R.raw.finalwin);

    }

    public void update() {
        aux = findViewById(radioGroup.getCheckedRadioButtonId());
        if (aux.getId() == radioButtonC.getId()) {
            aux.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            anwserCorrect.start(); // no need to call prepare(); create() does that for you
        } else {
            aux.setBackgroundColor(getResources().getColor(R.color.colorRedLight));
            radioButtonC.setBackgroundColor(getResources().getColor(R.color.colorAccent));
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
                radioButtonC.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                textoQuestao.setText("questao " + String.valueOf(i) + ":");
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

