package com.example.prismmart.Model;

public class salesModel {

    String orderNo;
    String orderTime;
    String orderDate;
    String Address;
    String totalBill;

    public salesModel() {
    }

    public salesModel(String orderNo, String orderTime, String orderDate, String address, String totalBill) {
        this.orderNo = orderNo;
        this.orderTime = orderTime;
        this.orderDate = orderDate;
        this.Address = address;
        this.totalBill = totalBill;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }
}
