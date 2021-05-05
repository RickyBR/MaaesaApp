package com.example.maesaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.maesaapp.ApiService.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class AddCardActivity extends AppCompatActivity {
    LoginResponse loginResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        Intent intent= getIntent();
        if(intent.getExtras() != null){
            loginResponse = (LoginResponse) intent.getSerializableExtra("data");
            Log.e("TAG", "=====>" +loginResponse.getEmail());
        }

        Call<List<AddCardResponse>> addCard = ApiClient.getService().getAddCard();
        addCard.enqueue(new Callback<List<AddCardResponse>>() {
            @Override
            public void onResponse(Call<List<AddCardResponse>> call, Response<List<AddCardResponse>> response) {
                if(response.isSuccessful()){
                    Log.e("Success",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<AddCardResponse>> call, Throwable t) {
                Log.e("Failure",t.getLocalizedMessage());
            }
        });
    }
}