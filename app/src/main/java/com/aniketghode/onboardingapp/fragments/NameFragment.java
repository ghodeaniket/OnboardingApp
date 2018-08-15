package com.aniketghode.onboardingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.aniketghode.onboardingapp.R;
import com.aniketghode.onboardingapp.models.UserName;

import org.greenrobot.eventbus.EventBus;


public class NameFragment extends Fragment {

    private final String TAG = "NameFragment";

    EditText nameEditText;

    public NameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_name, container, false);
        nameEditText = (EditText)layout.findViewById(R.id.name_editText);
        nameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Log.d(TAG, nameEditText.getText().toString());
                    EventBus.getDefault().post(new UserName(nameEditText.getText().toString()));
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nameEditText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        return layout;
    }
}
