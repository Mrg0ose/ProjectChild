package com.example.projectchild;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mathem extends AppCompatActivity {
    Timer timer = new Timer();
    private int score = 0; // счет
    private int highScore = 0; // рекорд
    private int chotchik =5;
    private int chot = 5;
    private int valuta;

    private int currentAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        generateTask();
        SharedPreferences sharedPreferences = getSharedPreferences("Reward", Context.MODE_PRIVATE);
        valuta = sharedPreferences.getInt("valutes", 0);
        TextView v;
        v = findViewById(R.id.valutemat);
        v.setText(String.valueOf(valuta));
        Button buttonAnswer;
        buttonAnswer = findViewById(R.id.bu);
        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                TextView textViewrez;

                EditText editTextAnswer;
                editTextAnswer = findViewById(R.id.editTextans);
                textViewrez = findViewById(R.id.textrez);
                String answerText = editTextAnswer.getText().toString();
                if (!answerText.isEmpty()) {
                    int answer = Integer.parseInt(answerText);
                    if (answer == currentAnswer) {
                        if (highScore == chot){
                            valuta += 5;
                            chotchik +=5;
                            chot +=5;
                            TextView textViewrew;
                            textViewrew = findViewById(R.id.textrew);
                            textViewrew.setText("Награда 5 рубинов!");
                            runtwo();
                        }
                        else{
                            valuta+=1;
                        }
                        v.setText(String.valueOf(valuta));
                        textViewrez.setText("Правильно!");
                        handleCorrectAnswer();
                        run();
                        onStop();

                    } else {
                        if(valuta>0){
                            textViewrez.setText("Неправильно :(");
                            valuta-=1;
                            v.setText(String.valueOf(valuta));
                            resetScore();
                            run();
                        }
                        else {
                            textViewrez.setText("Неправильно :(");
                            resetScore();
                            run();
                        }
                        onStop();
                    }
                    editTextAnswer.setText("");
                    generateTask();
                }
            }
            private void resetScore() {
                score = 0;
                TextView textViewcount;
                textViewcount = findViewById(R.id.textcount);
                textViewcount.setText("Счёт: " + score);
            }
            private void handleCorrectAnswer() {
                TextView textViewcount;
                TextView textViewrecord;
                textViewcount = findViewById(R.id.textcount);
                textViewrecord = findViewById(R.id.textrecord);
                score++;
                textViewcount.setText("Счёт: " + score);


                if (score > highScore) {
                    highScore = score;
                    textViewrecord.setText("Рекорд: " + highScore);
                }
            }


            private void run() {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TextView textViewrez;
                        textViewrez = findViewById(R.id.textrez);
                        textViewrez.setText("");
                    }
                }, 1000);
            }

            private void runtwo() {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TextView textViewrew;
                        textViewrew = findViewById(R.id.textrew);
                        textViewrew.setText("");
                    }
                }, 1700);
            }
        });





    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("score", score);
        editor.putInt("highScore", highScore);
        editor.putInt("chot", chotchik);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        score = sharedPref.getInt("score", 0);
        highScore = sharedPref.getInt("highScore", 0);
        chotchik = sharedPref.getInt("chot", 5);
        TextView textViewcount = findViewById(R.id.textcount);
        textViewcount.setText("Счёт: " + score);
        TextView textViewrecord = findViewById(R.id.textrecord);
        textViewrecord.setText("Рекорд: " + highScore);
        chot = chotchik;

    }
    private void generateTask() {
        TextView textViewTask;
        textViewTask = findViewById(R.id.textform);
        int minNumber = 5;
        int maxNumber = 10;
        int num1 = (int)(Math.random() * maxNumber) + minNumber;
        int num2 = (int)(Math.random() * maxNumber) + minNumber;
        int operation = (int)(Math.random() * 3); // 0 - сложение, 1 - вычитание, 2 - умножение

        String task;
        int correctAnswer;

        switch (operation) {
            case 0:
                task = num1 + " + " + num2 + " = ";
                correctAnswer = num1 + num2;
                break;
            case 1:
                if (num1 > num2){
                    task = num1 + " - " + num2 + " = ";
                    correctAnswer = num1 - num2;
                    break;
                }
                else{
                    task = num2 + " - " + num1 + " = ";
                    correctAnswer = num2 - num1;
                    break;
                }

            case 2:
                task = num1 + " * " + num2 + " = ";
                correctAnswer = num1 * num2;
                break;
            default:
                task = "";
                correctAnswer = 0;
                break;
        }
        currentAnswer = correctAnswer;
        textViewTask.setText(task);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // сохраняем значение рубинов
        SharedPreferences sharedPreferences = getSharedPreferences("Reward", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("valutes", valuta);
        editor.apply();
    }
    //@Override
    //public void onBackPressed() {
        //super.onBackPressed();
        //onResume();
    //}
}
