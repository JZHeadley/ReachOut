package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ThreeButtonView;

public class ViewProposalActivity extends AppCompatActivity {
    ThreeButtonView loanAmount;
    ThreeButtonView loanRepayAmount;
    ThreeButtonView moneyMaking;
    ThreeButtonView loanLength;
    ThreeButtonView loanPurchase;
    ThreeButtonView reason;
    Proposal proposal = getIntent().getExtras().getParcelable("singleProposal");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_proposal);

    }
}
