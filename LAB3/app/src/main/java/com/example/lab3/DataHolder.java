package com.example.lab3;

import java.util.ArrayList;

public class DataHolder {
    private static ArrayList<Fruit> arrayFruits = new ArrayList<>();

    public static void add(Fruit fruit) {
        arrayFruits.add(fruit);
    }

    public static void update(int index, Fruit fruit) {
        arrayFruits.set(index, fruit);
    }

    public static ArrayList<Fruit> getFruits() {
        return arrayFruits;
    }


    static {
        arrayFruits.add(new Fruit(1,"Banana","Yellow banana", "https://media.istockphoto.com/id/173242750/photo/banana-bunch.jpg?s=612x612&w=0&k=20&c=MAc8AXVz5KxwWeEmh75WwH6j_HouRczBFAhulLAtRUU="));
        arrayFruits.add(new Fruit(2,"Grapes","Green grapes", "https://bizweb.dktcdn.net/100/010/432/products/green-grape.jpg?v=1530625676553"));
        arrayFruits.add(new Fruit(3,"Dragon Fruit","Red flesh dragon fruit", "https://redpineinternational.vn/wp-content/uploads/2021/08/thanh-long-ruot-do-01.jpg"));
    }
}
