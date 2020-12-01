package com.vironit.bouquetService.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.service.OrderServiceImpl;

@WebServlet(urlPatterns = { "/orderList" })

public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderServiceImpl orderService = new OrderServiceImpl();
        List<Order> orderList = null;
        try {
            orderList = orderService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("orderList", orderList);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/orderListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}