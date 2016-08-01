package com.solutelabs.androidsamples.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.solutelabs.androidsamples.R;

/**
 * Created by stllpt038 on 1/8/16.
 */

public class AnalyticsFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Analytics.with(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analytics, container, false);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_device_interests);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(id);
                Analytics.get().setPreference(radioButton.getText().toString().toLowerCase());
            }
        });
        view.findViewById(R.id.btn_tutorials_begin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Analytics.Tutorial.begin();
            }
        });
        view.findViewById(R.id.btn_tutorials_complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Analytics.Tutorial.complete();
            }
        });
        view.findViewById(R.id.btn_process_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Analytics.Account.loginStart();
            }
        });
        view.findViewById(R.id.btn_process_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Analytics.Account.loginSuccess();
            }
        });
        view.findViewById(R.id.btn_process_failed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Analytics.Account.loginFailed();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Analytics.Account.loginView();
    }
}
