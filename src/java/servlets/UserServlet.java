/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import services.*;

/**
 *
 * @author Eric
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();

        try {
            List<User> users = userService.getAll();
            request.setAttribute("users", users);

            if (request.getParameter("action") != null && request.getParameter("action").equals("edit")) {
                request.setAttribute("action", "edit");
                String email = request.getParameter("email");
                email = email.replace(' ', '+');
                User user = userService.get(email);
                request.setAttribute("user", user);
            } else {
                request.setAttribute("action", "add");
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        RoleService roleService = new RoleService();

        String action = request.getParameter("action");

        if (action.equals("add")) {
            String email = request.getParameter("email");
            String firstName = request.getParameter("first-name");
            String lastName = request.getParameter("last-name");
            String password = request.getParameter("password");
            String roleParam = request.getParameter("role");
            try {
                if (roleParam.equals("admin")) {
                    userService.insert(email, firstName, lastName, password, new Role(1));
                } else if (roleParam.equals("user")) {
                    userService.insert(email, firstName, lastName, password, new Role(2));
                }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals("edit")){
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String roleParam = request.getParameter("role");
            try {
                if (roleParam.equals("admin")) {
                    userService.update(email, firstName, lastName, password, new Role(1));
                } else if (roleParam.equals("user")) {
                    userService.update(email, firstName, lastName, password, new Role(2));
                }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
