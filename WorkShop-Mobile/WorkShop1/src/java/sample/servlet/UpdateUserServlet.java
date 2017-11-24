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
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_User.Tbl_UserDAO;
import sample.tbl_User.Tbl_UserTypeError;

/**
 *
 * @author Nguen Cung
 */
public class UpdateUserServlet extends HttpServlet {

    private final String updateErrPage = "errorpage.jsp";

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
        String urlRewriting = updateErrPage;
        try {
            /* TODO output your page here. You may use following sample code. */
            String userId = request.getParameter("txtUserId");
            String password = request.getParameter("txtPassWord");
            String fullname = request.getParameter("txtFullname");
            String role = request.getParameter("txtRole");
            int passwordInt = 0, roleInt = 0;
            boolean errorfound = false;
            Tbl_UserTypeError error = new Tbl_UserTypeError();
            if (fullname.trim().length() < 3 || fullname.trim().length() > 15) {
                errorfound = true;
                error.setFullnameLengthError("You must input fullname 3 - 15 char");
            }

            try {
                passwordInt = Integer.parseInt(password);
                if (password.trim().length() < 3 || password.trim().length() > 15) {
                    errorfound = true;
                    error.setPasswordNotRightTypeError("You must input password(3-15)digit");
                }
            } catch (NumberFormatException e) {
                errorfound = true;
                error.setPasswordNotRightTypeError("You must input right type");
            }
            try {
                roleInt = Integer.parseInt(role);
                if (roleInt < 0 || roleInt > 3) {
                    errorfound = true;
                    error.setRoleNotRightTypeError("You must input Role (0-3)");
                }
            } catch (NumberFormatException e) {
                errorfound = true;
                error.setRoleNotRightTypeError("You must input right type");
            }
            if (errorfound) {
                error.setUserIDerror(userId);
                request.setAttribute("ERRORUPDATE", error);
                urlRewriting = "ProcessServlet?btAction=Search&txtSearchValueByAdmin=" + request.getParameter("lastSearchValue");
            } else {
                Tbl_UserDAO dao = new Tbl_UserDAO();
                boolean result = dao.UpdateUser(userId, passwordInt, fullname, roleInt);
                if (result) {
                    urlRewriting = "ProcessServlet?btAction=Search&txtSearchValueByAdmin=" + request.getParameter("lastSearchValue");
                } else {
                    request.setAttribute("ERRORPAGE", "UpdateUserServlet Occur Error");
                }
            }
        } catch (SQLException ex) {
            log("UpdateUserServlet _ SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("UpdateUserServlet _ ClassNotFound" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
