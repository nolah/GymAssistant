package pmf.danijela.gymassistant.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pmf.danijela.helloworld.activity.SignInActivity_;
import pmf.danijela.gymassistant.http.PublicApiService;
import pmf.danijela.gymassistant.http.RetrofitClientInstance;
import pmf.danijela.gymassistant.http.SecuredApiService;

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
