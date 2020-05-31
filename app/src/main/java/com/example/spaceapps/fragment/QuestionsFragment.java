package com.example.spaceapps.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spaceapps.R;
import com.example.spaceapps.activity.QuestionActivity;

import org.w3c.dom.Text;

public class QuestionsFragment extends Fragment {

    private Button buttonQuestion,buttonQuestion2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_questions, container, false);
        buttonQuestion = view.findViewById(R.id.buttonQM0);

        buttonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}