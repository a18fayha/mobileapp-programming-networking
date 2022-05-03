package com.example.networking;

public class Mountain {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                '}';
    }
}
