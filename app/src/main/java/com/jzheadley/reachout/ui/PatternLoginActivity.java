package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amnix.materiallockview.MaterialLockView;
import com.jzheadley.reachout.App;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;

import java.util.List;

public class PatternLoginActivity extends BaseActivity {

    private static final String TAG = "PatternLoginActivity";
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_login);
        final MaterialLockView materialLockView = (com.amnix.materiallockview.MaterialLockView) findViewById(R.id.pattern);
        person = (Person) getIntent().getExtras().get("PossibleUser");
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {

            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                if (pattern.hashCode() == Integer.parseInt(person.getPassHash())) {
                }
            }
        });
        ((Button) findViewById(R.id.login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Person pers : ModelSingleton.getInstance().getPeople().values()) {
                    if (person.equals(pers)) {
                        SharedPreferences preferences = App.get().getSharedPreferences("ReachOut", MODE_PRIVATE);
                        preferences.edit().putString("personId", pers.getPersonId()).apply();
                    }
                }
                v.getContext().startActivity(new Intent(v.getContext(), BorrowerActivity.class));
                finish();
            }
        });
    }
}
