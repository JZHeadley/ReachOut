package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.ModelUtilities;
import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BorrowerActivity extends BaseActivity {
    private static final String TAG = "BorrowerActivity";
    public ProgressBar riskBar;
    public int riskStatus = 0;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_proposals);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //String currentUserId = this.getApplicationContext().getSharedPreferences("currentUser",MODE_PRIVATE).toString();
        //Log.d(TAG, "onCreate: CurrentUserID:" + currentUserId);
        //Person currentUser = ModelSingleton.getInstance().getPeople().get(currentUserId);
        ArrayList<Proposal> proposals;
        if (true) { //TODO currentUser.isLeader()
            proposals = new ArrayList<>(ModelSingleton.getInstance().getProposals().values());
        } else {
            //proposals = ModelSingleton.getInstance().listProposalsForPerson(currentUser);
        }
        int creditScore = ModelUtilities.creditScore(proposals);

        adapter = new MyProposalsAdapter(proposals);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreate: " + proposals);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.submit_new_proposal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proposalCreationIntent = new Intent(view.getContext(), ProposalCreationActivity.class);
                view.getContext().startActivity(proposalCreationIntent);
            }
        });

//        riskBar = (ProgressBar)  findViewById(R.id.riskBar);
//        riskBar.setMax(1000000);
//        riskBar.setProgress(creditScore);

    }

}
