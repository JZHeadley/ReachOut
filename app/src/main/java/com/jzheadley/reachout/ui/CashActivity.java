package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ThreeButtonView;

public class CashActivity extends BaseActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "CashActivity";
    private ThreeButtonView getCash;
    private TextView accountNumber;
    private Proposal proposal;


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
        accountNumber = (TextView) findViewById(R.id.account_number);
        accountNumber.setText(proposal.getAccountNumber());
    }

}

