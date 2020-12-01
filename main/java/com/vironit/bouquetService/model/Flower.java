package com.vironit.bouquetService.model;

import com.vironit.bouquetService.model.enums.FlowerColor;
import com.vironit.bouquetService.model.enums.FlowerName;

import javax.persistence.*;
import java.util.List;

public class Flower {

    private long id;
    private FlowerName name;
    private short length;
    private FlowerColor color;
    private double price;
    private int quality;
    private Bouquet bouquet;
    private List<FlowerInBouquet> flowerInBouquets;


    public Flower(FlowerName name, short length, FlowerColor color, double price, int quality) {
        this.name = name;
        this.length = length;
        this.color = color;
        this.price = price;
        this.quality = quality;
    }

    public Flower() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlowerName getName() {
        return name;
    }

    public void setName(FlowerName name) {
        this.name = name;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public FlowerColor getColor() {
        return color;
    }

    public void setColor(FlowerColor color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public List<FlowerInBouquet> getFlowerInBouquets() {
        return flowerInBouquets;
    }

    public void setFlowerInBouquets(List<FlowerInBouquet> flowerInBouquets) {
        this.flowerInBouquets = flowerInBouquets;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name=" + name +
                ", length=" + length +
                ", color=" + color +
                ", price=" + price +
                ", quality=" + quality +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}