package com.jzheadley.reachout.views;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jzheadley.reachout.R;

public class ThreeButtonView extends LinearLayout {
    private EditText threeButtonEditText;
    private Button threeButtonPlayPrompt;
    private Button threeButtonAddResponse;
    private Button threeButtonPlayResponse;
    private String promptText;
    private TextToSpeech ttobj;

    public ThreeButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public ThreeButtonView(Context context) {
        super(context);
        initControl(context);
    }

    public void setPromptText(String promptText) {
        threeButtonPlayPrompt.setContentDescription(promptText);
    }

    /**
     * Load component XML layout
     */
    private void initControl(Context context) {
        ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
        LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.three_button_view, this, true);
        threeButtonEditText = (EditText) findViewById(R.id.view_three_button_edit_text);
        threeButtonPlayPrompt = (Button) findViewById(R.id.view_three_button_play_prompt);
        threeButtonAddResponse = (Button) findViewById(R.id.view_three_button_add_response);
        threeButtonPlayResponse = (Button) findViewById(R.id.view_three_button_play_response);

        threeButtonPlayPrompt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ttobj.speak(String.valueOf(v.getContentDescription()), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

}
