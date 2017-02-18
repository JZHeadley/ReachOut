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
import android.widget.TextView;
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
import java.util.Random;

public class CashActivity extends AppCompatActivity implements UploadTaskCallback {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "CashActivity";
    ThreeButtonView getCash;
    TextView accountNumber;
    Proposal proposal = getIntent().getExtras().getParcelable("proposal");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cash);
        getCash = ((ThreeButtonView) findViewById(R.id.three_button_get_cash));
        getCash.setPromptText(getString(R.string.prompt_show_to_banker));
        getCash.setEditTextVisibility(View.INVISIBLE);
        getCash.setAddResponseVisibility(View.INVISIBLE);
        getCash.setPlayResponseVisibility(View.INVISIBLE);
        accountNumber = (TextView) findViewById(R.id.account_number);
        accountNumber.setText(proposal.getAccountNumber());
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
}

