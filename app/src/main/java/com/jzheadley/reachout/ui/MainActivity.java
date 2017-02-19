package com.jzheadley.reachout.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.jzheadley.reachout.App;
import com.jzheadley.reachout.R;
import com.jzheadley.reachout.models.ModelSingleton;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        ModelSingleton.getInstance().synchWithDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test1:
                SharedPreferences preferences = App.get().getSharedPreferences("ReachOut", MODE_PRIVATE);
                preferences.edit().putString("personId", "1409832601").apply();
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
