package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.dao.BouquetDAOImpl;
import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.model.enums.BouquetType;
import com.vironit.bouquetService.service.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/createBouquet" })

public class CreateBouquetServlet extends HttpServlet {

    public CreateBouquetServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createBouquetView.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Order order = null;
        String errorString = null;

        String type = request.getParameter("type");
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            if(request.getParameter("orderId").equals("")) {
                errorString = "Error";
            }
            else if(orderService.getById(Long.parseLong(request.getParameter("orderId"))) == null){
                errorString = "Error";
            }
            else order = orderService.getById(Long.parseLong(request.getParameter("orderId")));

            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/errorView.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bouquet bouquet = new Bouquet();
        bouquet.setType(BouquetType.valueOf(type));

        if(order != null)
            bouquet.setOrder(order);
        else bouquet.setOrder(null);

        BouquetDAOImpl bouquetDAO = new BouquetDAOImpl();
        try {
            bouquetDAO.add(bouquet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("bouquet", bouquetDAO);

        response.sendRedirect(request.getContextPath() + "/bouquetList");

    }

}
