/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_room.Tbl_roomDAO;
import sample.tbl_room.Tbl_roomDTO;

/**
 *
 * @author CungNguyen
 */
public class FilterDateServlet extends HttpServlet {

    private final String showFilterResult = "staff.jsp";

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
        String urlRewriting = showFilterResult;
        try {

            /* TODO output your page here. You may use following sample code. */
            String date = request.getParameter("txtDate");
            Tbl_roomDAO dao = new Tbl_roomDAO();
            StringTokenizer st = new StringTokenizer(date, "-");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            Timestamp requestDate = new Timestamp(year-1900, month-1, day, 0, 0, 0, 0);
            dao.filterDate(requestDate);
            List<Tbl_roomDTO> list = dao.getListRoomsMaintance();
            request.setAttribute("RESULT", list);
            urlRewriting += "?txtDate=" + date;
        } catch (SQLException ex) {
            log("FilterDateServlet _ SQL" + ex.getMessage());
        } catch (ParseException ex) {
            log("FilterDateServlet _ Parse" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("FilterDateServlet _ ClassNotFound" + ex.getMessage());
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
