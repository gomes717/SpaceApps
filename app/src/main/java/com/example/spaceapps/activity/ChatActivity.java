package com.example.spaceapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.spaceapps.R;

public class ChatActivity extends AppCompatActivity {
    private ImageButton buttonSend, buttonRules;
    private TextView chat;
    private EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        buttonSend = findViewById(R.id.buttonSend);
        msg = findViewById(R.id.msg);
        chat = findViewById(R.id.chat);
        chat.setMovementMethod(new ScrollingMovementMethod());
    }

    public void sendmessage(View view) {
        String totalChat = chat.getText().toString();
        String myMessage = msg.getText().toString();
        totalChat += "\n Immanuel Kant: " + myMessage;

        chat.setText(totalChat);
        msg.setText("");
    }
}