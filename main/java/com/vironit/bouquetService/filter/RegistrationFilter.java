package com.vironit.bouquetService.filter;

import com.vironit.bouquetService.model.User;
import com.vironit.bouquetService.model.enums.Role;
import com.vironit.bouquetService.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "registrationFilter", urlPatterns = { "/doRegistration" })


public class RegistrationFilter implements Filter {

    private static final Logger log = Logger.getLogger(RegistrationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserServiceImpl userService = new UserServiceImpl();

        try {
            if (userService.isExistByEmail(email)) {
                request.getRequestDispatcher("/WEB-INF/views/registrationErrorView.jsp").forward(request, response);
            } else {
                String errorString = null;
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserRole(Role.valueOf(Role.USER.toString()));

                userService.add(user);

                request.setAttribute("errorString", errorString);
                request.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }
    }


    @Override
    public void destroy() {

    }
}
