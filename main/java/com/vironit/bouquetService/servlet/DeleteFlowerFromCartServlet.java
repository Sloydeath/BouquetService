package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.service.FlowerServiceImpl;
import com.vironit.bouquetService.util.Cart;
import com.vironit.bouquetService.model.Flower;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/deleteFlowerFromCart"})
public class DeleteFlowerFromCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = Cart.get(session);

        List<Flower> flowers = cart.getFlowers();

        long id = Long.parseLong(request.getParameter("id"));

        FlowerServiceImpl flowerService = new FlowerServiceImpl();
        try {
            for(Flower f: flowers) {
                if ( flowerService.getById(id).getId() == f.getId() ) {
                    cart.removeItem(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
