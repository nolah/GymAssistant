package pmf.danijela.gymassistant.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.gymassistant.dto.SignInRequest;
import pmf.danijela.gymassistant.dto.SignInResponse;
import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.activity.MainActivity_;
import pmf.danijela.helloworld.activity.SignUpActivity_;
import pmf.danijela.gymassistant.http.PublicApiService;
import pmf.danijela.gymassistant.http.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_sign_in)
public class SignInActivity extends AppCompatActivity {

    @ViewById
    EditText username;

    @ViewById
    EditText password;


    PublicApiService publicApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        publicApiService = RetrofitClientInstance.getPublicRetrofitInstance();
        final SharedPreferences prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        final String token = prefs.getString("token", null);
        final String refreshToken = prefs.getString("refreshToken", null);

        if(token != null && refreshToken != null){
            MainActivity_.intent(this).startForResult(100);
        }else{
            prefs.edit().clear().apply();
        }

    }

    @Click
    void signIn() {
        final String usernameText = username.getText().toString();
        final String passwordText = password.getText().toString();

        final Call<SignInResponse> call = publicApiService.signIn(new SignInRequest(usernameText, passwordText));
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SharedPreferences prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                final SharedPreferences.Editor edit = prefs.edit();
                edit.putString("token", response.body().getAccessToken());
                edit.putString("refreshToken", response.body().getRefreshToken());
                edit.commit();

                MainActivity_.intent(SignInActivity.this).startForResult(100);
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
            }
        });
    }

    @Click
    void goRegister() {
        SignUpActivity_.intent(this).startForResult(100);
    }

}
