/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.DiscountDAO;
import sample.dtos.CartDTO;
import sample.dtos.DiscountDTO;
/**
 *
 * @author ASUS
 */
public class ApplyServlet extends HttpServlet {
    private final String SUCCESS = "nextCart.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(ApplyServlet.class.getName());
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
        String url = ERROR;
        try {
            String discountCode = request.getParameter("txtDiscountCode");
            HttpSession session = request.getSession();
            DiscountDAO discountDAO = new DiscountDAO();
            DiscountDTO discount = discountDAO.checkDiscountCode(discountCode);
            if(discount != null){
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                cart.setDiscountID(discountCode);
                double finalTotal = cart.getTotal()* (1 - (double)discount.getPercentage()/100);
                cart.setFinalTotal(Double.parseDouble(new DecimalFormat("#########.##").format(finalTotal)));
                cart.setDiscountID(discountCode);
            }else{
                session.setAttribute("DISCOUNT_FAIL", "Discount code is incorrect or expiry");
            }     
            url = SUCCESS;
        } catch (Exception e) {
            logger.error(e);
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
