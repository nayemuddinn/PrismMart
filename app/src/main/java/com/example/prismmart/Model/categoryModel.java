package com.example.prismmart.Model;

public class categoryModel {

    String image_url;
    String name;
    String type;


    public categoryModel() {
    }

    public categoryModel(String image_url, String name, String type) {
        this.image_url = image_url;
        this.name = name;
        this.type = type;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
