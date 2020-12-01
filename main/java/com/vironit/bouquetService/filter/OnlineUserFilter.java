package com.vironit.bouquetService.filter;

import com.vironit.bouquetService.model.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "onlineUserFilter", urlPatterns = { "/registration" })


public class OnlineUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if (session.getAttribute("email") != null && session.getAttribute("password") != null) {
            String role = (String) session.getAttribute("role");
            moveToMenu(request, response, role);

        } else {
            request.getRequestDispatcher("/WEB-INF/views/registrationView.jsp").forward(request, response);
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
