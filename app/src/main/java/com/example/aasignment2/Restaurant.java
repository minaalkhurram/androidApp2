package com.example.aasignment2;

public class Restaurant {

    protected String name;
    protected String location;
    protected String phone;
    protected String description;
    protected float ratings;

    public Restaurant(String name, String location, String phone, String description,float ratings) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.ratings=ratings;
    }

    String getName()
    {
        return this.name;
    }
    String getLoc()
    {
        return this.location;
    }
    String getPhone()
    {
        return this.phone;
    }
    String getDesc()
    {
        return this.description;
    }

    float getRatings(){return this.ratings;}




}


