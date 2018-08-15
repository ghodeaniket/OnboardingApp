package com.aniketghode.onboardingapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// As of now as we only need the Country names not parsing other parameters.

public class Country {
    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }
}


