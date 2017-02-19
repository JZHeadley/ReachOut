package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amnix.materiallockview.MaterialLockView;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;

import java.util.List;

public class SetPatternPassword extends BaseActivity {
    private static final String TAG = "SetPatternPassword";
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pattern);
        person = (Person) getIntent().getExtras().get("almost_whole_person");
        final MaterialLockView materialLockView = (MaterialLockView) findViewById(R.id.pattern);
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {

            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                person.setPassHash(String.valueOf((pattern.hashCode())));
                Log.d(TAG, "onPatternDetected: " + person.getPassHash());
            }
        });
        ((Button) findViewById(R.id.submit_pattern_registration)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelSingleton.getInstance().createPerson(person);
                ModelSingleton.getInstance().synchWithDB();
                Log.d(TAG, "onClick: " + person.toString());
                finish();
            }
        });
    }
}
