package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.model.enums.OrderStatus;
import com.vironit.bouquetService.service.OrderServiceImpl;
import com.vironit.bouquetService.service.UserServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/createOrder" })
public class CreateOrderServlet extends HttpServlet {

    public CreateOrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createOrderView.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = null;
        String errorString = null;

        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String status = request.getParameter("status");
        UserServiceImpl userService = new UserServiceImpl();

        if(request.getParameter("userId").equals("")) {
            errorString = "Error";
        }
        else {
            try {
                if(userService.getById(Long.parseLong(request.getParameter("userId"))) == null){
                    user = null;
                    errorString = "Error";
                }
                else user = userService.getById(Long.parseLong(request.getParameter("userId")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/errorView.jsp");
            dispatcher.forward(request, response);
        }


        Order order = new Order();
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setStatus(OrderStatus.valueOf(status));
        order.setUser(user);

        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            orderService.add(order);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("order", order);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createOrderView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/orderList");
        }
    }

}