package com.aniketghode.onboardingapp.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.aniketghode.onboardingapp.R;
import com.aniketghode.onboardingapp.models.UserBirthDate;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.TimeZone;


public class BirthDateFragment extends Fragment implements View.OnClickListener{
    private TextView selectBirthDateTextView;
    private Dialog datePickerDialog;

    private DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.d("BirthDateFragment"," Day: "+dayOfMonth);
            String birthDate = getString(R.string.birthdate_string,month,dayOfMonth,year);
            EventBus.getDefault().post(new UserBirthDate(birthDate));
        }
    };

    public BirthDateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_birth_date, container, false);
        selectBirthDateTextView = (TextView) layout.findViewById(R.id.select_birth_date_textView);
        selectBirthDateTextView.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.select_birth_date_textView){
            showDatePickerDialog();
        }
    }

    /**
     * This method returns the new instance of the time picker
     *
     * @return
     */
    private void showDatePickerDialog() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());

        datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog, datepickerListener,cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH) );
        datePickerDialog.show();
    }
}
