package com.example.projectchild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class English extends AppCompatActivity {
    public class Letter {
        private int letterId;
        private int letterimageId;
        private int soundId;

        public Letter(int letterId,int letterimageId, int soundId) {
            this.letterId = letterId;
            this.letterimageId = letterimageId;
            this.soundId = soundId;
        }

        public int getletterimageId() {
            return letterimageId;
        }

        public int getletterId() {
            return letterId;
        }

        public int getSoundId() {
            return soundId;
        }

        public void playSound(Context context) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, soundId);
            mediaPlayer.start();
        }

    }
    private List<Letter> letters;
    private int currentLetterIndex = 0;

    private ImageView letter;
    private ImageView imgletter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        letter = findViewById(R.id.letter);
        imgletter = findViewById(R.id.imgletter);
        letters = new ArrayList<>();
        letters.add(new Letter(R.drawable.letter_a,R.drawable.image_a, R.raw.apple));
        letters.add(new Letter(R.drawable.letter_b,R.drawable.image_b, R.raw.bus));
        letters.add(new Letter(R.drawable.letter_c,R.drawable.image_c, R.raw.car));
        letters.add(new Letter(R.drawable.letter_d,R.drawable.image_d, R.raw.dolphin));
        letters.add(new Letter(R.drawable.letter_e,R.drawable.image_e, R.raw.eyes));
        letters.add(new Letter(R.drawable.letter_f,R.drawable.image_f, R.raw.fire));
        letters.add(new Letter(R.drawable.letter_g,R.drawable.image_g, R.raw.giraffe));
        setLetter();
        Button nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLetterIndex = (currentLetterIndex + 1) % letters.size();
                setLetter();
            }
        });
        Button playSoundButton = findViewById(R.id.voice);
        playSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(English.this, letters.get(currentLetterIndex).getSoundId());
                mediaPlayer.start();
            }
        });
    }

    private void setLetter() {
        Letter currentLetter = letters.get(currentLetterIndex);
        letter.setImageResource(currentLetter.getletterId());
        imgletter.setImageResource(currentLetter.getletterimageId());
    }
}