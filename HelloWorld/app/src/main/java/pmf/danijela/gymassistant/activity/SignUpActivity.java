package pmf.danijela.gymassistant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.gymassistant.dto.SignUpRequest;
import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.activity.SignInActivity_;
import pmf.danijela.gymassistant.http.PublicApiService;
import pmf.danijela.gymassistant.http.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity {

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    PublicApiService publicApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        publicApiService = RetrofitClientInstance.getPublicRetrofitInstance();
    }

    @Click
    void register() {
       final String usernameText = username.getText().toString();
       final String passwordText = password.getText().toString();

        Call<Void> call = publicApiService.signUp(new SignUpRequest(usernameText, passwordText));
        final SignUpActivity thisActivity = this;
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                SignInActivity_.intent(thisActivity).startForResult(100);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final Throwable a = t;
            }
        });
    }

}
