package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelUtilities;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ThreeButtonView;

import java.sql.Date;

public class ViewProposalActivity extends AppCompatActivity {
    ThreeButtonView loanAmount;
    ThreeButtonView loanRepayAmount;
    ThreeButtonView moneyMaking;
    ThreeButtonView loanLength;
    ThreeButtonView loanPurchase;
    ThreeButtonView reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_proposal);
        Proposal proposal = getIntent().getExtras().getParcelable("singleProposal");

        loanAmount = ((ThreeButtonView) findViewById(R.id.three_button_get_loan_amount));
        loanRepayAmount = ((ThreeButtonView) findViewById(R.id.three_button_get_repayment_amount));
        loanLength = ((ThreeButtonView) findViewById(R.id.three_button_get_loan_length));
        moneyMaking = ((ThreeButtonView) findViewById(R.id.three_button_get_money_making));
        loanPurchase = ((ThreeButtonView) findViewById(R.id.three_button_get_loan_purchase));
        reason = ((ThreeButtonView) findViewById(R.id.three_button_get_how_money_help));

        loanAmount.setPlayResponseVisibility(View.INVISIBLE);
        loanAmount.setAddResponseVisibility(View.INVISIBLE);
        loanRepayAmount.setPlayResponseVisibility(View.INVISIBLE);
        loanRepayAmount.setAddResponseVisibility(View.INVISIBLE);
        loanLength.setPlayResponseVisibility(View.INVISIBLE);
        loanLength.setAddResponseVisibility(View.INVISIBLE);
        moneyMaking.setPlayResponseVisibility(View.INVISIBLE);
        moneyMaking.setAddResponseVisibility(View.INVISIBLE);
        loanPurchase.setPlayResponseVisibility(View.INVISIBLE);
        loanPurchase.setAddResponseVisibility(View.INVISIBLE);
        reason.setPlayResponseVisibility(View.INVISIBLE);
        reason.setPlayResponseVisibility(View.INVISIBLE);

        loanAmount.setPromptText(getString(R.string.response_loan_amount) + " " + Integer.toString(proposal.getAmountBorrowed()) + " dollars");
        loanRepayAmount.setPromptText(Integer.toString(proposal.getAmountToBeRepayed()) + getString(R.string.response_loan_repayment_amount));
        moneyMaking.setPromptText(getString(R.string.response_money_making) + " " + proposal.getBusinessDescription());
        loanLength.setPromptText(getString(R.string.response_loan_repayment_date) + " " + new Date(ModelUtilities.dueDate(proposal)));
        loanPurchase.setPromptText(getString(R.string.response_loan_purchase) + " " + proposal.getPurchaseDescription());
        reason.setPromptText(getString(R.string.response_loan_how_help) + " " + proposal.getPlanDescription());
    }
}
