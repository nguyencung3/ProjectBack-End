/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_mobile.Tbl_MobileDTO;

/**
 *
 * @author CungNguyen
 */
public class SearchByStaffServlet extends HttpServlet {

    private final String staffpage = "staffpage.jsp";

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String txtsearchvalue = request.getParameter("txtSearchValueByStaff");
             List<Tbl_MobileDTO> result2=(List<Tbl_MobileDTO>)request.getAttribute("RESULT");
            if (txtsearchvalue.trim().length() > 0) {
                Tbl_MobileDAO dao = new Tbl_MobileDAO();
                dao.SearchDeviceByIDOrName(txtsearchvalue);
                List<Tbl_MobileDTO> result = dao.getListDevice();
                request.setAttribute("RESULT", result);
            }
        } catch (SQLException ex) {
            log("SearchByStaffServlet _ SQL " + ex.getMessage());
        }catch (ClassNotFoundException ex)
        {
            log("SearchByStaffServlet _ ClassNotFound " + ex.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(staffpage);
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
