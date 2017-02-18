package com.jzheadley.reachout.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jzheadley.reachout.R;

public class ThreeButtonView extends LinearLayout {
    private EditText threeButtonEditText;
    private Button threeButtonOne;
    private Button threeButtonTwo;
    private Button threeButtonThree;

    public ThreeButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public ThreeButtonView(Context context) {
        super(context);
        initControl(context);
    }

    /**
     * Load component XML layout
     */
    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.three_button_view, this, true);
        threeButtonEditText = (EditText) findViewById(R.id.three_button_edit_text);
        threeButtonOne = (Button) findViewById(R.id.three_button_play_prompt);
        threeButtonTwo = (Button) findViewById(R.id.three_button_add_response);
        threeButtonThree = (Button) findViewById(R.id.three_button_play_response);


    }
}
