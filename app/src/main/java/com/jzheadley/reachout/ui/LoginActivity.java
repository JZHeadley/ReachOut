package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity {


    private static final String TAG = "LoginActivity";
    private ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_person_faces);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        people = new ArrayList<>(ModelSingleton.getInstance().getPeople().values());
        Log.d(TAG, "onCreate: " + people);
        LoginFacesAdapter adapter = new LoginFacesAdapter(people);
        recyclerView.setAdapter(adapter);

    }

    public void onClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
