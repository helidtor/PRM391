package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    ListView fruitList;
    AdapterFruit adapterFruit;
    Button btnAdd, btnUpdate, btnNext;
    LinearLayout nameFrame, describeFrame, imageFrame, idFrame;
    Button btnAdd2, btnUpdate2, btnCancel, btnDelete;
    EditText inputName, inputDescribe, inputImage, inputID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_fruits);
        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.edit_list_fruits, null);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnNext = findViewById(R.id.btnNext);

        nameFrame = rootView.findViewById(R.id.nameFrame);
        describeFrame = rootView.findViewById(R.id.describeFrame);
        imageFrame = rootView.findViewById(R.id.imageFrame);
        inputID = rootView.findViewById(R.id.inputID);
        btnAdd2 = rootView.findViewById(R.id.btnAdd2);
        btnUpdate2 = rootView.findViewById(R.id.btnUpdate2);
        inputName = rootView.findViewById(R.id.inputName);
        inputDescribe = rootView.findViewById(R.id.inputDescribe);
        inputImage = rootView.findViewById(R.id.inputImage);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnDelete = rootView.findViewById(R.id.btnDelete);
        idFrame = rootView.findViewById(R.id.idFrame);

        fruitList = (ListView) findViewById(R.id.listViewFruits);

        adapterFruit = new AdapterFruit(this, R.layout.second_activity, DataHolder.getFruits());
        fruitList.setAdapter(adapterFruit);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, EditActivity.class);
                //truyền giá trị vào biến status để xét hiển thị button bên layout EditActivity
                Bundle bundle = new Bundle();
                bundle.putInt("status", 1);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                }
            });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, EditActivity.class);
                //truyền giá trị vào biến status để xét hiển thị button bên layout EditActivity
                Bundle bundle = new Bundle();
                bundle.putInt("status", 2);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}
