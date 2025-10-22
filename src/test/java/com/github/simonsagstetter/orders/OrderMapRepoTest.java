/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    public static Order order;
    public static final HashMap<String, Order> orders = new HashMap<>();
    public static List<Order> ordersList;

    @BeforeAll
    @DisplayName("Create order records")
    static void setup(){
        order = new Order("4005500087151",List.of("321323212321", "31231245234"));

        for(int i=0; i < 10; i++){
            orders.put("400550008715" + i ,new Order("400550008715" + i,List.of("12312321" + i, "986934589" + i)));
        }
        ordersList = new ArrayList<>(orders.values());
    }

    @Nested
    @DisplayName("OrderMapRepo constructor test methods")
    class OrderMapRepoConstructorTests {

        @Test
        @DisplayName("OrderMapRepo -> should return empty order list -> when called without arguments")
        void OrderListRepo_ShouldReturnEmptyOrderList_WhenCalledWithoutArguments(){
            List<Order> expected = new ArrayList<>();

            OrderMapRepo orderMapRepo = new OrderMapRepo();

            List<Order> actual = orderMapRepo.getAllOrders();

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("OrderMapRepo -> should return single order in orders list -> when called with single product")
        void OrderMapRepo_ShouldReturnOrderListWithSingleOrder_WhenCalledWithSingleOrder(){
            List<Order> expected = new ArrayList<>();
            expected.add(order);

            OrderMapRepo orderMapRepo = new OrderMapRepo(order);

            List<Order> actual = orderMapRepo.getAllOrders();

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("OrderMapRepo -> should return order list -> when called with orders")
        void OrderMapRepo_ShouldReturnOrderList_WhenCalledWithOrders(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

            List<Order> actual = orderMapRepo.getAllOrders();

            assertEquals(ordersList, actual);
        }

    }

    @Nested
    @DisplayName("addOrder test methods")
    class addOrderTest {

        @Test
        @DisplayName("addOrder -> should add single order to orders list -> when called with single order")
        void addOrder_ShouldAddSingleOrderToOrderList_WhenCalledWithSingleOrder(){
            OrderMapRepo orderMapRepo = new OrderMapRepo();

            List<Order> expected = List.of(order);

            orderMapRepo.addOrder(order);

            assertEquals(expected, orderMapRepo.getAllOrders());
        }

        @Test
        @DisplayName("addOrder -> should add several order to orders list -> when called with order list")
        void addOrder_ShouldAddSeveralOrderToOrderList_WhenCalledWithOrderList(){
            OrderMapRepo orderMapRepo = new OrderMapRepo();

            orderMapRepo.addOrder(orders);

            assertEquals(ordersList, orderMapRepo.getAllOrders());
        }

    }

    @Nested
    @DisplayName("getOrder/getAllOrders test methods")
    class getOrderTest {

        @Test
        @DisplayName("getOrder -> should return a single order -> when called with a valid id")
        void getOrder_ShouldReturnASingleOrder_WhenCalledWitAValidId(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(order);
            String id = order.id();

            Order p = orderMapRepo.getOrder(id);

            assertEquals(p, order);
        }

        @Test
        @DisplayName("getOrder -> should return null -> when called with an invalid id")
        void getOrder_ShouldReturnNull_WhenCalledWitAnInvalidId(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(order);
            String id = "DUMMYID";

            Order p = orderMapRepo.getOrder(id);

            assertNull(p);
        }

        @Test
        @DisplayName("getAllOrders -> should return a list of order -> when orders were added before")
        void getAllOrders_ShouldReturnListOfOrders_WhenOrdersWereAddedBefore(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

            assertFalse(orderMapRepo.getAllOrders().isEmpty());
            assertEquals(ordersList, orderMapRepo.getAllOrders());
        }

    }

    @Nested
    @DisplayName("removeOrder test methods")
    class removeOrderTests {

        @Test
        @DisplayName("removeOrder -> should remove order from order list -> when called with a valid id")
        void removeOrder_ShouldRemoveOrderFromOrderList_WhenCalledWitAValidId(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(order);
            orderMapRepo.removeOrder(order.id());

            Order p = orderMapRepo.getOrder(order.id());
            assertTrue(orderMapRepo.getAllOrders().isEmpty());
            assertNull(p);
        }

        @Test
        @DisplayName("removeOrder -> should stay same list of orders -> when called with an invalid id")
        void removeOrder_ShouldStaySameListOfOrders_WhenCalledWitAnInvalidId(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(orders);
            String id = "DUMMYID";

            orderMapRepo.removeOrder(id);

            assertEquals(ordersList, orderMapRepo.getAllOrders());
        }

        @Test
        @DisplayName("removeOrders -> should stay same list of orders -> when called with invalid ids")
        void removeOrders_ShouldStaySameListOfOrders_WhenCalledWithInvalidIds(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(orders);
            Set<String> orderIds = Set.of("Dummy1Id", "Dummy2Id");

            orderMapRepo.removeOrders(orderIds);

            assertEquals(ordersList, orderMapRepo.getAllOrders());
        }

        @Test
        @DisplayName("removeOrders -> should remove orders from orders list -> when called with valid ids")
        void removeOrders_ShouldRemoveOrdersFromOrdersList_WhenCalledWithValidIds(){
            OrderMapRepo orderMapRepo = new OrderMapRepo(order);
            Set<String> orderIds = Set.of(order.id());

            orderMapRepo.removeOrders(orderIds);

            assertTrue(orderMapRepo.getAllOrders().isEmpty());
        }

    }

}