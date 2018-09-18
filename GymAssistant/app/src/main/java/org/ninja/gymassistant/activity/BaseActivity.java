package org.ninja.gymassistant.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.ninja.gymassistant.activity.SignInActivity_;
import org.ninja.gymassistant.http.PublicApiService;
import org.ninja.gymassistant.http.RetrofitClientInstance;
import org.ninja.gymassistant.http.SecuredApiService;

public abstract class BaseActivity extends AppCompatActivity {

    PublicApiService publicApiService;

    SecuredApiService securedApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString("token", null);
        final String refreshToken = sharedPreferences.getString("refreshToken", null);

        publicApiService = RetrofitClientInstance.getPublicRetrofitInstance();
        securedApiService = RetrofitClientInstance.getSecuredRetrofitInstance(token, refreshToken, sharedPreferences);

        if (token == null || refreshToken == null) {
            SignInActivity_.intent(this).startForResult(101);
            return;
        }
    }

    public PublicApiService getPublicApiService() {
        return publicApiService;
    }

    public SecuredApiService getSecuredApiService() {
        return securedApiService;
    }

}
