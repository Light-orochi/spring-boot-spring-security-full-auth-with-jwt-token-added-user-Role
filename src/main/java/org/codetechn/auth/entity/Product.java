package org.codetechn.auth.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
   @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private float price;

    public Product(){

    }
    public Product(String name, float price) {

        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
