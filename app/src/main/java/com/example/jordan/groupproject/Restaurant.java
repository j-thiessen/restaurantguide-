package com.example.jordan.groupproject;

public class Restaurant {
    private long id;
    private String name;
    private String address;
    private String number;
    private String description;
    private String tags;


    public long getId() {
            return id;
        }
    public void setId(long id) {
            this.id = id;
        }

    public String getName() {
            return name;
        }
    public void setName(String name) {
            this.name = name;
        }

    public String getAddress() {
            return address;
        }
    public void setAddress(String address) {
            this.address = address;
        }

    public String getNumber() {
            return number;
        }
    public void setNumber(String number) {
            this.number = number;
        }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
}


