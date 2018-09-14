package pmf.danijela.helloworld.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.dto.SignInRequest;
import pmf.danijela.helloworld.dto.SignInResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_sign_in)
public class SignInActivity extends BaseActivity {

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                edit.apply();

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
