/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter;

import com.github.simonsagstetter.orders.Order;
import com.github.simonsagstetter.orders.OrderListRepo;
import com.github.simonsagstetter.orders.OrderMapRepo;
import com.github.simonsagstetter.orders.OrderRepo;
import com.github.simonsagstetter.products.Product;
import com.github.simonsagstetter.products.ProductRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ShopServiceTest {

    public static ShopService shopService;
    public static ShopService shopServiceWithMap;
    public final static List<Product> products = new ArrayList<>();
    public final static List<String> productIds = new ArrayList<>();
    public static Method productExists;
    public static Method getNewOrderId;
    public static Method resetOrderSequence;

    @BeforeAll
    @DisplayName("Setup -> create products for productRepo")
    static void setup(){
        products.addAll(List.of(
                new Product("4260524580051", "Ettes Aufstrich - Curry Ananas - vegan", new BigDecimal("1.59")),
                new Product("8076809572569", "\tgrünes Pesto Basilico vegan", new BigDecimal("3.75"))
        ));

        for(Product p : products){
            productIds.add(p.id());
        }

        ProductRepo productRepo = new ProductRepo(products);
        OrderRepo orderRepo = new OrderListRepo();
        OrderRepo orderRepoMap = new OrderMapRepo();

        shopService = new ShopService(productRepo, orderRepo);
        shopServiceWithMap = new ShopService(productRepo, orderRepoMap);
    }

    @Nested
    @DisplayName("ShopService test with OrderListRepo for OrderRepo")
    class ShopServiceOrderListRepoTest {

        @Nested
        @DisplayName("placeOrder test methods")
        class PlaceOrderTests {

            @Test
            @DisplayName("placeOrder -> should return null -> when called with invalid list of product ids")
            void placeOrder_ShouldReturnNull_WhenCalledWithInvalidListOfProductIds(){
                Order actual = shopService.placeOrder(
                        List.of(
                                "test-id",
                                productIds.getFirst()
                        )
                );

                assertNull(actual);
            }

            @Test
            @DisplayName("placeOrder -> should return order object -> when called with valid list of product ids")
            void placeOrder_ShouldReturnOrderObject_WhenCalledWithValidListOfProductIds(){
                Order actual = shopService.placeOrder(
                        productIds
                );

                assertNotNull(actual);
                assertThat(actual.productIds()).containsAll(
                        productIds
                );
            }

            @Test
            @DisplayName("placeOrder -> should return null -> when called with empty list")
            void placeOrder_ShouldReturnNull_WhenCalledWithEmptyList(){
                Order actual = shopService.placeOrder(List.of());

                assertNull(actual);
            }
        }

        @Nested
        @DisplayName("productExists test methods")
        class ProductExistsTests {

            @BeforeAll
            @DisplayName("Modifying the access modifier of productExists")
            static void modifyMethodAccess(){
                try {
                    productExists = ShopService.class.getDeclaredMethod("productExists", String.class);
                    productExists.setAccessible(true);

                } catch (NoSuchMethodException | InaccessibleObjectException error){
                    throw new RuntimeException(error);
                }
            }

            @Test
            @DisplayName("productExists -> should return true -> when called with existing product")
            void productExists_ShouldReturnTrue_WhenCalledWithExistingProduct(){
                try {
                    boolean actual = (boolean) productExists.invoke(shopService, productIds.getFirst());

                    assertTrue(actual);

                } catch (IllegalAccessException | InvocationTargetException error) {
                    throw new RuntimeException(error);
                }
            }

            @Test
            @DisplayName("productExists -> should return false -> when called with not existing product")
            void productExists_ShouldReturnFalse_WhenCalledWithNotExistingProduct(){
                try {
                    boolean actual = (boolean) productExists.invoke(shopService, "test-id");

                    assertFalse(actual);

                } catch (IllegalAccessException | InvocationTargetException error) {
                    throw new RuntimeException(error);
                }
            }

        }

        @Nested
        @DisplayName("getNewOrderId test methods")
        class GetNewOrderIdTests {

            @BeforeAll
            @DisplayName("Modifying the access modifier of getNewOrderId")
            static void modifyMethodAccess(){
                try {
                    getNewOrderId = ShopService.class.getDeclaredMethod("getNewOrderId");
                    getNewOrderId.setAccessible(true);
                    resetOrderSequence = ShopService.class.getDeclaredMethod("resetOrderSequence");
                    resetOrderSequence.setAccessible(true);

                } catch (NoSuchMethodException | InaccessibleObjectException error){
                    throw new RuntimeException(error);
                }
            }

            @Test
            @DisplayName("productExists -> should return true -> when called with existing product")
            void productExists_ShouldReturnTrue_WhenCalledWithExistingProduct(){
                try {
                    resetOrderSequence.invoke(shopService);

                    String expected = "O001";
                    String actual = (String) getNewOrderId.invoke(shopService);

                    assertEquals(expected, actual);

                } catch (IllegalAccessException | InvocationTargetException error) {
                    throw new RuntimeException(error);
                }
            }

        }

    }

    @Nested
    @DisplayName("ShopService test with OrderMapRepo for OrderRepo")
    class ShopServiceOrderMapRepoTest {

        @Nested
        @DisplayName("placeOrder test methods")
        class PlaceOrderTests {

            @Test
            @DisplayName("placeOrder -> should return null -> when called with invalid list of product ids")
            void placeOrder_ShouldReturnNull_WhenCalledWithInvalidListOfProductIds(){
                Order actual = shopServiceWithMap.placeOrder(
                        List.of(
                                "test-id",
                                productIds.getFirst()
                        )
                );

                assertNull(actual);
            }

            @Test
            @DisplayName("placeOrder -> should return order object -> when called with valid list of product ids")
            void placeOrder_ShouldReturnOrderObject_WhenCalledWithValidListOfProductIds(){
                Order actual = shopServiceWithMap.placeOrder(
                        productIds
                );

                assertNotNull(actual);
                assertThat(actual.productIds()).containsAll(
                        productIds
                );
            }

            @Test
            @DisplayName("placeOrder -> should return null -> when called with empty list")
            void placeOrder_ShouldReturnNull_WhenCalledWithEmptyList(){
                Order actual = shopServiceWithMap.placeOrder(List.of());

                assertNull(actual);
            }
        }

        @Nested
        @DisplayName("productExists test methods")
        class ProductExistsTests {

            @BeforeAll
            @DisplayName("Modifying the access modifier of productExists")
            static void modifyMethodAccess(){
                try {
                    productExists = ShopService.class.getDeclaredMethod("productExists", String.class);
                    productExists.setAccessible(true);

                } catch (NoSuchMethodException | InaccessibleObjectException error){
                    throw new RuntimeException(error);
                }
            }

            @Test
            @DisplayName("productExists -> should return true -> when called with existing product")
            void productExists_ShouldReturnTrue_WhenCalledWithExistingProduct(){
                try {
                    boolean actual = (boolean) productExists.invoke(shopServiceWithMap, productIds.getFirst());

                    assertTrue(actual);

                } catch (IllegalAccessException | InvocationTargetException error) {
                    throw new RuntimeException(error);
                }
            }

            @Test
            @DisplayName("productExists -> should return false -> when called with not existing product")
            void productExists_ShouldReturnFalse_WhenCalledWithNotExistingProduct(){
                try {
                    boolean actual = (boolean) productExists.invoke(shopServiceWithMap, "test-id");

                    assertFalse(actual);

                } catch (IllegalAccessException | InvocationTargetException error) {
                    throw new RuntimeException(error);
                }
            }

        }

        @Nested
        @DisplayName("getNewOrderId test methods")
        class GetNewOrderIdTests {

            @BeforeAll
            @DisplayName("Modifying the access modifier of getNewOrderId")
            static void modifyMethodAccess(){
                try {
                    getNewOrderId = ShopService.class.getDeclaredMethod("getNewOrderId");
                    getNewOrderId.setAccessible(true);
                    resetOrderSequence = ShopService.class.getDeclaredMethod("resetOrderSequence");
                    resetOrderSequence.setAccessible(true);

                } catch (NoSuchMethodException | InaccessibleObjectException error){
                    throw new RuntimeException(error);
                }
            }

            @Test
            @DisplayName("productExists -> should return true -> when called with existing product")
            void productExists_ShouldReturnTrue_WhenCalledWithExistingProduct(){
                try {
                    resetOrderSequence.invoke(shopServiceWithMap);

                    String expected = "O001";
                    String actual = (String) getNewOrderId.invoke(shopServiceWithMap);

                    assertEquals(expected, actual);

                } catch (IllegalAccessException | InvocationTargetException error) {
                    throw new RuntimeException(error);
                }
            }

        }
    }
}