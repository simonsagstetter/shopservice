/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.products;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    public static Product product;
    public static final List<Product> products = new ArrayList<>();

    @BeforeAll
    @DisplayName("Create a product records")
    static void setup(){
        ProductRepoTest.product = new Product("4005500087151","Smartphone Casing", new BigDecimal("1.99"));

        for(int i=0; i < 10; i++){
            products.add(new Product("400550008715" + i,"Product " + i, new BigDecimal(i)));
        }
    }

    @Nested
    @DisplayName("ProductRepo constructor test methods")
    class ProductRepoConstructorTests {

        @Test
        @DisplayName("ProductRepo -> should return empty product list -> when called without arguments")
        void productRepo_ShouldReturnEmptyProductList_WhenCalledWithoutArguments(){
            List<Product> expected = new ArrayList<>();

            ProductRepo productRepo = new ProductRepo();

            List<Product> actual = productRepo.getProducts();

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("ProductRepo -> should return one product in product list -> when called with one product")
        void productRepo_ShouldReturnProductListWithOneProduct_WhenCalledWithOneProduct(){
            List<Product> expected = new ArrayList<>();
            expected.add(product);

            ProductRepo productRepo = new ProductRepo(product);

            List<Product> actual = productRepo.getProducts();

            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("ProductRepo -> should return product list -> when called with products")
        void productRepo_ShouldReturnProductList_WhenCalledWithProducts(){
            ProductRepo productRepo = new ProductRepo(products);

            List<Product> actual = productRepo.getProducts();

            assertEquals(products, actual);
        }

    }

    @Nested
    @DisplayName("addProduct test methods")
    class addProductTests {

        @Test
        @DisplayName("addProduct -> should add single product to products list -> when called with single product")
        void addProduct_ShouldAddSingleProductToProductList_WhenCalledWithSingleProduct(){
            ProductRepo productRepo = new ProductRepo();

            List<Product> expected = List.of(product);

            productRepo.addProduct(product);

            assertEquals(expected, productRepo.getProducts());
        }

        @Test
        @DisplayName("addProduct -> should add several product to products list -> when called with product list")
        void addProduct_ShouldAddSeveralProductToProductList_WhenCalledWithProductList(){
            ProductRepo productRepo = new ProductRepo();

            productRepo.addProduct(products);

            assertEquals(products, productRepo.getProducts());
        }

    }

    @Nested
    @DisplayName("getProduct/getProducts test methods")
    class getProductTests {

        @Test
        @DisplayName("getProduct -> should return a single product -> when called with a valid id")
        void getProduct_ShouldReturnASingleProduct_WhenCalledWitAValidId(){
            ProductRepo productRepo = new ProductRepo(product);
            String id = product.id();

            Product p = productRepo.getProduct(id);

            assertEquals(p, product);
        }

        @Test
        @DisplayName("getProduct -> should return null -> when called with an invalid id")
        void getProduct_ShouldReturnNull_WhenCalledWitAnInvalidId(){
            ProductRepo productRepo = new ProductRepo(product);
            String id = "DUMMYID";

            Product p = productRepo.getProduct(id);

            assertNull(p);
        }

        @Test
        @DisplayName("getProducts -> should return a list of product -> when products were added before")
        void getProducts_ShouldReturnListOfProducts_WhenProductsWereAddedBefore(){
            ProductRepo productRepo = new ProductRepo(products);

            assertFalse(productRepo.getProducts().isEmpty());
            assertEquals(products, productRepo.getProducts());
        }

    }

    @Nested
    @DisplayName("removeProduct test methods")
    class removeProductTests {

        @Test
        @DisplayName("removeProduct -> should remove product from product list -> when called with a valid id")
        void removeProduct_ShouldRemoveProductFromProductList_WhenCalledWitAValidId(){
            ProductRepo productRepo = new ProductRepo(product);
            productRepo.removeProduct(product.id());

            Product p = productRepo.getProduct(product.id());
            assertTrue(productRepo.getProducts().isEmpty());
            assertNull(p);
        }

        @Test
        @DisplayName("removeProduct -> should stay same list of products -> when called with an invalid id")
        void removeProduct_ShouldStaySameListOfProducts_WhenCalledWitAnInvalidId(){
            ProductRepo productRepo = new ProductRepo(products);
            String id = "DUMMYID";

            productRepo.removeProduct(id);

            assertEquals(products, productRepo.getProducts());
        }

    }
}