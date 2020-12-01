package com.vironit.bouquetService.servlet;

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

@WebServlet(urlPatterns = { "/editFlower" })
public class UpdateFlowerServlet extends HttpServlet {


    public UpdateFlowerServlet() {

        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Flower flower = null;
        String errorString = null;

        try {
            FlowerServiceImpl flowerService = new FlowerServiceImpl();
            flower = flowerService.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/flowerList");
            return;
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("flower", flower);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/updateFlowerView.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String errorString = null;

        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        short length = Short.parseShort(request.getParameter("length"));
        String color = request.getParameter("color");
        double price = Double.parseDouble(request.getParameter("price"));
        int quality = Integer.parseInt(request.getParameter("quality"));

        Flower flower = new Flower();
        flower.setId(id);
        flower.setName(FlowerName.valueOf(name));
        flower.setLength(length);
        flower.setColor(FlowerColor.valueOf(color));
        flower.setPrice(price);
        flower.setQuality(quality);

        try {
            FlowerServiceImpl flowerService = new FlowerServiceImpl();
            flowerService.update(flower);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("flower", flower);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/updateFlowerView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/flowerList");
        }
    }

}
