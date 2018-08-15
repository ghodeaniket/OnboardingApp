package com.aniketghode.onboardingapp.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    public static String readAssetsFiles(Context context, String fileName) {
        Log.i("Utils", "Load countries frpm local json file.");
        StringBuilder returnString = new StringBuilder();
        InputStreamReader isr = null;
        BufferedReader input = null;
        InputStream is = null;
        try {

            is = context.getResources().getAssets()
                    .open("countries.json");

            isr = new InputStreamReader(is);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }

            return returnString.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return null;
    }
}
