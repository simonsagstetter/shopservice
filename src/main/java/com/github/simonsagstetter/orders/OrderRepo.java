/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import java.util.List;

public interface OrderRepo {

    public void addOrder(Order order);

    public void addOrder(List<Order> orders);

    public List<Order> getAllOrders();

    public Order getOrder(String id);

    public void removeOrder(String id);

    public void removeOrders(List<String> ids);

}
