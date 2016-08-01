package com.solutelabs.androidsamples.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.solutelabs.androidsamples.R;

/**
 * Created by stllpt038 on 1/8/16.
 */

public class RealTimeDatabaseFragment extends Fragment {

    private static final String TAG = "RealTimeDBFragment";

    private TextView text;
    private DatabaseReference reference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("message");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realtime_database, container, false);
        text = (TextView) view.findViewById(R.id.text);

        view.findViewById(R.id.btn_welcome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.setValue("Welcome");
            }
        });
        view.findViewById(R.id.btn_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.setValue("Hello");
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                text.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        reference.setValue("Hello, World!");
    }
}
