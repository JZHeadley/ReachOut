package com.jzheadley.reachout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jzheadley.reachout.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Drawer drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResId) {
        LinearLayout fullView =
            (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResId, activityContainer, true);
        super.setContentView(fullView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // to opt out of using the toolbar override useToolbar() and return false
        if (useToolbar()) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        } else {
            toolbar.setVisibility(View.GONE);
        }
        drawer = createDrawer(toolbar, this);

    }

    protected boolean useToolbar() {
        return true;
    }

    public void onDrawerClick(View view) {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else if (!(drawer.isDrawerOpen())) {
            drawer.openDrawer();
        }
    }

    public Drawer createDrawer(final Toolbar toolbar, final BaseActivity activity) {
        Drawer drawer = new DrawerBuilder()
            .withActivity(activity)
            .withDisplayBelowStatusBar(true)
            .addDrawerItems(
                new PrimaryDrawerItem().withName("Temp")
                    .withIcon(R.drawable.ic_error)
            )
            .withDrawerGravity(Gravity.END)
            .build();
        return drawer;
    }

}
