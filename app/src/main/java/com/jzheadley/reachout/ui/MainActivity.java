package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;
import com.jzheadley.reachout.models.ModelUtilities;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        ModelSingleton.getInstance().synchWithDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ModelUtilities.getCurrentUser() == null)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test1:
                startActivity(new Intent(this, BorrowerActivity.class));
                break;
            case R.id.test2:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.test3:
                startActivity(new Intent(this, InvestorActivity.class));
        }
    }
}
