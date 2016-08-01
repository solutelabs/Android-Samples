package com.solutelabs.androidsamples;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.crash.FirebaseCrash;
import com.solutelabs.androidsamples.firebase.AnalyticsFragment;
import com.solutelabs.androidsamples.firebase.RealTimeDatabaseFragment;
import com.solutelabs.androidsamples.firebase.auth.AuthHelper;

public class MainActivity extends AppCompatActivity {


    private AuthHelper authHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authHelper = new AuthHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        authHelper.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        authHelper.onStop();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_crash: {
                FirebaseCrash.report(new Exception("Test Android non-fatal error"));
                return;
            }
            case R.id.btn_database: {
                replace(new RealTimeDatabaseFragment());
                return;
            }
            case R.id.btn_analytics: {
                replace(new AnalyticsFragment());
                return;
            }
        }
    }

    private void replace(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commitNowAllowingStateLoss();
    }

}
