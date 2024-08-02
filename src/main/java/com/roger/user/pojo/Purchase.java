package com.roger.user.pojo;

import java.util.Date;

public class Purchase {
    private int id;
    private String item;
    private Date time;
    private boolean paid;

    public Purchase(int orderId, String orderItem, Date purchaseTime, boolean paid) {
        this.id = orderId;
        this.item = orderItem;
        this.time = purchaseTime;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String orderedItem) {
        this.item = orderedItem;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date purchaseTime) {
        this.time = purchaseTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
