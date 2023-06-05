package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class EditActivity extends AppCompatActivity {
    LinearLayout nameFrame, describeFrame, imageFrame, idFrame;
    Button btnAdd2, btnUpdate2, btnCancel, btnDelete;
    EditText inputName, inputDescribe, inputImage, inputID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_list_fruits);

        nameFrame = findViewById(R.id.nameFrame);
        describeFrame = findViewById(R.id.describeFrame);
        imageFrame = findViewById(R.id.imageFrame);
        inputID = findViewById(R.id.inputID);
        btnAdd2 = findViewById(R.id.btnAdd2);
        btnUpdate2 = findViewById(R.id.btnUpdate2);
        inputName = findViewById(R.id.inputName);
        inputDescribe = findViewById(R.id.inputDescribe);
        inputImage = findViewById(R.id.inputImage);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        idFrame = findViewById(R.id.idFrame);

        //set các trường hợp bật tắt button khi chọn chức năng từ layout SecondActivity
        Bundle bundle = getIntent().getExtras();
        int status = bundle.getInt("status");
        switch (status) {
            case 1:
                btnAdd2.setVisibility(View.VISIBLE);
                btnUpdate2.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                break;
            case 2:
                btnAdd2.setVisibility(View.INVISIBLE);
                btnUpdate2.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
        }

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = inputID.getText().toString();
                String name = inputName.getText().toString().trim();
                String describe = inputDescribe.getText().toString().trim();
                String img = inputImage.getText().toString().trim();
                if(!name.isEmpty() && !id.isEmpty() && !describe.isEmpty() && !img.isEmpty()) {
                    boolean check = false;
                    int desiredId = Integer.parseInt(id);
                    int index = -1;
                    for (int i = 0; i < DataHolder.getFruits().size(); i++) {
                        Fruit fruit = DataHolder.getFruits().get(i);
                        if (fruit.getId() != Integer.parseInt(id)) {
                            check = true;
                            Toast.makeText(EditActivity.this, "ID already exists!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if(!check) {
                        DataHolder.add(new Fruit(Integer.parseInt(id), name, describe, img));
                        Toast.makeText(EditActivity.this, "Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                } else
                    Toast.makeText(EditActivity.this, "Fill all information to add new!", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = inputID.getText().toString();
                if(id.isEmpty()) {
                    Toast.makeText(EditActivity.this, "Input ID and others information to update!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = false;
                    int desiredId = Integer.parseInt(id);
                    int index = -1;
                    for (int i = 0; i < DataHolder.getFruits().size(); i++) {
                        Fruit fruit = DataHolder.getFruits().get(i);
                        if (fruit.getId() == desiredId) {
                            check = true;
                            index = i;
                            break;
                        }
                    }
                    if(check == false)
                        Toast.makeText(EditActivity.this, "ID doesn't exist! Please input correct ID!", Toast.LENGTH_SHORT).show();
                    else{
                        String name = inputName.getText().toString().trim();
                        String describe = inputDescribe.getText().toString().trim();
                        String img = inputImage.getText().toString().trim();
                        if(name.isEmpty()) name = DataHolder.getFruits().get(index).getName();
                        if(describe.isEmpty()) describe = DataHolder.getFruits().get(index).getDescribe();
                        if(img.isEmpty()) img = DataHolder.getFruits().get(index).getImg();
                        DataHolder.update(index, new Fruit(Integer.parseInt(id), name, describe, img));
                        Toast.makeText(EditActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = inputID.getText().toString();
                if(id.isEmpty()) {
                    Toast.makeText(EditActivity.this, "Input ID to delete!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = false;
                    int desiredId = Integer.parseInt(id);
                    int index = -1;
                    for (int i = 0; i < DataHolder.getFruits().size(); i++) {
                        Fruit fruit = DataHolder.getFruits().get(i);
                        if (fruit.getId() == desiredId) {
                            check = true;
                            index = i;
                            break;
                        }
                    }
                    if(check == false)
                        Toast.makeText(EditActivity.this, "ID doesn't exist! Please input correct ID!", Toast.LENGTH_SHORT).show();
                    else{
                        DataHolder.getFruits().remove(index);
                        Toast.makeText(EditActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
