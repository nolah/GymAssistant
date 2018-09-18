package org.ninja.gymassistant.http;


import org.ninja.gymassistant.dto.RefreshTokenRequest;
import org.ninja.gymassistant.dto.SignInRequest;
import org.ninja.gymassistant.dto.SignInResponse;
import org.ninja.gymassistant.dto.SignUpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PublicApiService {

    @POST("/api/sign-up")
    Call<Void> signUp(@Body SignUpRequest request);

    @POST("/api/sign-in")
    Call<SignInResponse> signIn(@Body SignInRequest request);

    @POST("/api/refresh-token")
    Call<SignInResponse> refreshToken(@Body RefreshTokenRequest request);
}
