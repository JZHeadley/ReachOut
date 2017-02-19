package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.models.services.NessieService;
import com.jzheadley.reachout.views.ThreeButtonView;

public class CashActivity extends BaseActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "CashActivity";
    private ThreeButtonView getCash;
    private TextView accountNumber;
    private Proposal proposal;
    private TextView loanAmount;
    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cash);
        proposal = getIntent().getExtras().getParcelable("proposal");

        getCash = ((ThreeButtonView) findViewById(R.id.three_button_get_cash));
        getCash.setPromptText(getString(R.string.prompt_show_to_banker));
        getCash.setEditTextVisibility(View.INVISIBLE);
        getCash.setAddResponseVisibility(View.INVISIBLE);
        getCash.setPlayResponseVisibility(View.INVISIBLE);
        getCash.setPictureVisibility(View.INVISIBLE);
        accountNumber = (TextView) findViewById(R.id.account_number);
        accountNumber.setHint("Account Number");
        accountNumber.setText("56c66be6a73e492741507e04");
        loanAmount = (TextView) findViewById(R.id.loan_amount);
        loanAmount.setText("Withdrawl amount: $"+proposal.getAmountBorrowed());
        b1 = (Button) findViewById(R.id.withdrawl_button);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NessieService.cashWithdrawl(proposal,accountNumber.getText().toString());
            }
        });
    }

}
