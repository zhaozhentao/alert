package com.alert.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.alert.App;

import java.io.IOException;

/**
 * Created by zhaotao on 2018/1/17.
 */

public class MediaManager {

    public static void play(int FileResId) {
        new Thread(() -> {
            App app = App.getInstance();
            try {
                AssetFileDescriptor file = app.getResources().openRawResourceFd(FileResId);
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.reset();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                AudioManager audioManager = (AudioManager) app.getSystemService(Context.AUDIO_SERVICE);
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                mediaPlayer.setVolume(maxVolume, maxVolume);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
