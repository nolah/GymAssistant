package pmf.danijela.helloworld.http;


import pmf.danijela.helloworld.dto.RefreshTokenRequest;
import pmf.danijela.helloworld.dto.SignInRequest;
import pmf.danijela.helloworld.dto.SignInResponse;
import pmf.danijela.helloworld.dto.SignUpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PublicApiService {

    @POST("/api/sign-up")
    Call<Void> signUp(@Body SignUpRequest request);

    @POST("/api/sign-in")
    Call<SignInResponse> signIn(@Body SignInRequest request);

    @POST
    Call<SignInResponse> refreshToken(@Body RefreshTokenRequest request);
}
