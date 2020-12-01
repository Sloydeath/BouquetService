package com.vironit.bouquetService.model;

import com.vironit.bouquetService.model.enums.BouquetType;

import javax.persistence.*;
import java.util.List;

public class Bouquet {

    private long id;
    private BouquetType type;
    private Order order;
    private List<FlowerInBouquet> flowerInBouquets;

    public Bouquet(BouquetType type) {
        this.type = type;
    }

    public Bouquet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BouquetType getType() {
        return type;
    }

    public void setType(BouquetType type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<FlowerInBouquet> getFlowerInBouquets() {
        return flowerInBouquets;
    }

    public void setFlowerInBouquets(List<FlowerInBouquet> flowerInBouquets) {
        this.flowerInBouquets = flowerInBouquets;
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
