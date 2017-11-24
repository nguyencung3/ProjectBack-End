/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_User.Tbl_UserDAO;
import sample.tbl_User.Tbl_UserTypeError;

/**
 *
 * @author CungNguyen
 */
public class CreateAccountServlet extends HttpServlet {

    private final String insertErrorPage = "createNewAccount.jsp";
    private final String loginPage = "login.html";

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
        String username = request.getParameter("txtUserId");
        String password = request.getParameter("txtPassWord");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String url = insertErrorPage;
        int passwordInt = 0, roleInt = 0;
        boolean errorfound = false;
        Tbl_UserTypeError error = new Tbl_UserTypeError();
        try {

            /* TODO output your page here. You may use following sample code. */
            if (fullname.trim().length() < 5 || fullname.trim().length() > 15) {
                errorfound = true;
                error.setFullnameLengthError("You must input fullname 5 - 15 char");
            }
            if (username.trim().length() < 3 || username.trim().length() > 15) {
                errorfound = true;
                error.setUserNameLengthError("You must input fullname 3 - 15 char");
            }
            try {
                passwordInt = Integer.parseInt(password);
                if (password.trim().length() < 3 || password.trim().length() > 15) {
                    errorfound = true;
                    error.setPasswordNotRightTypeError("You must input password(3-15)digit");
                } else if (!confirm.trim().equals(password)) {
                    errorfound = true;
                    error.setConfirmNotMatch("Confirm must match password");
                }
            } catch (NumberFormatException e) {
                errorfound = true;
                error.setPasswordNotRightTypeError("You must input right type");
            }
            if (errorfound) {
                request.setAttribute("CREATEERROR", error);
            } else {
                Tbl_UserDAO dao = new Tbl_UserDAO();
                boolean result = dao.insertRecord(username, passwordInt, fullname, roleInt);
                if (result) {
                    url = loginPage;
                }
            }
        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet _ ClassNotFound " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateAccountServlet _ SQL " + ex.getMessage());
            error.setUsernameIsExisted(username + "is Existed in System");
            request.setAttribute("CREATEERROR", error);
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
