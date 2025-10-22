/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderListRepo implements OrderRepo {

    private final List<Order> orders = new ArrayList<>();

    public OrderListRepo(){}

    public OrderListRepo(Order order){
        this.addOrder(order);
    }

    public OrderListRepo(List<Order> orders){
        this.addOrder(orders);
    }

    @Override
    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public void addOrder(List<Order> orders){
        this.orders.addAll(orders);
    }

    @Override
    public List<Order> getAllOrders(){
        return this.orders;
    }

    @Override
    public Order getOrder(String id){
        for(Order order: this.getAllOrders()){
            if(order.id().equals(id))return order;
        }
        return null;
    }

    @Override
    public void removeOrder(String id){
        Order order = this.getOrder(id);
        if(order != null){
            this.orders.remove(order);
        }
    }

    @Override
    public void removeOrders(Set<String> orderIds){
        for(String id : orderIds){
            this.removeOrder(id);
        }
    }

}
