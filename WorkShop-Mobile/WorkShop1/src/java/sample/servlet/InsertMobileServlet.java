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
public class InsertMobileServlet extends HttpServlet {

    private String insertErrorPage = "createNewMobile.jsp";
    private String staffPage = "staffpage.jsp";

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
        String url = insertErrorPage;
        String mobileID = request.getParameter("txtmobileID");
        String description = request.getParameter("txtDescription");
        String price = request.getParameter("txtPrice");
        String mobileName = request.getParameter("txtMobilename");
        String year = request.getParameter("txtyearOfProduction");
        String quantity = request.getParameter("txtQuantity");
        String ckbSale = request.getParameter("ckbNotsale");
        Tbl_MobileCheckVariable checkVariable = new Tbl_MobileCheckVariable();
        Tbl_MobileTypeError error = new Tbl_MobileTypeError();

        try {
            /* TODO output your page here. You may use following sample code. */
            boolean isNotSale = false;
            boolean errorFound = checkVariable.checkVariableInsert(mobileID, description, price, mobileName, year, quantity);
            if (errorFound) {
                error = checkVariable.getError();
                request.setAttribute("ERRORINSERT", error);
            } else {
                if (ckbSale != null) {
                    isNotSale = true;
                }
                Tbl_MobileDAO dao = new Tbl_MobileDAO();
                int yearInt = checkVariable.getYearInt();
                float priceFloat = checkVariable.getPriceFloat();
                int quantityInt = checkVariable.getQuantityInt();
                boolean result = dao.insertNewMobile(mobileID, description, priceFloat, mobileName, yearInt, quantityInt, isNotSale);
                if (result) {
                    url = staffPage;
                }
            }

        } catch (ClassNotFoundException ex) {
            log("InsertMobileServlet _ ClassNotFound" + ex.getMessage());
        } catch (SQLException ex) {
            log("InsertMobileServlet _ SQL" + ex.getMessage());
            error.setMobileIDExisted("The mobile is existed");
            request.setAttribute("ERRORINSERT", error);
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
