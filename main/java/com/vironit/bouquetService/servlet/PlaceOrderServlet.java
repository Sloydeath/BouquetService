package com.vironit.bouquetService.servlet;

import com.vironit.bouquetService.model.*;
import com.vironit.bouquetService.model.enums.*;
import com.vironit.bouquetService.service.UserServiceImpl;
import com.vironit.bouquetService.util.Cart;
import com.vironit.bouquetService.service.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/placeOrder"})
public class PlaceOrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(PlaceOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        Cart cart = Cart.get(session);
        List<Flower> flowers = cart.getFlowers();
        String errorString;

        if(flowers.isEmpty()){
            errorString = "Корзина пуста! Добавьте цветы в корзину!";
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/errorView.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/placeOrderView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = Cart.get(session);
        List<Flower> flowers = cart.getFlowers();
        boolean result;

        User user = null;
        Order order = new Order();
        Bouquet bouquet = new Bouquet();
        List<FlowerInBouquet> flowerInBouquets = new ArrayList<>();
        String email = (String) session.getAttribute("email");

        OrderServiceImpl orderService = new OrderServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        try {
            user = userService.getByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String address = (String) request.getParameter("address");
        String phoneNumber = (String) request.getParameter("phoneNumber");

        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setStatus(OrderStatus.IN_PROCESS);
        order.setUser(user);

        String type = (String) request.getParameter("type");
        bouquet.setType(BouquetType.valueOf(type));
        bouquet.setOrder(order);


        for(Flower f: flowers)
            flowerInBouquets.add(new FlowerInBouquet(f.getQuality(), bouquet, f));

        result = orderService.createOrder(order, bouquet, flowerInBouquets);

        if(result){
            session.removeAttribute("cart");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/successPlaceOrderView.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/errorView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
