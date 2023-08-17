package com.example.prismmart.Model;

public class cartModel {


    String productID;
    String productCategory;
    String productName;
    String productPrice;
    String totalQuantity;
    String TotalPrice;


    public cartModel() {
    }

    public cartModel(String productCategory, String productName, String totalQuantity, String totalPrice) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        TotalPrice = totalPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }
}
