package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer player;
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    player.pause();
                    player.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                player.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };



    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        setContentView(R.layout.words_list);
        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("lutti","one",R.drawable.number_one,R.raw.number_one)) ;
        words.add(new word("otiiko","two",R.drawable.number_two,R.raw.number_two )) ;
        words.add(new word("tolookosu","three",R.drawable.number_three,R.raw.number_three)) ;
        words.add(new word("oyyisa","four",R.drawable.number_four,R.raw.number_four)) ;
        words.add(new word("massokka","five",R.drawable.number_five,R.raw.number_five)) ;
        words.add(new word("temmokka","six",R.drawable.number_six,R.raw.number_six)) ;
        words.add(new word("kenekaku","seven",R.drawable.number_seven,R.raw.number_seven));
        words.add(new word("kawinta","eight",R.drawable.number_eight,R.raw.number_eight));
        words.add(new word("wo'e","nine",R.drawable.number_nine,R.raw.number_nine));
        words.add(new word("na'aacha","ten",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter Adapter = new WordAdapter(this, words,R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(Adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               word currentWord= words.get(position);
               releaseMediaPlayer();

               int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                       // Use the music stream.
                       AudioManager.STREAM_MUSIC,
                       // Request permanent focus.
                       AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

               if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                   // Start playback

                   player= MediaPlayer.create(NumbersActivity.this,currentWord.getMusic_id());
                   player.start();
                   player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                       @Override
                       public void onCompletion(MediaPlayer mp) {
                           releaseMediaPlayer();
                       }
                   });
               }
           }
       });


    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
