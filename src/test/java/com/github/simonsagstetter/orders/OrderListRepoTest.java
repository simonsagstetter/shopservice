/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import com.github.simonsagstetter.products.Product;
import com.github.simonsagstetter.products.ProductRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    public static Order order;
    public static final List<Order> orders = new ArrayList<>();

    @BeforeAll
    @DisplayName("Create order records")
    static void setup(){
        OrderListRepoTest.order = new Order("4005500087151",List.of("321323212321", "31231245234"));

        for(int i=0; i < 10; i++){
            orders.add(new Order("400550008715" + i,List.of("12312321" + i, "986934589" + i)));
        }
    }

    @Nested
    @DisplayName("OrderListRepo constructor test methods")
    class OrderListRepoConstructorTests {

        @Test
        @DisplayName("OrderListRepo -> should return empty order list -> when called without arguments")
        void OrderListRepo_ShouldReturnEmptyOrderList_WhenCalledWithoutArguments(){
            List<Order> expected = new ArrayList<>();

            OrderListRepo orderListRepo = new OrderListRepo();

            List<Order> actual = orderListRepo.getAllOrders();

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("OrderListRepo -> should return single order in orders list -> when called with single product")
        void OrderListRepo_ShouldReturnOrderListWithSingleOrder_WhenCalledWithSingleOrder(){
            List<Order> expected = new ArrayList<>();
            expected.add(order);

            OrderListRepo orderListRepo = new OrderListRepo(order);

            List<Order> actual = orderListRepo.getAllOrders();

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("OrderListRepo -> should return order list -> when called with orders")
        void OrderListRepo_ShouldReturnOrderList_WhenCalledWithOrders(){
            OrderListRepo orderListRepo = new OrderListRepo(orders);

            List<Order> actual = orderListRepo.getAllOrders();

            assertEquals(orders, actual);
        }

    }

    @Nested
    @DisplayName("addOrder test methods")
    class addOrderTest {

        @Test
        @DisplayName("addOrder -> should add single order to orders list -> when called with single order")
        void addOrder_ShouldAddSingleOrderToOrderList_WhenCalledWithSingleOrder(){
            OrderListRepo orderListRepo = new OrderListRepo();

            List<Order> expected = List.of(order);

            orderListRepo.addOrder(order);

            assertEquals(expected, orderListRepo.getAllOrders());
        }

        @Test
        @DisplayName("addOrder -> should add several order to orders list -> when called with order list")
        void addOrder_ShouldAddSeveralOrderToOrderList_WhenCalledWithOrderList(){
            OrderListRepo orderListRepo = new OrderListRepo();

            orderListRepo.addOrder(orders);

            assertEquals(orders, orderListRepo.getAllOrders());
        }

    }

    @Nested
    @DisplayName("getOrder/getAllOrders test methods")
    class getOrderTest {

        @Test
        @DisplayName("getOrder -> should return a single order -> when called with a valid id")
        void getOrder_ShouldReturnASingleOrder_WhenCalledWitAValidId(){
            OrderListRepo orderListRep = new OrderListRepo(order);
            String id = order.id();

            Order p = orderListRep.getOrder(id);

            assertEquals(p, order);
        }

        @Test
        @DisplayName("getOrder -> should return null -> when called with an invalid id")
        void getOrder_ShouldReturnNull_WhenCalledWitAnInvalidId(){
            OrderListRepo orderListRep = new OrderListRepo(order);
            String id = "DUMMYID";

            Order p = orderListRep.getOrder(id);

            assertNull(p);
        }

        @Test
        @DisplayName("getAllOrders -> should return a list of order -> when orders were added before")
        void getAllOrders_ShouldReturnListOfOrders_WhenOrdersWereAddedBefore(){
            OrderListRepo orderListRep = new OrderListRepo(orders);

            assertFalse(orderListRep.getAllOrders().isEmpty());
            assertEquals(orders, orderListRep.getAllOrders());
        }

    }

    @Nested
    @DisplayName("removeOrder test methods")
    class removeOrderTests {

        @Test
        @DisplayName("removeOrder -> should remove order from order list -> when called with a valid id")
        void removeOrder_ShouldRemoveOrderFromOrderList_WhenCalledWitAValidId(){
            OrderListRepo orderListRepo = new OrderListRepo(order);
            orderListRepo.removeOrder(order.id());

            Order p = orderListRepo.getOrder(order.id());
            assertTrue(orderListRepo.getAllOrders().isEmpty());
            assertNull(p);
        }

        @Test
        @DisplayName("removeOrder -> should stay same list of orders -> when called with an invalid id")
        void removeOrder_ShouldStaySameListOfOrders_WhenCalledWitAnInvalidId(){
            OrderListRepo orderListRepo = new OrderListRepo(orders);
            String id = "DUMMYID";

            orderListRepo.removeOrder(id);

            assertEquals(orders, orderListRepo.getAllOrders());
        }

        @Test
        @DisplayName("removeOrders -> should stay same list of orders -> when called with invalid ids")
        void removeOrders_ShouldStaySameListOfOrders_WhenCalledWithInvalidIds(){
            OrderListRepo orderListRepo = new OrderListRepo(orders);
            List<String> orderIds = List.of("Dummy1Id", "Dummy2Id");

            orderListRepo.removeOrders(orderIds);

            assertEquals(orders, orderListRepo.getAllOrders());
        }

        @Test
        @DisplayName("removeOrders -> should remove orders from orders list -> when called with valid ids")
        void removeOrders_ShouldRemoveOrdersFromOrdersList_WhenCalledWithValidIds(){
            OrderListRepo orderListRepo = new OrderListRepo(order);
            List<String> orderIds = List.of(order.id());

            orderListRepo.removeOrders(orderIds);

            assertTrue(orderListRepo.getAllOrders().isEmpty());
        }

    }

}