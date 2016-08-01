package com.solutelabs.androidsamples;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
}
