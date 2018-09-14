package pmf.danijela.helloworld.activity;

import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pmf.danijela.helloworld.R;
import pmf.danijela.helloworld.dto.SignUpRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends BaseActivity {

    @ViewById
    EditText username;

    @ViewById
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
