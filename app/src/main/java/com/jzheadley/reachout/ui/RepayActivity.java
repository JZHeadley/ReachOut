package com.jzheadley.reachout.ui;

import android.content.Context;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.models.services.NessieService;
import com.jzheadley.reachout.views.ThreeButtonView;

public class RepayActivity extends BaseActivity {
    private static final String TAG = "RepayActivity";
    private Proposal proposal;
    private Button b1;
    private TextToSpeech ttobj;
    private SpeechRecognizer speechRecognizer;
    private RecognitionProgressView recognitionProgressView;
    private TextView repayAmount, lenderAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        CharSequence text = "Thank you, your loan has be repayed";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);
        setContentView(R.layout.activity_repay);
        repayAmount = (TextView) findViewById(R.id.repay_amount);
        lenderAccount = (TextView) findViewById(R.id.lender_account);
        proposal = getIntent().getExtras().getParcelable("proposal");

        repayAmount.setText("Please deposit $"+proposal.getAmountToBeRepayed());
        lenderAccount.setText("Into account number "+proposal.getLenderAccountNumber());

        b1 = (Button) findViewById(R.id.repay_button);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(proposal.getAmountToBeRepayed() <= NessieService.getBalance(proposal)) {
                    NessieService.repay(proposal);
                    toast.show();
                }
            }
        });
    }

}
