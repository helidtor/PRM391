package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button btnAdd, btnUpdate;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private List<String> itemList;
    private int selectedItemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        list = findViewById(R.id.list);

        itemList = new ArrayList<>(); // tạo danh sách lưu trữ item
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList); //tạo adapter
        list.setAdapter(adapter); // kết nối list lưu trữ với list hiển thị bằng adapter

        itemList.add("Android 17");
        itemList.add("PHP");
        itemList.add("iOS");
        itemList.add("Unity");

        list.setEnabled(false);

        Button nextButton = findViewById(R.id.btnNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = input.getText().toString().trim();
                if (!item.isEmpty()) {
                    itemList.add(item);
                    adapter.notifyDataSetChanged();
                    input.setText(null);
                    Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please input an item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Please input new data to update\n Leave blank to remove item", Toast.LENGTH_LONG).show();
                input.setText(itemList.get(position));
                selectedItemIndex = position;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No item to update!", Toast.LENGTH_SHORT).show();
                } else {
                    list.setEnabled(true);
                    btnAdd.setEnabled(false);
                    if(selectedItemIndex == -1)
                        Toast.makeText(MainActivity.this, "Choose an item to update", Toast.LENGTH_SHORT).show();
                }
                String newItem = input.getText().toString().trim();
                if (selectedItemIndex != -1) {
                    if(!newItem.isEmpty()) {
                        itemList.set(selectedItemIndex, newItem);
                        adapter.notifyDataSetChanged();
                        input.setText("");
                        selectedItemIndex = -1;
                        Toast.makeText(MainActivity.this, "Item updated!", Toast.LENGTH_SHORT).show();
                        selectedItemIndex = -1;
                        list.setEnabled(false);
                        btnAdd.setEnabled(true);
                    } else {
                        itemList.remove(selectedItemIndex);
                        adapter.notifyDataSetChanged();
                        input.setText("");
                        selectedItemIndex = -1;
                        Toast.makeText(MainActivity.this, "Item removed!", Toast.LENGTH_SHORT).show();
                        list.setEnabled(false);
                        btnAdd.setEnabled(true);
                    }
                }
            }
        });


    }
}
