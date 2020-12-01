package com.vironit.bouquetService.filter;

import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.model.enums.Role;
import com.vironit.bouquetService.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "authFilter", urlPatterns = { "/login" })


public class AuthFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserServiceImpl userService = new UserServiceImpl();
        User user = null;

        HttpSession session = request.getSession();

        if (session.getAttribute("email") != null && session.getAttribute("password") != null) {
            final String role = (String) session.getAttribute("role");
            moveToMenu(request, response, role);

        } else {
            try {
                if (userService.isExistByEmailPassword(email, password)) {

                    user = userService.getByEmailPassword(email, password);

                    String role = "";

                    request.getSession().setAttribute("password", password);
                    request.getSession().setAttribute("email", email);
                    if (user != null) {
                        request.getSession().setAttribute("role", role = user.getUserRole().toString());
                    }

                    moveToMenu(request, response, role);

                } else {

                    request.getRequestDispatcher("/WEB-INF/views/loginView.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e);
            }
        }

    }


    private void moveToMenu(HttpServletRequest request, HttpServletResponse response, String role)
            throws ServletException, IOException {
        if (role.equals(Role.ADMIN.toString()))
            request.getRequestDispatcher("/WEB-INF/views/adminMenuView.jsp").forward(request, response);
        else if(role.equals(Role.USER.toString()))
            request.getRequestDispatcher("/WEB-INF/views/userMenuView.jsp").forward(request, response);
    }


    @Override
    public void destroy() {

    }
}
