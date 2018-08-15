package com.aniketghode.onboardingapp.models;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String name;
    private String birthDate;
    private String birthCountry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }


    public boolean isUpdated() {
       if (name != null && !name.trim().equals("")
                && birthDate != null && !birthDate.trim().equals("")
                && birthCountry != null && !birthCountry.trim().equals("")){
           return true;
       } else {
           return false;
       }
    }
}
