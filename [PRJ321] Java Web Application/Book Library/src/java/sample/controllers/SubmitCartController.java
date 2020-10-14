/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.BookDAO;
import sample.daos.CartDAO;
import sample.dtos.BookDTO;
import sample.dtos.CartDTO;
import sample.dtos.HistoryDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author Admin
 */
public class SubmitCartController extends HttpServlet {

    private final String SUCCESS = "home.jsp";
    private final String ERROR = "viewCart.jsp";

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
            HttpSession session = request.getSession();
            Date date = new Date();
            java.sql.Date rentDate = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());

            String txtReturnDate = request.getParameter("txtReturnDate");
            if (txtReturnDate.isEmpty()) {
                session.setAttribute("ERROR_VIEW_CART", "Return date is required!");
            } else {
                java.sql.Date returnDate = java.sql.Date.valueOf(txtReturnDate);
                if (returnDate.before(rentDate)) {
                    session.setAttribute("ERROR_VIEW_CART", "Return date must be after Rent Date!");
                } else {
                    CartDAO cartDAO = new CartDAO();
                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    boolean flag=true;
                    String error="";
                    for(BookDTO book : cart.getCart().values()){
                        String msg=cartDAO.checkValid(book.getId(), book.getQuantity());
                         if(!msg.equals("")){
                             flag=false;
                             String[] split = msg.split("-");
                             msg = split[0] + " has only " + split[1] + " books in library!";
                             error+=""+msg;
                         }
                    }
                    if(flag){
                    String userID = ((UserDTO) session.getAttribute("USER")).getUserName();
                    String orderID = cartDAO.getOrderID(userID);
                    cartDAO.submit(orderID, rentDate, returnDate);

                    HistoryDTO history = (HistoryDTO) session.getAttribute("HISTORY");

                    if (history == null) {
                        history = new HistoryDTO();
                        UserDTO user = (UserDTO) session.getAttribute("USER");
                        history.getHistory(user.getUserName());
                    }

                    cart.setRentDate(rentDate);
                    cart.setReturnDate(returnDate);
                    cart.setId(orderID);
                    cart.setUserID(userID);
                    history.getHistory().put(orderID, cart);
                    session.setAttribute("HISTORY", history);
                    url = SUCCESS;
                    session.removeAttribute("CART");
                    
                    //get list book after checkout (update status)
                    BookDAO book = new BookDAO();
                    List<BookDTO> list =book.getListBook();
                    session.setAttribute("LIST_BOOK", list);
                    }else{
                        session.setAttribute("ERROR_VIEW_CART", error);
                    }
                }

            }
        } catch (Exception e) {
            log("Error in SubmitCartController: " + e.toString());
        } finally {
            response.sendRedirect(url);
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
