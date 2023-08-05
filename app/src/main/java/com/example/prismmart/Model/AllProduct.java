package com.example.prismmart.Model;

public class AllProduct {

    String productName;
    String productCatagory;
    String productDescription;
    String productPrice;
    String Product_imageUrl;


    public AllProduct() {
    }

    public AllProduct(String productName, String productCatagory, String productDescription, String productPrice, String product_imageUrl) {
        this.productName = productName;
        this.productCatagory = productCatagory;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        Product_imageUrl = product_imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCatagory() {
        return productCatagory;
    }

    public void setProductCatagory(String productCatagory) {
        this.productCatagory = productCatagory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProduct_imageUrl() {
        return Product_imageUrl;
    }

    public void setProduct_imageUrl(String product_imageUrl) {
        Product_imageUrl = product_imageUrl;
    }
}
