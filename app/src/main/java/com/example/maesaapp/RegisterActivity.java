package com.example.maesaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maesaapp.ApiService.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    TextView tv_login;
    EditText et_email,et_pass,et_cpass;
    Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_cpass = findViewById(R.id.et_cpass);
        tv_login = findViewById(R.id.tv_loginn);

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_email.getText().toString())  || TextUtils.isEmpty(et_pass.getText().toString()) || TextUtils.isEmpty(et_cpass.getText().toString())){
                    String message = "all is required..";
                    Toast.makeText(com.example.maesaapp.RegisterActivity.this,message, Toast.LENGTH_LONG).show();
                }else{
                    com.example.maesaapp.RegisterRequest registerRequest = new com.example.maesaapp.RegisterRequest();
                    registerRequest.setEmail(et_email.getText().toString());
                    registerRequest.setPassword(et_pass.getText().toString());
                    registerRequest.setPassword_confirmation(et_cpass.getText().toString());
                    registerUser(registerRequest);
                }


            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.maesaapp.RegisterActivity.this, com.example.maesaapp.LoginActivity.class));
            }
        });

    }
    public void registerUser(com.example.maesaapp.RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<com.example.maesaapp.RegisterResponse>() {
            @Override
            public void onResponse(Call<com.example.maesaapp.RegisterResponse> call, Response<com.example.maesaapp.RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "Succesful..";
                    com.example.maesaapp.RegisterResponse registerResponse = response.body();
                    Toast.makeText(com.example.maesaapp.RegisterActivity.this,message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(com.example.maesaapp.RegisterActivity.this, com.example.maesaapp.ValidateEmail.class).putExtra("data",registerResponse));
                    finish();

                }else{
                    String message = "An error occured please try again later..";
                    Toast.makeText(com.example.maesaapp.RegisterActivity.this,message, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<com.example.maesaapp.RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(com.example.maesaapp.RegisterActivity.this,message,Toast.LENGTH_LONG).show();

            }
        });
    }
}