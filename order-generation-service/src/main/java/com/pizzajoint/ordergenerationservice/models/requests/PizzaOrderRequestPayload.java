package com.pizzajoint.ordergenerationservice.models.requests;

import javax.validation.constraints.NotNull;

public class PizzaOrderRequestPayload {

    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @NotNull(message = "Price is required.")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
