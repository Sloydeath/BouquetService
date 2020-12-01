package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.dao.BouquetDAOImpl;
import com.vironit.bouquetService.dao.OrderDAOImpl;
import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.model.Order;
import com.vironit.bouquetService.model.enums.BouquetType;
import com.vironit.bouquetService.service.BouquetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/editBouquet" })
public class UpdateBouquetServlet extends HttpServlet {


    public UpdateBouquetServlet() {

        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        Bouquet bouquet = null;
        String errorString = null;

        BouquetServiceImpl bouquetService = new BouquetServiceImpl();
        try {
            bouquet = bouquetService.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/bouquetList");
            return;
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("bouquet", bouquet);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/updateBouquetView.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Order order = null;
        String errorString = null;

        long id = Long.parseLong(request.getParameter("id"));
        String type = request.getParameter("type");
        OrderDAOImpl orderDAO = new OrderDAOImpl();

        try {
            if(request.getParameter("orderId").equals("")) {
                order = null;
                errorString = "Error";
            }
            else if(orderDAO.getById(Long.parseLong(request.getParameter("orderId"))) == null){
                order = null;
                errorString = "Error";
            }
            else order = orderDAO.getById(Long.parseLong(request.getParameter("orderId")));

            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/errorView.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Bouquet bouquet = new Bouquet();
        bouquet.setId(id);
        bouquet.setType(BouquetType.valueOf(type));
        bouquet.setOrder(order);

        try {
            BouquetDAOImpl bouquetDAO = new BouquetDAOImpl();
            bouquetDAO.update(bouquet);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("bouquet", bouquet);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/updateBouquetView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/bouquetList");
        }
    }

}

