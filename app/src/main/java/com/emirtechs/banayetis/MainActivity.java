package com.emirtechs.banayetis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    Button startButton;

    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        startButton = findViewById(R.id.startButton);

        imageArray = new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};


        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        score = 0;
        scoreText.setText("Score: " + score);


    }

    public void increaseScore(View view) {
        score++;
        scoreText.setText("Score: " + score);
    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);
            }
        };

        handler.post(runnable);
        startButton.setEnabled(false);

    }

    public void startGame(View view) {
        score = 0;
        scoreText.setText("Score: 0");
        hideImages();

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long l) {
                timeText.setText("Time: " + l / 1000);
            }

            @Override
            public void onFinish() {
                startButton.setEnabled(true);

                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
            }
        }.start();
    }
}