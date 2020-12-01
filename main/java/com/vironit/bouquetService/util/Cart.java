package com.vironit.bouquetService.util;

import com.vironit.bouquetService.model.Flower;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Flower> flowers = new ArrayList<>();

    public synchronized void addItem(Flower flower) {
        flowers.add(flower);
    }

    public synchronized void removeItem(Flower flower){
        flowers.remove(flower);
    }

    public synchronized List<Flower> getFlowers() {
        if(flowers != null)
            return new ArrayList<>(flowers);
        else {
            return null;
        }
    }

    public static Cart get(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

}
