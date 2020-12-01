package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.model.Flower;
import com.vironit.bouquetService.service.FlowerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet (urlPatterns = {"/flowerListMainPage"})
public class FlowerListMainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlowerServiceImpl flowerService = new FlowerServiceImpl();
        List<Flower> flowerList = null;
        try {
            flowerList = flowerService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("flowerList", flowerList);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/flowerListMainPageView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
