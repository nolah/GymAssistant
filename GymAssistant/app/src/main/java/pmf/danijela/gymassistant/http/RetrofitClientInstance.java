package pmf.danijela.gymassistant.http;

import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import pmf.danijela.gymassistant.dto.RefreshTokenRequest;
import pmf.danijela.gymassistant.dto.SignInResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static final String BASE_URL = "http://10.0.2.2:8080";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";


    public static SecuredApiService getSecuredRetrofitInstance(String token, String refreshToken, SharedPreferences sharedPreferences) {
        final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    final JWT jwt = new JWT(token);
                    String tokenToUse = token;
                    if (jwt.isExpired(0)) {
                        final Call<SignInResponse> call = getPublicRetrofitInstance().refreshToken(new RefreshTokenRequest(refreshToken));
                        final Response<SignInResponse> response = call.execute();
                        final SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("token", response.body().getAccessToken());
                        edit.putString("refreshToken", response.body().getRefreshToken());
                        edit.apply();
                        tokenToUse = response.body().getAccessToken();
                    }

                    return chain.proceed(chain.request().newBuilder().addHeader(AUTHORIZATION, BEARER + tokenToUse).build());
                })
                .build();
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SecuredApiService.class);
    }


    public static PublicApiService getPublicRetrofitInstance() {
        final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PublicApiService.class);
    }
}
