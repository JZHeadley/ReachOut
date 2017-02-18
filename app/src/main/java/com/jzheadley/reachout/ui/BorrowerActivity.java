package com.jzheadley.reachout.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jzheadley.reachout.R;

public class BorrowerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.submit_new_restaurant);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proposalCreationIntent = new Intent(view.getContext(), ProposalCreationActivity.class);
                view.getContext().startActivity(proposalCreationIntent);
            }
        });
    }

}
