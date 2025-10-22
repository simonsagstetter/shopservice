/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo {

    private final List<Order> orders = new ArrayList<>();

    public OrderListRepo(){}

    public OrderListRepo(Order order){
        this.addOrder(order);
    }

    public OrderListRepo(List<Order> orders){
        this.addOrder(orders);
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void addOrder(List<Order> orders){
        this.orders.addAll(orders);
    }

    public List<Order> getAllOrders(){
        return this.orders;
    }

    public Order getOrder(String id){
        for(Order order: this.getAllOrders()){
            if(order.id().equals(id))return order;
        }
        return null;
    }

    public void removeOrder(String id){
        Order order = this.getOrder(id);
        if(order != null){
            this.orders.remove(order);
        }
    }

    public void removeOrders(List<String> orderIds){
        for(String id : orderIds){
            removeOrder(id);
        }
    }

}
