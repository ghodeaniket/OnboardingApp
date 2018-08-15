package com.aniketghode.onboardingapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aniketghode.onboardingapp.R;
import com.aniketghode.onboardingapp.models.UserInfo;

public class UserSummaryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UserSummaryActivity";

    TextView userNameTextView;
    TextView userBirthDateTextView;
    TextView userBirthCountryTextView;

    Button confirmButton;
    Button restartOnboardingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_summary);

        userNameTextView = findViewById(R.id.summary_username);
        userBirthDateTextView = findViewById(R.id.summary_birthdate);
        userBirthCountryTextView = findViewById(R.id.summary_birth_country);

        confirmButton = findViewById(R.id.confirm_button);
        restartOnboardingButton = findViewById(R.id.restart_onboarding);

        confirmButton.setOnClickListener(this);
        restartOnboardingButton.setOnClickListener(this);

        renderUserInfomation();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_button:
                finishAffinity();
                System.exit(0);
                break;
            case R.id.restart_onboarding:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void renderUserInfomation() {
        Intent intent = getIntent();
        UserInfo userInfo = (UserInfo) intent.getSerializableExtra(MainActivity.USER_INFORMATION);

        Log.d(TAG, "launchUserSummary: name: "+userInfo.getName());
        Log.d(TAG, "launchUserSummary: birthdate: "+userInfo.getBirthDate());
        Log.d(TAG, "launchUserSummary: birthcountry: "+userInfo.getBirthCountry());
        userNameTextView.setText(userInfo.getName());
        userBirthDateTextView.setText(userInfo.getBirthDate());
        userBirthCountryTextView.setText(userInfo.getBirthCountry());

    }

}
