/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface OrderRepo {

    void addOrder( Order order );

    default void addOrder( List<Order> orders ) {
    }

    default void addOrder( HashMap<String, Order> orders ) {
    }

    List<Order> getAllOrders();

    Order getOrder( String id );

    void removeOrder( String id );

    void removeOrders( Set<String> ids );

}
