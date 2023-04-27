package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.driver.Order.getDeliveryTimeInt;
@Repository
public class OrderRepository {
    HashMap<String,Order> orderDb = new HashMap<>();
    HashMap<String ,DeliveryPartner> partnerDb = new HashMap<>();
    HashMap<String,String> orderPartnerDb = new HashMap<>();
    HashMap<String, HashSet<String>> partnerOrderListDb = new HashMap<>();
    //partnerId,HashSet<order>
    public void addOrder(Order order)
    {
        String key = order.getId();
        orderDb.put(key,order);
    }

    public void addPartner(DeliveryPartner deliveryPartner)
    {
        String key = deliveryPartner.getId();
        partnerDb.put(key,deliveryPartner);
    }
    public void addOrderPartnerPair(String orderID,String partnerID)
    {
        orderPartnerDb.put(orderID,partnerID);
        HashSet<String> ans =partnerOrderListDb.get(partnerID);
        ans.add(orderID);
        partnerOrderListDb.put(partnerID,ans);
    }

    public Order getOrderById(String orderId)
    {
        Order ans=orderDb.get(orderId);
        return ans;
    }
    public DeliveryPartner getPartnerById(String partnerId)
    {
        DeliveryPartner ans = partnerDb.get(partnerId);
        return ans;
    }

    public int getOrderCountByPartnerId(String partnerId)
    {
        HashSet<String> l=partnerOrderListDb.get(partnerId);
        int ans = l.size();
        return ans;
    }
    public List<String> getOrdersByPartnerId(String partnerId)
    {
        List<String> ans= new ArrayList<>();
        HashSet<String> l=partnerOrderListDb.get(partnerId);
        for(String orderId : l)
        {
            ans.add(orderId);
        }
        return ans;
    }
    public List<String> getAllOrders()
    {
        List<String> ans = new ArrayList<>();
        for(String orderId:orderDb.keySet())
        {
            ans.add(orderId);
        }
        return ans;
    }
    public int getCountOfUnassignedOrders()
    {
        int ans =orderDb.size()-orderPartnerDb.size();
        return ans;
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime,String partnerId)
    {
//        int hour = Integer.valueOf(deliveryTime.substring(0,2));
//        int min =Integer.valueOf(deliveryTime.substring(3));
//        int maxTime= hour*60 + min;
        int maxTime = getDeliveryTimeInt(deliveryTime);
        HashSet<String> list = partnerOrderListDb.get(partnerId);
        int count = 0;
        for(String s:list)
        {
            int time = getOrderById(s).getDeliveryTime();
            if(time > maxTime)
            {
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        HashSet<String> list = partnerOrderListDb.get(partnerId);
        int maxTime = Integer.MIN_VALUE;
        for(String s:list)
        {
            int time = getOrderById(s).getDeliveryTime();
            maxTime = Math.max(time,maxTime);
        }

        String hh = Integer.toString(maxTime / 60);
        String mm =Integer.toString(maxTime % 60);
        if (hh.length()==1)
            hh='0'+hh;
        else if (mm.length() == 1) {
            mm='0'+mm;
        }
        String ans = hh+ ':' + mm;
        return ans;
    }

    public void deletePartnerById(String partnerId) {
//        partnerDb.remove(partnerId);
        partnerOrderListDb.remove(partnerId);
        for(String orderId:orderPartnerDb.keySet())
        {
            if(orderPartnerDb.get(orderId)==partnerId)
            {
                orderPartnerDb.remove(orderId);
            }
        }
    }

    public void deleteOrderById(String orderId) {
//        orderDb.remove(orderId);
        for(String order : orderPartnerDb.keySet())
        {
            if(order==orderId)
            {
                orderPartnerDb.remove(orderId);
            }
        }
        for(HashSet<String> partnerSet:partnerOrderListDb.values())
        {
            if(partnerSet.contains(orderId))
            {
                partnerSet.remove(orderId);
            }
        }
    }
}
