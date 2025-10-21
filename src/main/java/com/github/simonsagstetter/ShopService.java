/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter;

import com.github.simonsagstetter.orders.Order;
import com.github.simonsagstetter.orders.OrderListRepo;
import com.github.simonsagstetter.products.ProductRepo;

import java.util.List;

public class ShopService {

    private static int orderSequence = 1;
    private final ProductRepo productRepo;
    private final OrderListRepo orderListRepo;

    private static String getNewOrderId(){
        String number = String.format("%03d", orderSequence++);
        return "O" + number;
    }

    private static void resetOrderSequence(){
        ShopService.orderSequence = 1;
    }

    public ShopService(ProductRepo productRepo, OrderListRepo orderListRepo) {
        this.productRepo = productRepo;
        this.orderListRepo = orderListRepo;
    }

    public Order placeOrder(List<String> productIds){
        if(productIds.isEmpty())return null;

        for(String productId : productIds){
            if(!this.productExists(productId))return null;
        }
        Order order = new Order(ShopService.getNewOrderId(),productIds);
        this.orderListRepo.addOrder(order);

        return order;
    }

    private boolean productExists(String productId){
        return this.productRepo.getProduct(productId) != null;
    }


}
