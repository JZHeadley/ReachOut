package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelUtilities;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ExplainFieldView;
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
        loanAmount.setEditTextVisibility(View.INVISIBLE);
        loanRepayAmount.setPlayResponseVisibility(View.INVISIBLE);
        loanRepayAmount.setAddResponseVisibility(View.INVISIBLE);
        loanRepayAmount.setEditTextVisibility(View.INVISIBLE);
        loanLength.setPlayResponseVisibility(View.INVISIBLE);
        loanLength.setAddResponseVisibility(View.INVISIBLE);
        loanLength.setEditTextVisibility(View.INVISIBLE);
        moneyMaking.setPlayResponseVisibility(View.INVISIBLE);
        moneyMaking.setAddResponseVisibility(View.INVISIBLE);
        moneyMaking.setEditTextVisibility(View.INVISIBLE);
        loanPurchase.setPlayResponseVisibility(View.INVISIBLE);
        loanPurchase.setAddResponseVisibility(View.INVISIBLE);
        loanPurchase.setEditTextVisibility(View.INVISIBLE);
        reason.setPlayResponseVisibility(View.INVISIBLE);
        reason.setAddResponseVisibility(View.INVISIBLE);
        reason.setEditTextVisibility(View.INVISIBLE);

        loanAmount.setPromptText(Integer.toString(R.string.response_loan_amount)+" "+proposal.getAmountBorrowed()+" dollars");
        loanRepayAmount.setPromptText(Integer.toString(proposal.getAmountToBeRepayed())+Integer.toString(R.string.response_loan_repayment_amount));
        moneyMaking.setPromptText(Integer.toString(R.string.response_money_making)+" "+proposal.getBusinessDescription());
        loanLength.setPromptText(Integer.toString(R.string.response_loan_repayment_date)+" "+new Date(ModelUtilities.dueDate(proposal)));
        loanPurchase.setPromptText(Integer.toString(R.string.response_loan_purchase)+" "+proposal.getPurchaseDescription());
        reason.setPromptText(Integer.toString(R.string.response_loan_how_help)+" "+proposal.getPlanDescription());
    }
}
