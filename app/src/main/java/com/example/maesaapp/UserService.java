package com.example.maesaapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @POST("api/user/login")
    Call<LoginResponse> loginUsers(@Body com.example.maesaapp.LoginRequest loginRequest);


    @POST("api/user/register")
    Call<com.example.maesaapp.RegisterResponse> registerUsers(@Body com.example.maesaapp.RegisterRequest registerRequest);

    @POST("api/user/validate_email/971552")
    Call<com.example.maesaapp.ValidateEmailResponse> validateEmailUsers(@Body com.example.maesaapp.ValidateEmailRequest validateEmailRequest);

    @GET("api/user/validate_email/971552")
    Call<List<ValidateEmailResponse>> getValidateEmail();

    @GET("api/merchant")
    Call<AddCardActivity> getAddCard();


}
