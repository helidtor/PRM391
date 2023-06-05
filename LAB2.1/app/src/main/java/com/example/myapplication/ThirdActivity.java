package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity{
    private EditText user;
    private EditText pass;
    private Button btnSignIn;
    private TextView signUp;
    private final String REQUIRE = "Cannot be empty!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.SignInButton);
        signUp = findViewById(R.id.SignUp);

        Button nextButton = findViewById(R.id.nextButton);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean checkInput() {
        EditText pass = findViewById(R.id.password);
        String password = pass.getText().toString();
        if(TextUtils.isEmpty(user.getText().toString())) {
            user.setError(REQUIRE);
            return false;
        }
        if(TextUtils.isEmpty(pass.getText().toString())) {
            pass.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void SignIn() {
        if(!checkInput())
            return;
        Toast.makeText(this, "Login Successfully!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void SignUp() {
        Intent intent = new Intent(ThirdActivity.this, FouthActivity.class);
        startActivity(intent);
        finish();
    }

}
