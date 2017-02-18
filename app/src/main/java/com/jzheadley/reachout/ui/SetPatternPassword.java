package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Person;

public class SetPatternPassword extends BaseActivity {
    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pattern);
        person = (Person) getIntent().getExtras().get("almost_whole_person");
    }
}
