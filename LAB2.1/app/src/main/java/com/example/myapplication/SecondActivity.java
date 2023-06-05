package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private final String REQUIRE = "Cannot be empty!";
    EditText firstNumber;
    EditText secondNumber;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView numberResult;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        firstNumber = findViewById(R.id.input1);
        secondNumber = findViewById(R.id.input2);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        numberResult = findViewById(R.id.number);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInput())
                    return;
                float number1 = Float.parseFloat(firstNumber.getText().toString());
                float number2 = Float.parseFloat(secondNumber.getText().toString());
                float sum = number1 + number2;
                numberResult.setText(String.valueOf(sum));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInput())
                    return;
                float number1 = Float.parseFloat(firstNumber.getText().toString());
                float number2 = Float.parseFloat(secondNumber.getText().toString());
                float sub = (number1 - number2);
                numberResult.setText(String.valueOf(sub));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInput())
                    return;
                float number1 = Float.parseFloat(firstNumber.getText().toString());
                float number2 = Float.parseFloat(secondNumber.getText().toString());
                float multi = (number1 * number2);
                DecimalFormat formatDivision = new DecimalFormat("#.#########");
                String roundedResult = formatDivision.format(multi);
                numberResult.setText(String.valueOf(roundedResult));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInput())
                    return;
                if(!checkZero())
                    return;
                float number1 = Float.parseFloat(firstNumber.getText().toString());
                float number2 = Float.parseFloat(secondNumber.getText().toString());
                float division = number1 / number2;
                DecimalFormat formatDivision = new DecimalFormat("#.#########");
                String roundedResult = formatDivision.format(division);
                numberResult.setText(String.valueOf(roundedResult));
            }
        });

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean checkInput() {
        if(TextUtils.isEmpty(firstNumber.getText().toString())) {
            firstNumber.setError(REQUIRE);
            return false;
        }
        if(TextUtils.isEmpty(secondNumber.getText().toString())) {
            secondNumber.setError(REQUIRE);
            return false;
        }
        return true;
    }
    private boolean checkZero() {
        float b = Float.parseFloat(secondNumber.getText().toString());
        if(b == 0) {
            Toast.makeText(this, "Second number cannot be zero!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
