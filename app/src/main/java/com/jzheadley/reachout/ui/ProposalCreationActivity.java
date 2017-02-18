package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jzheadley.reachout.Constants;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.dataobjects.Proposal;
import com.jzheadley.reachout.views.ThreeButtonView;
import com.sakebook.android.uploadhelper.UploadHelper;
import com.sakebook.android.uploadhelper.UploadTaskCallback;

import java.io.IOException;

public class ProposalCreationActivity extends AppCompatActivity implements UploadTaskCallback {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "ProposalCreationActivit";
    private Proposal proposal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_creation);
        proposal = new Proposal();

        ((ThreeButtonView) findViewById(R.id.three_button_loan_amount)).setPromptText(getString(R.string.prompt_loan_amount));
        ((ThreeButtonView) findViewById(R.id.three_button_repayment_amount)).setPromptText(getString(R.string.prompt_loan_repayment_amount));
        ((ThreeButtonView) findViewById(R.id.three_button_repayment_date)).setPromptText(getString(R.string.prompt_loan_repayment_date));
        ((ThreeButtonView) findViewById(R.id.three_button_money_making)).setPromptText(getString(R.string.prompt_money_making));
        ((ThreeButtonView) findViewById(R.id.three_button_loan_purchase)).setPromptText(getString(R.string.prompt_loan_purchase));
        ((ThreeButtonView) findViewById(R.id.three_button_how_money_help)).setPromptText(getString(R.string.prompt_loan_how_help));
    }

    @Override
    public void success(String url) {
        Log.d(TAG, "success: Things worked! Heres the url to the image you just uploaded " + url);
        // TODO: 2/18/2017 set the url for the proposal
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
        Intent imageGalleryChooserIntent = new Intent();
        imageGalleryChooserIntent.setType("image/*");
        imageGalleryChooserIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(imageGalleryChooserIntent, "Select Picture"),
            PICK_IMAGE_REQUEST);
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
                    Uri pathToFile = data.getData();
                    helper.uploadData(data.getData());
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                            pathToFile);
                        ImageView imageView = (ImageView)
                            findViewById(R.id.add_photo);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }
                break;
        }
    }
}

