package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.service.FlowerServiceImpl;
import com.vironit.bouquetService.util.Cart;
import com.vironit.bouquetService.model.Flower;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = {"/addFlowerToCart"})
public class AddFlowerToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = Cart.get(session);
        String errorString = null;

        List<Flower> flowersInCart = cart.getFlowers();
        int count = 0;

        int quality = 0;
        long id = Long.parseLong(request.getParameter("id"));
        if(!request.getParameter("quality").equals(""))
            quality = Integer.parseInt(request.getParameter("quality"));

        FlowerServiceImpl flowerService = new FlowerServiceImpl();
        try {
            if (errorString != null) {
                request.setAttribute("errorString", errorString);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/addFlowerToCartError.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Flower flower = flowerService.getById(id);
            if(!flowersInCart.isEmpty()) {
                for (Flower flowerInCart : flowersInCart)
                    if (flower.getId() == flowerInCart.getId()) {
                        flowerInCart.setQuality(flowerInCart.getQuality() + quality);
                        count++;
                    }
                if(count == 0) {
                    flower.setQuality(quality);
                    cart.addItem(flower);
                }
            }
            else {
                flower.setQuality(quality);
                cart.addItem(flower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/addFlowerToCartError.jsp");
            dispatcher.forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/flowerRange");
    }
}
