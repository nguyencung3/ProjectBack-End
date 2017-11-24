/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.CartObj;
import sample.cart.QuantityObj;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_OrderDetail.Tbl_OrderDetailDAO;

/**
 *
 * @author CungNguyen
 */
public class PayCartServlet extends HttpServlet {

    private final String paysuccess = "paysuccess.jsp";
    private final String payUnsuccess = "viewcart.jsp";

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
        QuantityObj quantity = new QuantityObj();

        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        int quantityInStock = 0;
        int count = 0;
        String url = payUnsuccess;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, QuantityObj> items = cart.getItems();
                    for (Map.Entry<String, QuantityObj> item : items.entrySet()) {
                        String idMobile = item.getKey();
                        StringTokenizer st = new StringTokenizer(idMobile, ",");
                        idMobile = st.nextToken();
                        // get IdMobile by Split (,) in Key
                        quantity = item.getValue();
                        //get quantityObj
                        quantityInStock = dao.getQuantityProduct(idMobile);
                        if (quantityInStock != -1) {
                            quantityInStock = quantityInStock - quantity.getQuantityUser();
                        } else {
                            quantityInStock = 0;
                            quantity.setQuantityUser(0);
                        }
                        quantity.setQuantityinStock(quantityInStock);
                        if (quantityInStock >= 0) {
                            ++count;
                        }
                        items.put(item.getKey(), quantity);
                    }
                    if (count == items.size()) {
                        //pay success so update tbl_Mobile and insert orderdetail into tbl_orderdetail
                        Tbl_OrderDetailDAO order = new Tbl_OrderDetailDAO();
                        int orderId = order.getOrderId() + 1;
                        if (orderId == 0) {
                            orderId = 1;
                        }
                        for (Map.Entry<String, QuantityObj> item : items.entrySet()) {
                            String idMobile = item.getKey();
                            StringTokenizer st = new StringTokenizer(idMobile, ",");
                            idMobile = st.nextToken();
                            // get IdMobile by Split (,) in Key
                            quantity = item.getValue();
                            // get quantityObj

                            order.insertNewOrder(orderId, cart.getUserId(), idMobile, quantity.getQuantityUser());
                            dao.updateRecord(idMobile, quantity.getQuantityinStock());
                        }
                        session.removeAttribute("CART");
                        url = paysuccess;
                    } else {
                        cart.setItems(items);
                        session.setAttribute("CART", cart);
                    }
                }
            }

        } catch (SQLException ex) {
            log("PayCartServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("PayCartServlet _ ClassNotFound " + ex.getMessage());
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
