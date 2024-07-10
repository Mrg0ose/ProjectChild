package com.example.projectchild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Russian extends AppCompatActivity {
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
        setContentView(R.layout.activity_russian);
        letter = findViewById(R.id.letter);
        imgletter = findViewById(R.id.imgletter);
        letters = new ArrayList<>();
        letters.add(new Russian.Letter(R.drawable.let_a,R.drawable.imag_a, R.raw.arbuz));
        letters.add(new Russian.Letter(R.drawable.let_b,R.drawable.imag_b, R.raw.baraban));
        letters.add(new Russian.Letter(R.drawable.let_v,R.drawable.imag_v, R.raw.velosiped));
        letters.add(new Russian.Letter(R.drawable.let_g,R.drawable.imag_g, R.raw.gus));
        letters.add(new Russian.Letter(R.drawable.let_d,R.drawable.imag_d, R.raw.dom));
        letters.add(new Russian.Letter(R.drawable.let_e,R.drawable.imag_e, R.raw.edinorog));
        letters.add(new Russian.Letter(R.drawable.let_z,R.drawable.imag_z, R.raw.zebra));
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
                MediaPlayer mediaPlayer = MediaPlayer.create(Russian.this, letters.get(currentLetterIndex).getSoundId());
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