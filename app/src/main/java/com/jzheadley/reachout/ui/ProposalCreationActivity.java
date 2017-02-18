package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jzheadley.reachout.Constants;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ThreeButtonView;
import com.sakebook.android.uploadhelper.UploadHelper;
import com.sakebook.android.uploadhelper.UploadTaskCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ProposalCreationActivity extends AppCompatActivity implements UploadTaskCallback {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "ProposalCreationActivit";
    ThreeButtonView loanAmount;
    ThreeButtonView loanRepayAmount;
    ThreeButtonView moneyMaking;
    ThreeButtonView loanLength;
    ThreeButtonView loanPurchase;
    ThreeButtonView reason;
    private Proposal proposal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_creation);
        proposal = new Proposal();
        loanAmount = ((ThreeButtonView) findViewById(R.id.three_button_loan_amount));
        loanRepayAmount = ((ThreeButtonView) findViewById(R.id.three_button_repayment_amount));
        loanLength = ((ThreeButtonView) findViewById(R.id.three_button_loan_length));
        moneyMaking = ((ThreeButtonView) findViewById(R.id.three_button_money_making));
        loanPurchase = ((ThreeButtonView) findViewById(R.id.three_button_loan_purchase));
        reason = ((ThreeButtonView) findViewById(R.id.three_button_how_money_help));

        loanAmount.setPromptText(getString(R.string.prompt_loan_amount));
        loanRepayAmount.setPromptText(getString(R.string.prompt_loan_repayment_amount));
        loanLength.setPromptText(getString(R.string.prompt_loan_repayment_date));
        moneyMaking.setPromptText(getString(R.string.prompt_money_making));
        loanPurchase.setPromptText(getString(R.string.prompt_loan_purchase));
        reason.setPromptText(getString(R.string.prompt_loan_how_help));
    }

    @Override
    public void success(String url) {
        Log.d(TAG, "success: Things worked! Heres the url to the image you just uploaded " + url);
        // TODO: 2/18/2017 set the url for the proposal
        Set<String> set = proposal.getPictures();
        set.add(url);
        proposal.setPictures((HashSet<String>) set);
    }

    @Override
    public void cancel(String s) {

        Toast.makeText(getApplicationContext(), s,
            Toast.LENGTH_LONG).show();
    }

    @Override
    public void fail(String s) {
        Log.d(TAG, "fail: Things didn't work out between us...");
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

    }

    public void onImageClick(View view) {
        /*Intent imageGalleryChooserIntent = new Intent();
        imageGalleryChooserIntent.setType("image*//*");
        imageGalleryChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageGalleryChooserIntent, "Select Picture"),
            PICK_IMAGE_REQUEST);*/
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    UploadHelper helper = new UploadHelper(this, this);
                    helper.setClientId(Constants.IMGUR_CLIENT_ID);
                    helper.setSecretId(Constants.IMGUR_SECRET);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    ImageView imageView = (ImageView) findViewById(R.id.add_photo);
                    imageView.setImageBitmap(bitmap);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                    File destination = new File(Environment.getExternalStorageDirectory(), "temp.png");
                    FileOutputStream fo;
                    try {
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    helper.uploadData(Uri.fromFile(destination));
                }
                break;
        }
    }

    public void onClick(View view) {
        proposal.setProposalId(String.valueOf((new Random()).nextInt()));
        proposal.setAmountBorrowed(Integer.parseInt(loanAmount.getInputText()));
        proposal.setAmountToBeRepayed(Integer.parseInt(loanRepayAmount.getInputText()));
        proposal.setMonthsOfLoan(Integer.parseInt(loanLength.getInputText()));
        proposal.setBusinessDescription(moneyMaking.getInputText());
        proposal.setPurchaseDescription(loanPurchase.getInputText());
        proposal.setPlanDescription(reason.getInputText());
        ModelSingleton.getInstance().createProposal(proposal);
        ModelSingleton.getInstance().synchWithDB();
        // finish();
    }
}

