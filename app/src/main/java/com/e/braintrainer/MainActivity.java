package com.e.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button goButton;
    Button playAgainButton;
    android.support.v7.widget.GridLayout gridLayout;
    CountDownTimer countDownTimer;
    TextView timerText;
    TextView answerText;
    Random random;
    TextView questionText;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationCorrectAns;
    int score = 0;
    int questionNumber = 0;
    TextView questionNumberText;
    Boolean isActive = true;

    public void playAgain(View view){

        timerText.setText("30s");
        answerText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        count();
        generateQues();
        questionNumberText.setText("0/0");
        score = 0;
        questionNumber = 1;
        isActive = true;

    }

    public void generateAns(int a, int b){

        if (isActive) {

            int incorrectAns;

            for (int i = 0; i < 4; i++) {

                if (i == locationCorrectAns) {
                    answers.add(a + b);
                } else {
                    incorrectAns = random.nextInt(41);
                    while (incorrectAns == a + b) {
                        incorrectAns = random.nextInt(41);
                    }
                    answers.add(incorrectAns);
                }
            }

            Button guess0 = (Button) findViewById(R.id.guess1);
            Button guess1 = (Button) findViewById(R.id.guess2);
            Button guess2 = (Button) findViewById(R.id.guess3);
            Button guess3 = (Button) findViewById(R.id.guess4);

            guess0.setText(Integer.toString(answers.get(0)));
            guess1.setText(Integer.toString(answers.get(1)));
            guess2.setText(Integer.toString(answers.get(2)));
            guess3.setText(Integer.toString(answers.get(3)));

        }
    }

    public void generateQues(){

        if (isActive) {

            random = new Random();

            int a = random.nextInt(21);
            int b = random.nextInt(21);

            questionText.setText(Integer.toString(a) + " + " + Integer.toString(b));

            locationCorrectAns = random.nextInt(4);
            answers.clear();
            generateAns(a, b);

        }
    }

    public void count(){

        countDownTimer = new CountDownTimer(30000 + 100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int sec = (int) millisUntilFinished / 1000;
                timerText.setText(Integer.toString(sec) + "s");

            }

            @Override
            public void onFinish() {

                timerText.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                answerText.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(questionNumber));
                answerText.setVisibility(View.VISIBLE);
                isActive = false;

            }
        }.start();

    }

    public void chooseAns(View view){

        if (isActive) {

            if (view.getTag().toString().equals(Integer.toString(locationCorrectAns))) {

                answerText.setText("Correct!");
                score++;

            } else {

                answerText.setText("Incorrect!");

            }
            questionNumber++;
            answerText.setVisibility(View.VISIBLE);
            questionNumberText.setText(Integer.toString(score) + "/" + Integer.toString(questionNumber));
            answers.clear();
            generateQues();

        }

    }

    public void startButton(View view){

        goButton.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);

        count();
        generateQues();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.textLayout);
        gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);
        goButton = (Button) findViewById(R.id.goButton);
        timerText = (TextView) findViewById(R.id.timerText);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        answerText = (TextView) findViewById(R.id.answerText);
        questionText = (TextView) findViewById(R.id.questionText);
        questionNumberText = (TextView) findViewById(R.id.questionNumberText);

        goButton.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        answerText.setVisibility(View.INVISIBLE);

    }
}
