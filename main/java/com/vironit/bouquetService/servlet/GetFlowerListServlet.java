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

@WebServlet(urlPatterns = {"/getFlowerList"})
public class GetFlowerListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = Cart.get(session);
        List<Flower> flowersInCart = cart.getFlowers();

        String name = String.valueOf(request.getParameter("name"));
        String color = String.valueOf(request.getParameter("color"));
        short length = Short.parseShort(request.getParameter("length"));
        Flower flower = null;
        String errorString = null;
        FlowerServiceImpl flowerService = new FlowerServiceImpl();

        if(!name.equals("") && !color.equals("") && length != 0) {
            try {
                flower = flowerService.getByNameColorLength(name, color, length);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            response.sendRedirect(request.getServletPath() + "/flowerRange");
            return;
        }

        if(flower != null) {
            for (Flower flowerInCart : flowersInCart)
                if (flower.getId() == flowerInCart.getId()) {
                    flower.setQuality(flower.getQuality() - flowerInCart.getQuality());
                }
        }

        request.setAttribute("flower", flower);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/getFlowerView.jsp");
        dispatcher.forward(request, response);    }
}
