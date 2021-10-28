package com.example.zad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ANSWER_SHOWN = "answerShown";
    //private TextView answerQuestionTextView;
    private TextView answerTextView;
    private Button showAnswerButton;
    private boolean correctAnswer;

    private void setAnswerShownResult (boolean answerWasShown){
        Intent resultintent = new Intent();
        resultintent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultintent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        //answerQuestionTextView = findViewById(R.id.answer_q_text_view);
        showAnswerButton = findViewById(R.id.show_answer_button);
        answerTextView = findViewById(R.id.answer_text_view);


        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

    }


}