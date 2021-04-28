package com.example.maesaapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("api/user/login")
    Call<LoginResponse> loginUsers(@Body com.example.maesaapp.LoginRequest loginRequest);


    @POST("api/user/register")
    Call<com.example.maesaapp.RegisterResponse> registerUsers(@Body com.example.maesaapp.RegisterRequest registerRequest);

    @POST("api/user/validate_email/971552")
    Call<com.example.maesaapp.ValidateEmailResponse> validateEmailUsers(@Body com.example.maesaapp.ValidateEmailRequest validateEmailRequest);

    @GET("api/user/validate_email/971552")
    Call<List<ValidateEmailResponse>> getValidateEmail();


}
