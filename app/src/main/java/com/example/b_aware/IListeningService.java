package com.example.b_aware;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class IListeningService extends Service {
    public IListeningService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        //onTaskRemoved(intent);
        /*
        What To Do When Service Is Called.
         */
        try
        {

            startSpeerchRecognation();

//            TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
//            telephonyManager.listen(new PhoneStateListener()
//            {
//                @Override
//                public void onCallStateChanged(int state, String incomingPhoneNumber)
//                {
//
//                    if (state == TelephonyManager.CALL_STATE_OFFHOOK)
//                    {
//
//                        startSpeerchRecognation();
//                    }
//                }
//            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
        catch (IllegalStateException ex)
        {
            ex.printStackTrace();
        }
        return START_STICKY;
    }

    public void startSpeerchRecognation() {
        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {


                Log.v("results", "message from results");

                //getting all the matches
                //ArrayList<String> matches = bundle
                //      .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                //if (matches != null)
                //   editText.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                ArrayList<String> unstableData = bundle.getStringArrayList("android.speech.extra.UNSTABLE_TEXT");

                Log.v("partial", data.get(0) + unstableData.get(0));

                //String mResult = data.get(0) + unstableData.get(0);
            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
    }
}
