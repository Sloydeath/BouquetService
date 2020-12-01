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


@WebServlet(urlPatterns = { "/editOrder" })
public class UpdateOrderServlet extends HttpServlet {


    public UpdateOrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Order order = null;
        String errorString = null;

        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            order = orderService.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/orderList");
            return;
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("order", order);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/updateOrderView.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = null;
        String errorString = null;

        long id = Long.parseLong(request.getParameter("id"));
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
        order.setId(id);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setStatus(OrderStatus.valueOf(status));
        order.setUser(user);

        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            orderService.update(order);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("order", order);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/updateOrderView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/orderList");
        }
    }

}
