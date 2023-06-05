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
public class FouthActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private EditText rePass;
    private Button signUp;
    private TextView signIn;
    private final String REQUIRE = "Cannot be empty!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        rePass = findViewById(R.id.rePassword);
        signUp = findViewById(R.id.SignUpButton);
        signIn = findViewById(R.id.SignIn);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FouthActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
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
        if(TextUtils.isEmpty(rePass.getText().toString())) {
            rePass.setError(REQUIRE);
            return false;
        }
        if(!TextUtils.equals(pass.getText().toString(), rePass.getText().toString())) {
            Toast.makeText(this, "Password are not match", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[a-z].*") || password.length() < 8 || password.length() > 16) {
            pass.setError("Password must be 8-16 characters and contain at least one uppercase, lowercase and digit!");
            return false;
        }
        return true;
    }
    private void signUp() {
        if(!checkInput())
            return;
        Toast.makeText(this, "Sign Up Successfully!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(FouthActivity.this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }

    private void signIn() {
        Intent intent = new Intent(FouthActivity.this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }
}
