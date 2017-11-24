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
import sample.tbl_mobile.Tbl_MobileCheckVariable;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_mobile.Tbl_MobileTypeError;

/**
 *
 * @author CungNguyen
 */
public class UpdateRecordServlet extends HttpServlet {

    private final String updateError = "errorpage.jsp";

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
        String lastSearchValue = request.getParameter("txtLastSearchValue");
        String urlRewriting = "ProcessServlet?btAction=Search"
                + "&txtSearchValueByStaff=" + lastSearchValue;
        String mobileId = request.getParameter("txtmobileID");
        String description = request.getParameter("txtdescription");
        String price = request.getParameter("txtPrice");
        String quantity = request.getParameter("txtQuantity");
        String ckbisNotSale = request.getParameter("ckbIsNotSale");
        boolean isNotSale = false;
        boolean errorFound = false;
        Tbl_MobileTypeError error = new Tbl_MobileTypeError();
        Tbl_MobileCheckVariable checkVariable = new Tbl_MobileCheckVariable();
        try {
            errorFound = checkVariable.checkVariableUpdate(description, price, quantity);
            if (errorFound) {
                error = checkVariable.getError();
                error.setMobileIDerror(mobileId);
                request.setAttribute("ERRORUPDATE", error);
            } else {
                float priceFloat = checkVariable.getPriceFloat();
                int quantityInt = checkVariable.getQuantityInt();
                if (ckbisNotSale != null) {
                    isNotSale = true;
                }
                Tbl_MobileDAO dao = new Tbl_MobileDAO();
                boolean result = dao.updateRecord(mobileId, description, priceFloat, quantityInt, isNotSale);
                if (!result) {
                    request.setAttribute("ERRORPAGE", "UpdateRecordServlet Occur Error");
                    urlRewriting = updateError;
                }
            }

        } catch (SQLException ex) {
            log("UpdateRecordServlet _ SQL" + ex.getMessage());

        } catch (ClassNotFoundException ex) {
            log("UpdateRecordServlet _ ClassNotFound" + ex.getMessage());
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
