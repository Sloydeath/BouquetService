package com.vironit.bouquetService.model;

import javax.persistence.*;

public class FlowerInBouquet {

    private long id;
    private int quality;
    private Bouquet bouquet;
    private Flower flower;

    public FlowerInBouquet() {
    }

    public FlowerInBouquet(int quality, Bouquet bouquet, Flower flower) {
        this.quality = quality;
        this.bouquet = bouquet;
        this.flower = flower;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }


    @Override
    public String toString() {
        return "FlowerInBouquet{" +
                "id=" + id +
                ", quality=" + quality +
                '}';
    }
}

