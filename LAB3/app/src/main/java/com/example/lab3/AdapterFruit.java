package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.squareup.picasso.Picasso;

public class AdapterFruit extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> fruitList;

    public AdapterFruit(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView id = view.findViewById(R.id.id);
        TextView name = view.findViewById(R.id.name);
        TextView describe = view.findViewById(R.id.describe);
        ImageView img = view.findViewById(R.id.img);

        Fruit fruit = fruitList.get(i);
        id.setText(String.valueOf(fruit.getId()));
        name.setText(fruit.getName());
        describe.setText(fruit.getDescribe());
        Picasso.get().load(fruit.getImg()).into(img); //thêm ảnh bằng link url

        return view;
    }
}

