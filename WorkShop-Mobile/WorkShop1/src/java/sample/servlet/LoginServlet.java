/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_User.Tbl_UserDAO;
import sample.role.RoleObj;

/**
 *
 * @author CungNguyen
 */
public class LoginServlet extends HttpServlet {

    private final String invalidPage = "invalid.html";
    private final String userPage = "userpage.jsp";
    private final String staffPage = "staffpage.jsp";
    private final String adminPage = "adminpage.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = invalidPage;
        RoleObj roleObj = new RoleObj();
        try {
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            Tbl_UserDAO dao = new Tbl_UserDAO();
            int role = dao.checkLogin(username, password);
            roleObj.setRole(role);
            roleObj.setUserId(username);
            session.setAttribute("ROLE", roleObj);
            if (role == 0) {
                url = userPage;
            } else if (role == 2) {
                url = staffPage;
            } else if (role == 1) {
                url = adminPage;

            }
        } catch (SQLException ex) {
            log("LoginServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("LoginServlet _ ClassNotFoundException " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
