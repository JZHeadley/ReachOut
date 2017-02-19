package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Proposal;

import java.util.ArrayList;

/**
 * Created by mit on 2/19/17.
 */

public class InvestorActivity extends BaseActivity {
    private static final String TAG = "InvestorActivity";


    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_proposals);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //TODO: replace this with the above code once currentUser is implemented
        ArrayList<Proposal> proposals = new ArrayList<>(ModelSingleton.getInstance().getProposals().values());

        adapter = new MyProposalsAdapter(proposals);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreate: " + proposals);


    }


}


