package com.example.myapp;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.widget.SeekBar;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // සින්දුව ප්ලේ කිරීම
        mediaPlayer = MediaPlayer.create(this, R.raw.mysong);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Volume පාලනය
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        SeekBar volumeBar = findViewById(R.id.volumeBar);
        
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeBar.setMax(maxVol);
        volumeBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }
}

