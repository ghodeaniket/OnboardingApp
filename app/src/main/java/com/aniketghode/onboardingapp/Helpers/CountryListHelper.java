package com.aniketghode.onboardingapp.Helpers;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.aniketghode.onboardingapp.models.Country;
import com.aniketghode.onboardingapp.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CountryListHelper {
    public static String[] populateLocalDBData(Context context) {

        // Read vehicle details
        String data = Utils.readAssetsFiles(context, "countries.json");
        if (!TextUtils.isEmpty(data)) {
            // declare custom type for GSON Parsing
            Type listType = new TypeToken<List<Country>>() {}.getType();
            List<Country> countryModel = new Gson().fromJson(data, listType);

            return getCountiesArray(countryModel);
        }
        return null;
    }

    private static String[] getCountiesArray(List<Country> countries) {
        String[] countriesArray = new String[countries.size()];
        for (int i = 0; i < countries.size(); i++) {
            countriesArray[i] = countries.get(i).getName();
        }
        return countriesArray;
    }
}
