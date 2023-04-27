package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order)
    {
        orderRepository.addOrder(order);
    }
    public void addPartner(String deliveryPartner)
    {
        DeliveryPartner partner = new DeliveryPartner(deliveryPartner);
        orderRepository.addPartner(partner);
    }
    public void addPartnerOrderPair(String orderId,String partnerId)
    {
        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }
    public Order getOrderById(String orderId)
    {

        return orderRepository.getOrderById(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId)
    {
        return orderRepository.getPartnerById(partnerId);
    }
    public Integer getOrderCountByPartnerId(String partnerId)
    {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }
    public List<String> getAllOrders()
    {
        List<String> ans =orderRepository.getAllOrders();
        return ans;
    }
    public int getCountOfUnassignedOrders()
    {
        int ans=orderRepository.getCountOfUnassignedOrders();
        return ans;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        Integer ans = orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
        return ans;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}
