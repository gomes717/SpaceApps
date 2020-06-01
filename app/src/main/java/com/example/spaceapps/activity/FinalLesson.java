package com.example.spaceapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import com.example.spaceapps.R;

import java.util.Locale;

public class FinalLesson extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_lesson);

        tts = new TextToSpeech(this, this);
        tts.setPitch((float) 0.5);
        tts.setSpeechRate((float) 0.9);
        tts.setLanguage(Locale.US);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tts.speak("Congratulations, little pupil.  You completed this module!", tts.QUEUE_ADD, null);
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);//
        startActivity(intent);

    }

    public void onInit ( int status){
        if (status == TextToSpeech.SUCCESS) {

            tts.setLanguage(Locale.US);

            tts.speak("Congratulations, little pupil.  You complete this module!", tts.QUEUE_FLUSH, null);


        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}