package com.jzheadley.reachout.ui;

import android.app.Activity;
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
import com.jzheadley.reachout.models.dataobjects.Person;
import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.ArrayList;
import java.util.HashMap;

public class BorrowerActivity extends Activity {
    private static final String TAG = "BorrowerActivity";
    public ProgressBar riskBar;
    public int riskStatus = 0;
    private Person person;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower);
        ModelSingleton.getInstance().synchWithDB();
        HashMap<String, Proposal> proposals = ModelSingleton.getInstance().getProposals();
        adapter = new MyProposalsAdapter(new ArrayList<Proposal>(proposals.values()));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_proposals);
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


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

//        int creditScore = ModelUtilities.creditScore(proposals);
//        riskBar = (ProgressBar)  findViewById(R.id.riskBar);
//        riskBar.setMax(1000000);
//        riskBar.setProgress(creditScore);


    }

}
