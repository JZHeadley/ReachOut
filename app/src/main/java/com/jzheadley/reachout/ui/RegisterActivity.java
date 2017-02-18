package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amnix.materiallockview.MaterialLockView;
import com.jzheadley.reachout.Constants;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.dataobjects.Person;
import com.sakebook.android.uploadhelper.UploadHelper;
import com.sakebook.android.uploadhelper.UploadTaskCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class RegisterActivity extends BaseActivity implements UploadTaskCallback {
    private static final int PICK_IMAGE_REQUEST = 2;
    private static final String TAG = "RegisterActivity";
    private Person person;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        person = new Person();
        final MaterialLockView materialLockView = (MaterialLockView) findViewById(R.id.pattern);
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                String encodedString = Base64.encodeToString(pattern.toString().getBytes(), Base64.DEFAULT);
                person.setPassHash(encodedString);
                Log.d(TAG, "onPatternDetected: " + encodedString);
            }
        });
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

    @Override
    public void success(String s) {
        person.setProfile_picture(s);
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

    public void onClick(View view) {
        person.setName(((EditText) findViewById(R.id.name_input)).getText().toString());
        person.setLattitude((new Random()).nextDouble() * 100 - 50);
        person.setLongitude((new Random()).nextDouble() * 100 - 50);
        person.setLeader((new Random().nextBoolean()));
        ModelSingleton.getInstance().createPerson(person);
        finish();
    }
}
