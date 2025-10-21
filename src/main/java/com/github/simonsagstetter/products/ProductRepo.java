/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    private final List<Product> products = new ArrayList<>();

    public ProductRepo(){}

    public ProductRepo(Product product){
        this.addProduct(product);
    }

    public ProductRepo(List<Product> products){
        this.addProduct(products);
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void addProduct(List<Product> products){
        this.products.addAll(products);
    }

    public Product getProduct(String id){
        for(Product p: this.getAllProducts()){
            if(p.id().equals(id))return p;
        }
        return null;
    }

    public List<Product> getAllProducts(){
        return this.products;
    }

    public void removeProduct(String id){
        Product p = this.getProduct(id);
        if(p != null){
            this.products.remove(p);
        }
    }

    public void removeProducts(List<String> productIds){
        for(String id: productIds){
            this.removeProduct(id);
        }
    }

}

