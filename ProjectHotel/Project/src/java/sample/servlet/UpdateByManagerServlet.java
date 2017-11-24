/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.room.RoomDAO;

/**
 *
 * @author CungNguyen
 */
public class UpdateByManagerServlet extends HttpServlet {

    private final String occurError = "errorUpdateRoom.html";
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
        String urlRewriting = occurError;
        try {
            String action = request.getParameter("btAction");
            StringTokenizer stk = new StringTokenizer(action, ":");
            stk.nextToken();
            String roomId = stk.nextToken();
            String lastSearchValue = request.getParameter("txtLastSearch");
            String description = request.getParameter("txtDescription" + roomId);
            float hourPrice = Float.parseFloat(request.getParameter("txtHourPrice" + roomId));
            float dayPrice = Float.parseFloat(request.getParameter("txtDayPrice" + roomId));
            RoomDAO dao = new RoomDAO();
            boolean result = dao.UpdateInfoRoom(roomId, description, hourPrice, dayPrice);
            if (result) {
                if (lastSearchValue == "") {
                    urlRewriting = "roomInfo?btAction=Show All";
                } else {
                    urlRewriting = "roomInfo?btAction=Search&txtRoomSearch=" + lastSearchValue;
                }
            } else {
                request.setAttribute("ERRORPAGE", "Update Room occurs Error");
            }
        } catch (SQLException ex) {
            log("UpdateByManagerServlet _ SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("UpdateByManagerServlet _ ClassNotFound" + ex.getMessage());
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
