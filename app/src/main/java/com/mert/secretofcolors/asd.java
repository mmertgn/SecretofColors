/*package com.mert.secretofcolors;

import android.util.Log;

/**
 * Created by Mert on 21.05.2017.
 */
/*
public class asd {
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

            long totalDuration = 0;
            long currentDuration = 0;

            if(musicPlayer.isPlaying()) {
                totalDuration = musicPlayer.getDuration();
                currentDuration = musicPlayer.getCurrentPosition();

                // Updating progress bar
                int progress = (utils.getProgressPercentage(currentDuration, totalDuration));
                trackPb.setProgress(progress);

                if (!trackDownloaded && currentDuration > 100) {
                    Log.i(TagsContainer.MUSIC_PLAYER_TAG,"next track download started");
                    trackDownloaded = true;
                    new TrackLoader().execute();

                }

                long crossFadeValue = currentDuration + CROSSFADE_DURATION;
                if (crossFadeValue > totalDuration && !fadeStarted && currentDuration > 100) {
                    fadeStarted = true;
                    crossFade();
                }
                // Running this thread after 100 milliseconds

            }
            mHandler.postDelayed(this, 100);
        }
    };

    private void crossFade() {

        fadeOut(musicPlayer, CROSSFADE_DURATION);
        fadeIn(musicPlayer2, CROSSFADE_DURATION);
    }

    public void fadeOut(final MediaPlayer _player, final int duration) {
        final float deviceVolume = getDeviceVolume();
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            private float time = duration;
            private float volume = 0.0f;

            @Override
            public void run() {
                if (!_player.isPlaying())
                    _player.start();
                // can call h again after work!
                time -= 100;
                volume = (deviceVolume * time) / duration;
                _player.setVolume(volume, volume);
                if (time > 0)
                    h.postDelayed(this, 100);
                else {
                    _player.stop();
                    _player.release();
                }
            }
        }, 100); // 1 second delay (takes millis)


    }

    public void fadeIn(final MediaPlayer _player, final int duration) {
        final float deviceVolume = getDeviceVolume();
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            private float time = 0.0f;
            private float volume = 0.0f;

            @Override
            public void run() {
                if (!_player.isPlaying())
                    _player.start();
                // can call h again after work!
                time += 100;
                volume = (deviceVolume * time) / duration;
                _player.setVolume(volume, volume);
                if (time < duration)
                    h.postDelayed(this, 100);
            }
        }, 100); // 1 second delay (takes millis)

    }
}*/
