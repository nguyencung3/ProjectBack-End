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
public class SearchByUserServlet extends HttpServlet {
    private String showSearchResult = "userpage.jsp";
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
        String mintxt = request.getParameter("txtFrom");
        String maxtxt = request.getParameter("txtTo");
        try {
            int min = Integer.parseInt(mintxt);
            int max = Integer.parseInt(maxtxt);
            if (min>max)
            {
                int temp = max;
                max = min;
                min = temp;
            }
            Tbl_MobileDAO dao = new Tbl_MobileDAO();
            dao.SearchDeviceByRange(max, min);
 
            List<Tbl_MobileDTO> result = dao.getListDevice();
            request.setAttribute("RESULT", result);
        }catch(NumberFormatException ex){
            log("SearchByUserServlet _ NumerFormat " +ex.getMessage());
             request.setAttribute("ERRORINPUT","Please Input number and Try again");
        }
        catch (SQLException ex){
            log("SearchByUserServlet _ SQL "+ex.getMessage());
        }
        catch(ClassNotFoundException ex){
            log("SearchByUserServlet _ ClassNotFound "+ex.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(showSearchResult);
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
