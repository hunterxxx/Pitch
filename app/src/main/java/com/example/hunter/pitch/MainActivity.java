package com.example.hunter.pitch;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pitch);
       /* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }*/


        final MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.sad);
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

        dispatcher.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, new PitchDetectionHandler() {

            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult,
                                    AudioEvent audioEvent) {
                final float pitchInHz = pitchDetectionResult.getPitch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.textView1);
                        text.setText("" + pitchInHz);
                    }
                });

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Stuff that update UI
                                if (pitchInHz > 100 && pitchInHz<400) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    TextView success = (TextView) findViewById(R.id.setEmotionText);
                                    success.setText("sad");

                                    //System.exit(0);
                                    Log.v(TAG, "finish publishing");
                                }else if(pitchInHz>400){
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    TextView success = (TextView) findViewById(R.id.setEmotionText);
                                    success.setText("sad");
                                    //System.exit(0);
                                    Log.v(TAG, "finish publishing");
                                }
                            }
                        });
                        //TODO

                if(mediaplayer == null) {
                    Log.v(TAG, "Create() on MediaPlayer failed.");
                } else {
                    mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mediaplayer) {
                            mediaplayer.stop();
                            mediaplayer.release();
                        }
                    });
                    //mediaplayer.start();
                }
            }
        }));
        new Thread(dispatcher,"Audio Dispatcher").start();

    }

    public void listenButton(View view){
        Intent intent = new Intent(MainActivity.this,RoutesActivity.class);
        startActivity(intent);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.pitch,
                    container, false);
            return rootView;
        }
    }
}
