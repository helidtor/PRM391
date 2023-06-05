package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final String REQUIRE = "Cannot be empty!";
    EditText min;
    EditText max;
    Button button;
    TextView number;

    Calendar calendar = Calendar.getInstance();

    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss - dd/MM/yyyy");
    String dateTime = dateFormat.format(calendar.getTime());

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        min = findViewById(R.id.inputMin);
        max = findViewById(R.id.inputMax);
        button = findViewById(R.id.button);
        number = findViewById(R.id.number);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(min.getText().toString())) {
                    min.setError(REQUIRE);
                    return;
                }
                if(TextUtils.isEmpty(max.getText().toString())) {
                    max.setError(REQUIRE);
                    return;
                }
                if (!checkInput())
                    return;
                int inputMin = Integer.parseInt(min.getText().toString());
                int inputMax = Integer.parseInt(max.getText().toString());
                int randomNumber = new Random().nextInt(inputMax);
                while (true) {
                    if (randomNumber < inputMin || randomNumber > inputMax)
                        randomNumber = new Random().nextInt(inputMax);
                    else {
                        number.setText(randomNumber + "\nMin: " + inputMin + ", Max: " + inputMax + "\n" + dateTime);
                        break;
                    }
                }}
        });

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean checkInput() {
        int a = Integer.parseInt(min.getText().toString());
        int b = Integer.parseInt(max.getText().toString());
        if(a > b) {
            Toast.makeText(this, "Min number cannot be greater than Max number!", Toast.LENGTH_LONG).show();
            return false;
        }
        if(a == b) {
            Toast.makeText(this, "Min number cannot be equal to Max number!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}