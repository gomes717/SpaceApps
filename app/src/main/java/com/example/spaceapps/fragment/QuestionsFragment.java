package com.example.spaceapps.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spaceapps.R;

import org.w3c.dom.Text;

public class QuestionsFragment extends Fragment {
    private TextView textRoll;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

                View view  = inflater.inflate(R.layout.fragment_questions, container, false);
                textRoll = view.findViewById(R.id.textRoll);

                return view;
    }
}