package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.model.Bouquet;
import com.vironit.bouquetService.service.BouquetServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { "/bouquetList" })

public class BouquetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BouquetServiceImpl bouquetService = new BouquetServiceImpl();
        List<Bouquet> bouquetList = null;
        try {
            bouquetList = bouquetService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("bouquetList", bouquetList);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/bouquetListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
