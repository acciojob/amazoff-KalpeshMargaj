package com.driver;

import java.util.Arrays;
import java.util.List;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        int HH = Integer.parseInt(deliveryTime.substring(0,2));
        int MM = Integer.parseInt(deliveryTime.substring(3));

        this.deliveryTime  = HH*60 + MM;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public static int getDeliveryTimeInt(String deliveryTime) {
        List<String> l = Arrays.asList(deliveryTime.split(":"));
        int totalTime = (Integer.parseInt(l.get(0)) *60) + Integer.parseInt(l.get(1));
        return totalTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
