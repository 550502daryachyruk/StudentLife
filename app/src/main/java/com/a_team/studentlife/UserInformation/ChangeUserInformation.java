package com.a_team.studentlife.UserInformation;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.a_team.studentlife.R;
import com.a_team.studentlife.Server.APIService;
import com.a_team.studentlife.Server.Retrofit.ApiUtils;
import com.a_team.studentlife.Server.ServerResponse.ChangeUserInformationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeUserInformation extends AppCompatActivity implements View.OnClickListener {

    AnimationDrawable animationDrawable;
    LinearLayout linearLayout;
    private EditText emailTextField;
    private EditText firstNameTextField;
    private EditText secondNameTextField;
    private EditText passwordTextField;
    private EditText rePasswordTextField;
    private EditText reRePasswordTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        emailTextField = (EditText) findViewById(R.id.emailTextField);
        firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        secondNameTextField = (EditText) findViewById(R.id.secondNameTextField);
        passwordTextField = (EditText) findViewById(R.id.passwordTextField);
        rePasswordTextField = (EditText) findViewById(R.id.rePasswordTextField);
        reRePasswordTextField = (EditText) findViewById(R.id.reRePasswordTextField);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        findViewById(R.id.changeInformationBottom).setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        String password;
        if (rePasswordTextField.getText() == reRePasswordTextField.getText()) {
            password = rePasswordTextField.getText().toString();
        } else {
            password = passwordTextField.getText().toString();
        }
        if (view.getId() == R.id.changeInformationBottom) {
            APIService mAPIService = ApiUtils.getAPIService();
            mAPIService.changeUserInformation("6", emailTextField.getText().toString(), firstNameTextField.getText().toString(),
                    secondNameTextField.getText().toString(), password)
                    .enqueue(new Callback<ChangeUserInformationResponse>() {
                        @Override
                        public void onResponse(Call<ChangeUserInformationResponse> call, Response<ChangeUserInformationResponse> response) {
                            String error = response.body().getError();
                            if (response.isSuccessful() && response.body().getError().equals("ok")) {
                                Toast.makeText(ChangeUserInformation.this, "Успешная замена данных",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ChangeUserInformation.this, "Ошибка замены данных" + " " + error,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangeUserInformationResponse> call, Throwable t) {
                            Toast.makeText(ChangeUserInformation.this, "Ошибка при авторизации",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
