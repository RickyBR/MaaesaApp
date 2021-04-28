package com.example.maesaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maesaapp.ApiService.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText et_email,et_pass,et_cpass;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_cpass = findViewById(R.id.et_pass);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_email.getText().toString())  || TextUtils.isEmpty(et_pass.getText().toString()) || TextUtils.isEmpty(et_cpass.getText().toString())){
                    String message = "all is required..";
                    Toast.makeText(com.example.maesaapp.LoginActivity.this,message, Toast.LENGTH_LONG).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(et_email.getText().toString());
                    loginRequest.setPassword(et_pass.getText().toString());
                    loginRequest.setPassword(et_cpass.getText().toString());
                    loginUser(loginRequest);
                }
            }
        });
    }

    public  void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUsers(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){

                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(com.example.maesaapp.LoginActivity.this, MainActivity.class).putExtra("data",loginResponse));
                    finish();
                }else {
                    String message = "An error occured please try again later..";
                    Toast.makeText(com.example.maesaapp.LoginActivity.this,message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(com.example.maesaapp.LoginActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });

    }
}