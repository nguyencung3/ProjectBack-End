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

/**
 *
 * @author CungNguyen
 */
public class DeleteRecordServlet extends HttpServlet {

    private final String deleteErrPage = "errorpage.jsp";

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
        String urlRewriting = deleteErrPage;
        try {
            String primaryKey = request.getParameter("pk");
            String lastSearchValue = request.getParameter("lastsearchValue");
            Tbl_MobileDAO dao = new Tbl_MobileDAO();
            boolean result = dao.DeleteRecord(primaryKey);
            if (result) {
                urlRewriting = "ProcessServlet?"
                        + "btAction=Search"
                        + "&txtSearchValueByStaff=" + lastSearchValue;
            } else {
                request.setAttribute("ERRORPAGE", "DeleteRecordServlet Occur Error");
            }
        } catch (SQLException ex) {
            log("DeleteRecordServlet _ SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("DeleteRecordServlet _ ClassNotFound" + ex.getMessage());
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
