package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.os.Bundle;

import com.amnix.materiallockview.MaterialLockView;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Person;

public class PatternLoginActivity extends BaseActivity {

    private static final String TAG = "PatternLoginActivity";
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_login);
        final MaterialLockView materialLockView = (com.amnix.materiallockview.MaterialLockView) findViewById(R.id.pattern);
        person = (Person) getIntent().getExtras().get("PossibleUser");

        if (person.getPassHash().equals(Integer.toString(materialLockView.getPattern().hashCode()))) {
            if (person.isLeader()) {
                startActivity(new Intent(getApplicationContext(), BorrowerActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), BorrowerActivity.class));
            }
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
/*        ((Button) findViewById(R.id.login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelSingleton.getInstance().synchWithDB();

                for (Person person : new ArrayList<Person>(ModelSingleton.getInstance().getPeople().values())) {

                    if (person.getPassHash().equalsIgnoreCase(String.valueOf(materialLockView.getPattern().hashCode()))) {
                        SharedPreferences preferences = App.get().getSharedPreferences("ReachOut", MODE_PRIVATE);
                        preferences.edit().putString("personId", person.getPersonId()).apply();
                        Log.d(TAG, "onClick: Someone got their password right");
                    }

                }
                if (getPreferences(MODE_PRIVATE).getString("personId", "").length() > 0) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Log.d(TAG, "onClick: Someone was authenticated...");
                } else {
                    startActivity(new Intent(materialLockView.getContext(), LoginActivity.class));
                }
            }
        });*/
    }
}