package com.github.simonsagstetter;

import com.github.simonsagstetter.orders.Order;
import com.github.simonsagstetter.orders.OrderMapRepo;
import com.github.simonsagstetter.orders.OrderRepo;
import com.github.simonsagstetter.products.Product;
import com.github.simonsagstetter.products.ProductRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main( String[] args ) {

        // Define products and product repository
        List<Product> products = List.of(
                new Product( "P001", "Shop Service Core Version", new BigDecimal( "19.99" ) ),
                new Product( "P002", "Shop Service Extended Version", new BigDecimal( "49.99" ) ),
                new Product( "P003", "Shop Service Support 1 Year", new BigDecimal( "34.99" ) )
        );
        ProductRepo productRepo = new ProductRepo( products );

        // Define an OrderMapRepo or OrderListRepo as OrderRepo
        // It also possible to pass existing Order or List<Order> into constructor
        OrderRepo orderRepo = new OrderMapRepo();

        // Define ShopServce
        ShopService shopService = new ShopService( productRepo, orderRepo );

        Order o1 = shopService.placeOrder( List.of( "P001", "P002" ) );

        // Not recommended to use orderRepo.addOrder() because its managed by ShopService
        // orderRepo.addOrder(Order or List<Order>);

        // Retrieve single or multiple orders
        Order o2 = orderRepo.getOrder( o1.id() );
        List<Order> orders = orderRepo.getAllOrders();

        // Remove single or multiple orders
        Set<String> orderIds = Set.of( "P001", "P003" );
        orderRepo.removeOrders( orderIds );
        orderRepo.removeOrder( "P002" );

    }
}