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
import sample.tbl_account.Tbl_accountDAO;
import sample.role.RoleObj;

/**
 *
 * @author CungNguyen
 */
public class LoginServlet extends HttpServlet {

    private final String invalidPage = "invalid.html";

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
        String urlRewriting = invalidPage;
        try {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            Tbl_accountDAO dao = new Tbl_accountDAO();
            int role = dao.checkLogin(username, password);
            if (role != -1) {
                RoleObj roleObj = new RoleObj();
                roleObj.setRole(role);
                roleObj.setUserId(username);
                HttpSession session = request.getSession();
                session.setAttribute("ROLE", roleObj);
                urlRewriting = "decentral?txtrole=" + role;
            }
        } catch (ClassNotFoundException ex) {
            log("LoginServlet _ ClassNotFound " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet _ SQL " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
