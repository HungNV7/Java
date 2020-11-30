/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.RoomDAO;
import sample.dtos.CartDTO;

/**
 *
 * @author ASUS
 */
public class NextCheckoutServlet extends HttpServlet {

    private final String SUCCESS = "nextCart.jsp";
    private final String ERROR = "cart.jsp";
    private Logger logger = Logger.getLogger(NextCheckoutServlet.class.getName());

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
            String action = request.getParameter("btnAction");
            if (action.equals("Update")) {
                request.getRequestDispatcher("UpdateServlet").forward(request, response);
            } else {
                String txtArriveDate = request.getParameter("txtArriveDate");
                String txtReturnDate = request.getParameter("txtReturnDate");
                Date arriveDate = Date.valueOf(txtArriveDate);
                Date returnDate = Date.valueOf(txtReturnDate);
                HttpSession session = request.getSession();
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (arriveDate.before(returnDate)) {
                    long gap = ChronoUnit.DAYS.between(arriveDate.toLocalDate(), returnDate.toLocalDate());
                    cart.setArriveDate(arriveDate);
                    cart.setReturnDate(returnDate);

                    session.setAttribute("ARRIVE_DATE", arriveDate);
                    session.setAttribute("RETURN_DATE", returnDate);
                    session.setAttribute("GAP", gap);

                    cart.setTotal(cart.getTotal() * gap);
                    cart.setFinalTotal(cart.getTotal());
                    RoomDAO roomDAO = new RoomDAO();
                    List<String> msg = roomDAO.checkValid(cart);
                    if (msg.size() == 0) {
                        url = SUCCESS;
                    } else {
                        session.setAttribute("CHECKVALID_FAIL", msg);    
                    }
                } else {
                    session.setAttribute("NEXT_FAIL", "Check in date must be befor check out date");
                }
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            logger.error(e);
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
