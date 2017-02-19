package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jzheadley.reachout.Constants;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants constants = new Constants();
    }

    @Override
    public void setContentView(int layoutResId) {
        LinearLayout fullView =
            (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResId, activityContainer, true);
        super.setContentView(fullView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // to opt out of using the toolbar override useToolbar() and return false
        if (useToolbar()) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected boolean useToolbar() {
        return true;
    }


}
