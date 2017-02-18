package com.jzheadley.reachout.views;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jzheadley.reachout.R;

public class ExplainFieldView extends LinearLayout {
    private TextToSpeech ttobj;
    private Button playExplanationButton;
    private View view;

    public ExplainFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public ExplainFieldView(Context context) {
        super(context);
        initControl(context);
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        ttobj.stop();
        ttobj.shutdown();
    }

    public void setPromptText(String promptText) {
        view.setContentDescription(promptText);
    }

    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        inflater.inflate(R.layout.explain_field_view, this, true);
        view = (View) findViewById(R.id.view_to_be_explained);
        playExplanationButton = (Button) findViewById(R.id.play_explanation_for_view);
        ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
        playExplanationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ttobj.speak(String.valueOf(v.getContentDescription()), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }


}
