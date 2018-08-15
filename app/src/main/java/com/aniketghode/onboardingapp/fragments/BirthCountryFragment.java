package com.aniketghode.onboardingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.aniketghode.onboardingapp.Helpers.CountryListHelper;
import com.aniketghode.onboardingapp.R;
import com.aniketghode.onboardingapp.models.Country;
import com.aniketghode.onboardingapp.models.UserBirthCountry;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BirthCountryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BirthCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BirthCountryFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner countrySpinner;
    String[] country;

    public BirthCountryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_birth_country, container, false);

        countrySpinner = layout.findViewById(R.id.country_spinner);

        initializeCountriesSpinner();

        return layout;
    }

    private void initializeCountriesSpinner() {
        country = CountryListHelper.populateLocalDBData(getContext());

        countrySpinner.setOnItemSelectedListener(this);

        ArrayAdapter countriesAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, country);

        countrySpinner.setAdapter(countriesAdapter);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        String selectedCoutry = country[position];
        EventBus.getDefault().post(new UserBirthCountry(selectedCoutry));
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // DO NOTHING
    }
}
