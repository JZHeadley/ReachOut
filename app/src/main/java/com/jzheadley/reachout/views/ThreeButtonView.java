package com.jzheadley.reachout.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;
import com.jzheadley.reachout.R;

public class ThreeButtonView extends LinearLayout {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final String TAG = "ThreeButtonView";
    private EditText threeButtonEditText;
    private Button threeButtonPlayPrompt;
    private Button threeButtonAddResponse;
    private Button threeButtonPlayResponse;
    private TextToSpeech ttobj;
    private SpeechRecognizer speechRecognizer;
    private RecognitionProgressView recognitionProgressView;

    public ThreeButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public ThreeButtonView(Context context) {
        super(context);
        initControl(context);
    }

    public String getInputText() {
        return threeButtonEditText.getText().toString();
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        ttobj.stop();
        ttobj.shutdown();
        speechRecognizer.destroy();
    }

    public void setEditTextVisibility(int visibility) {
        threeButtonEditText.setVisibility(visibility);
    }

    public void setPromptText(String promptText) {
        threeButtonPlayPrompt.setContentDescription(promptText);
    }

    /**
     * Load component XML layout
     */
    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);

        inflater.inflate(R.layout.three_button_view, this, true);
        threeButtonEditText = (EditText) findViewById(R.id.view_three_button_edit_text);
        threeButtonPlayPrompt = (Button) findViewById(R.id.view_three_button_play_prompt);
        threeButtonAddResponse = (Button) findViewById(R.id.view_three_button_add_response);
        threeButtonPlayResponse = (Button) findViewById(R.id.view_three_button_play_response);
        ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
        threeButtonPlayPrompt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ttobj.speak(String.valueOf(v.getContentDescription()), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        threeButtonAddResponse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecognition(v);
            }
        });
        threeButtonPlayResponse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ttobj.speak(threeButtonEditText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void startRecognition(final View callingView) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 10000);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 10000);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.jzheadley.reachout");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        final Dialog dialog = new Dialog(callingView.getContext());
        dialog.setContentView(R.layout.dialog_speech_recognition);
        int[] colors = {
            ContextCompat.getColor(dialog.getContext(), R.color.color1),
            ContextCompat.getColor(dialog.getContext(), R.color.color2),
            ContextCompat.getColor(dialog.getContext(), R.color.color3),
            ContextCompat.getColor(dialog.getContext(), R.color.color4),
            ContextCompat.getColor(dialog.getContext(), R.color.color5)
        };

        recognitionProgressView = ((RecognitionProgressView) dialog.findViewById(R.id.recognition_view));
        recognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onBeginningOfSpeech() {
                recognitionProgressView.play();
            }

            @Override
            public void onResults(Bundle results) {
                Log.d(TAG, "onResults: " + results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
                setEditTextText(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
            }

            @Override
            public void onEndOfSpeech() {
                recognitionProgressView.stop();
                dialog.dismiss();
                super.onEndOfSpeech();
            }
        });
        recognitionProgressView.setSpeechRecognizer(speechRecognizer);
        recognitionProgressView.setColors(colors);
        speechRecognizer.startListening(intent);
        recognitionProgressView.play();
        dialog.show();
    }

    private void setEditTextText(String text) {
        threeButtonEditText.setText(text);
    }
}

