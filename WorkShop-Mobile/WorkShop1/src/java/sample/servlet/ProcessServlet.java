/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.role.RoleObj;

/**
 *
 * @author CungNguyen
 */
public class ProcessServlet extends HttpServlet {

    private final String loginServlet = "LoginServlet";
    private final String loginPage = "login.html";
    private final String deleteRecordServlet = "DeleteRecordServlet";
    private final String updateRecordServlet = "UpdateRecordServlet";
    private final String insertNewMobile = "InsertMobileServlet";
    private final String addCartServlet = "AddCartServlet";
    private final String viewCartServlet = "viewcart.jsp";
    private final String payCartServlet = "PayCartServlet";
    private final String removeItemsServlet = "RemoveItemsServlet";
    private final String logOutServlet = "LogOutServlet";
    private final String createAccountServlet = "CreateAccountServlet";

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
        String url = loginPage;
        try {
            HttpSession session = request.getSession();
            /* TODO output your page here. You may use following sample code. */
            String button = request.getParameter("btAction");
            if (button == null) {

            } else if (button.equals("Login")) {
                url = loginServlet;
            } else if (button.equals("Search")) {
                RoleObj roleObj = (RoleObj) session.getAttribute("ROLE");
                roleObj.transferRole(button);
                url = roleObj.getUrl();
            } else if (button.equals("Delete")) {
                RoleObj roleObj = (RoleObj) session.getAttribute("ROLE");
                roleObj.transferRole(button);
                url = roleObj.getUrl();
            } else if (button.equals("Update")) {
                RoleObj roleObj = (RoleObj) session.getAttribute("ROLE");
                roleObj.transferRole(button);
                url = roleObj.getUrl();
            } else if (button.equals("Insert New Mobile")) {
                url = insertNewMobile;
            } else if (button.equals("Add To Cart")) {
                url = addCartServlet;
            } else if (button.equals("View Your Cart")) {
                url = viewCartServlet;
            } else if (button.equals("Pay Cart")) {
                url = payCartServlet;
            } else if (button.equals("Remove Your Items Selected")) {
                url = removeItemsServlet;
            } else if (button.equals("Log Out")) {
                url = logOutServlet;
            } else if (button.equals("Create New Account")) {
                url = createAccountServlet;
            }

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
