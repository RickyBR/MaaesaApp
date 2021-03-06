package com.example.maesaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maesaapp.ApiService.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidateEmail extends AppCompatActivity {
    EditText et_validate_email,et_emaill;
    Button btn_submit;
        com.example.maesaapp.RegisterResponse registerResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_email);
        et_validate_email = findViewById(R.id.et_validate_email);


        btn_submit = findViewById(R.id.btn_submit);
        Intent intent= getIntent();
        if(intent.getExtras() != null){
            registerResponse = (com.example.maesaapp.RegisterResponse) intent.getSerializableExtra("data");
            Log.e("TAG", "=====>" +registerResponse.getEmail());
        }


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_validate_email.getText().toString())){
                    String message = "all is required..";
                    Toast.makeText(ValidateEmail.this,message, Toast.LENGTH_LONG).show();
                }else{
                    ValidateEmailRequest validateEmailRequest = new ValidateEmailRequest();

                    validateEmailRequest.setRemember_token(et_validate_email.getText().toString());

                    validateEmailSubmit(validateEmailRequest);

                    Call<List<ValidateEmailResponse>> validateEmail = ApiClient.getService().getValidateEmail();
                    validateEmail.enqueue(new Callback<List<ValidateEmailResponse>>() {
                        @Override
                        public void onResponse(Call<List<ValidateEmailResponse>> call, Response<List<ValidateEmailResponse>> response) {
                            if(response.isSuccessful()){
                                Log.e("Success",response.body().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ValidateEmailResponse>> call, Throwable t) {
                            Log.e("Failure",t.getLocalizedMessage());
                        }
                    });
                }

            }
        });

    }



    public void validateEmailSubmit(ValidateEmailRequest validateEmailRequest){
        Call<com.example.maesaapp.ValidateEmailResponse> validateEmailResponseCall = ApiClient.getService().validateEmailUsers(validateEmailRequest);
        validateEmailResponseCall.enqueue(new Callback<com.example.maesaapp.ValidateEmailResponse>() {
            @Override
            public void onResponse(Call<com.example.maesaapp.ValidateEmailResponse> call, Response<com.example.maesaapp.ValidateEmailResponse> response) {
                if(response.isSuccessful()){

                    com.example.maesaapp.ValidateEmailResponse validateEmailResponse = response.body();
                    startActivity(new Intent(com.example.maesaapp.ValidateEmail.this, HomeActivity.class));
                    finish();
                }else {
                    String message = "An error occured please try again later..";
                    Toast.makeText(com.example.maesaapp.ValidateEmail.this,message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.maesaapp.ValidateEmailResponse> call, Throwable t) {
                String message = "An error occured please try again later..";
                Toast.makeText(com.example.maesaapp.ValidateEmail.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
}