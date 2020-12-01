package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.model.Flower;
import com.vironit.bouquetService.model.enums.FlowerColor;
import com.vironit.bouquetService.model.enums.FlowerName;
import com.vironit.bouquetService.service.FlowerServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/createFlower" })

public class CreateFlowerServlet extends HttpServlet {

    public CreateFlowerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createFlowerView.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Bouquet bouquet = null;
        String errorString = null;

        String name = request.getParameter("name");
        short length = Short.parseShort(request.getParameter("length"));
        String color = request.getParameter("color");
        float price = Float.parseFloat(request.getParameter("price"));
        int quality = Integer.parseInt(request.getParameter("quality"));

        Flower flower = new Flower();
        flower.setName(FlowerName.valueOf(name));
        flower.setLength(length);
        flower.setColor(FlowerColor.valueOf(color));
        flower.setPrice(price);
        flower.setQuality(quality);

        try {
            FlowerServiceImpl flowerService = new FlowerServiceImpl();
            if(!flowerService.isExistByNameColorLength(name, color, length))
                flowerService.add(flower);
            else {
                errorString = "Цветок с такими именем, цветом и длиной уже существует!";
                request.setAttribute("errorString", errorString);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/errorView.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("flower", flower);

        response.sendRedirect(request.getContextPath() + "/flowerList");

    }

}
