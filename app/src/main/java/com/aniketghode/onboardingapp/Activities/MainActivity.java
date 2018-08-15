package com.aniketghode.onboardingapp.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.aniketghode.onboardingapp.Adapters.ViewPagerAdapter;
import com.aniketghode.onboardingapp.CustomViews.CustomViewPager;
import com.aniketghode.onboardingapp.R;
import com.aniketghode.onboardingapp.fragments.BirthCountryFragment;
import com.aniketghode.onboardingapp.fragments.BirthDateFragment;
import com.aniketghode.onboardingapp.fragments.NameFragment;
import com.aniketghode.onboardingapp.models.Country;
import com.aniketghode.onboardingapp.models.UserBirthCountry;
import com.aniketghode.onboardingapp.models.UserBirthDate;
import com.aniketghode.onboardingapp.models.UserInfo;
import com.aniketghode.onboardingapp.models.UserName;
import com.aniketghode.onboardingapp.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String USER_INFORMATION = "User Information";
    private final String TAG = "MainActivity";

    private CustomViewPager viewPager;
    private UserInfo userInfo = new UserInfo();

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // EventBus Subscription for communication between Fragments and Activities

    @Subscribe
    public void onMessage(UserName userName) {
        userInfo.setName(userName.getValue());
        viewPager.setCurrentItem(1,true);
    }

    @Subscribe
    public void onMessage(UserBirthCountry birthCountry) {
        userInfo.setBirthCountry(birthCountry.getValue());
        launchUserSummary();
    }

    @Subscribe
    public void onMessage(UserBirthDate birthDate) {
        userInfo.setBirthDate(birthDate.getValue());
        viewPager.setCurrentItem(2,true);
    }

    /* Launch User Summary after Onboarding. */

    private void launchUserSummary() {

        if (userInfo.isUpdated()) {
            Intent intent = new Intent(getApplicationContext(), UserSummaryActivity.class);
            intent.putExtra(USER_INFORMATION, userInfo);
            startActivity(intent);
        }
    }

    // Custom View Pager Setup.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        addTabs(viewPager);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NameFragment(), "Name");
        adapter.addFrag(new BirthDateFragment(), "Birth Date");
        adapter.addFrag(new BirthCountryFragment(), "Birth Country");
        viewPager.setAdapter(adapter);
    }
}
