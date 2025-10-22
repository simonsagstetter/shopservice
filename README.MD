# Shop Service
[![CI](https://github.com/simonsagstetter/shopservice/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/simonsagstetter/shopservice/actions/workflows/ci.yml)
![Version](https://img.shields.io/badge/version-1.0.0-blue)

> You are looking at the docs of the **core** version **1.x.x**. This version does not include any **bonus** features.


ShopService is a Java library for basic shop management. It allows you to create a product repository, manage orders, and place new orders by passing product IDs from your product repository. The order repository can be implemented using either a list or a map by passing an OrderListRepo or OrderMapRepo instance to the ShopService constructor.


## Overview
![core-schema.png](.github/resources/core-schema.png)

## Setup
*Tested with JDK Version 21 - Amazon Corretto*

- compile: `mvn clean compile`
- test: `mvn clean verify`
- build: `mvn clean package `

#### Manual Compilation

- compile: `javac src/main/java/com/github/simonsagstetter/** -d out`
- execute: `java -cp out com.github.simonsagstetter.Main`

## Usage

### Shop Service

```java
package com.github.simonsagstetter;

import com.github.simonsagstetter.orders.Order;
import com.github.simonsagstetter.orders.OrderListRepo;
import com.github.simonsagstetter.orders.OrderMapRepo;
import com.github.simonsagstetter.orders.OrderRepo;
import com.github.simonsagstetter.products.Product;
import com.github.simonsagstetter.products.ProductRepo;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Define a product repository and add products
        ProductRepo productRepo = new ProductRepo();
        List<Product> products = List.of(
                new Product("P001", "Shop Service Core Version", new BigDecimal("19.99")),
                new Product("P002", "Shop Service Extended Version", new BigDecimal("49.99"))
        );

        productRepo.addProduct(products);

        // Define an order repository by either creating a new instance of OrderListRepo or OrderMapRepo
        // OrderRepo orderRepo = new OrderListRepo();
        OrderRepo orderRepo = new OrderMapRepo();

        // Create the ShopService
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // Place an order
        List<String> productIds = List.of("P001");
        Order order = shopService.placeOrder(productIds);

    }
}
```

### Product Management

```java
package com.github.simonsagstetter;

import com.github.simonsagstetter.products.Product;
import com.github.simonsagstetter.products.ProductRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        
        // Define products
        List<Product> products = List.of(
                new Product("P001", "Shop Service Core Version", new BigDecimal("19.99")),
                new Product("P002", "Shop Service Extended Version", new BigDecimal("49.99"))
        );
        Product singleProduct = new Product("P003", "Shop Service Support 1 Year", new BigDecimal("34.99"));
        
        // Define product repository
        ProductRepo productRepo = new ProductRepo();
        // ProductRepo productRepo = new ProductRepo(singleProduct);
        // ProductRepo productRepo = new ProductRepo(products);
        
        // Add multiple or single products
        productRepo.addProduct(products);
        productRepo.addProduct(singleProduct);
        
        // Retrieve single or multiple products
        Product p001 = productRepo.getProduct("P001");
        List<Product> allProducts = productRepo.getAllProducts();
        
        // Remove single or multiple products
        Set<String> productIds = Set.of("P001", "P002");
        productRepo.removeProduct("P003");
        productRepo.removeProducts(productIds);
        
    }
}

```

### Order Management

```java
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
    public static void main(String[] args) {

        // Define products and product repository
        List<Product> products = List.of(
                new Product("P001", "Shop Service Core Version", new BigDecimal("19.99")),
                new Product("P002", "Shop Service Extended Version", new BigDecimal("49.99")),
                new Product("P003", "Shop Service Support 1 Year", new BigDecimal("34.99"))
        );
        ProductRepo productRepo = new ProductRepo(products);

        // Define an OrderMapRepo or OrderListRepo as OrderRepo
        // It also possible to pass existing Order or List<Order> into constructor
        OrderRepo orderRepo = new OrderMapRepo();

        // Define ShopServce
        ShopService shopService = new ShopService(productRepo, orderRepo);

        Order o1 = shopService.placeOrder(List.of("P001", "P002"));
        
        // Not recommended to use orderRepo.addOrder() because its managed by ShopService
        // orderRepo.addOrder(Order or List<Order>);
        
        // Retrieve single or multiple orders
        Order o2 = orderRepo.getOrder(o1.id());
        List<Order> orders = orderRepo.getAllOrders();
        
        // Remove single or multiple orders
        Set<String> orderIds = Set.of("P001", "P003");
        orderRepo.removeOrders(orderIds);
        orderRepo.removeOrder("P002");
        
    }
}

```