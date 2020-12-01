package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.service.OrderServiceImpl;
import com.vironit.bouquetService.service.UserServiceImpl;

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

@WebServlet(urlPatterns = {"/accountInfo"})
public class AccountInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = null;
        List<Order> orders = null;
        String email = (String) session.getAttribute("email");
        UserServiceImpl userService = new UserServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            user = userService.getByEmail(email);
            orders = orderService.getByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("orders", orders);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accountInfoView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
