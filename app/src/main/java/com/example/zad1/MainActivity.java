package com.example.zad1;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private Button clueButton;


    private int currentIndex=0;
    private static final String QUIZ_TAG = "MainActivity";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.zad1.correctAnswer";
    private static final int REQUEST_CODE_CLUE =0;
    public static boolean answerWasShown = false;

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG, "Wywoływane: onCreate");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        clueButton = findViewById(R.id.button_show_clue);
        questionTextView = findViewById(R.id.question_text_view);

        clueButton.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_CLUE);
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex +1)%questions.length;
                answerWasShown = false;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) {return ;}
        if(requestCode == REQUEST_CODE_CLUE){
            if(data==null){return;}
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(QUIZ_TAG, "Wywoływane: onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(QUIZ_TAG, "Wywoływane: onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(QUIZ_TAG, "Wywoływane: onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(QUIZ_TAG, "Wywoływane: onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(QUIZ_TAG, "Wywoływane: onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Wywoływane: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }


    private Question[] questions = new Question[] {
            new Question(R.string.q_sun, true),
            new Question(R.string.q_sky, false),
            new Question(R.string.q_grass, true),
            new Question(R.string.q_rainbow, true),
            new Question(R.string.q_orange, false)
    };


    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        }
        else{
            if (userAnswer==correctAnswer){
                resultMessageId = R.string.correct_answer;
            }
            else{
                resultMessageId = R.string.incorrect_answer;
            }
        }

        Toast.makeText(this, resultMessageId, Toast.LENGTH_LONG).show();
    }

}

