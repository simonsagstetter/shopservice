/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class OrderMapRepo implements OrderRepo {

    private final HashMap<String, Order> orders = new HashMap<>();

    public OrderMapRepo() {
    }

    public OrderMapRepo( Order order ) {
        this.addOrder( order );
    }

    public OrderMapRepo( HashMap<String, Order> orders ) {
        this.addOrder( orders );
    }

    @Override
    public void addOrder( Order order ) {
        this.orders.put( order.id(), order );
    }

    @Override
    public void addOrder( HashMap<String, Order> orders ) {
        this.orders.putAll( orders );
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>( this.orders.values() );
    }

    @Override
    public Order getOrder( String id ) {
        return this.orders.get( id );
    }

    @Override
    public void removeOrder( String id ) {
        this.orders.remove( id );
    }

    @Override
    public void removeOrders( Set<String> ids ) {
        for ( String id : ids ) {
            this.removeOrder( id );
        }
    }
}
