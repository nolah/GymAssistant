package pmf.danijela.gymassistant.http;


import pmf.danijela.gymassistant.dto.RefreshTokenRequest;
import pmf.danijela.gymassistant.dto.SignInRequest;
import pmf.danijela.gymassistant.dto.SignInResponse;
import pmf.danijela.gymassistant.dto.SignUpRequest;
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
