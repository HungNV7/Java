/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.dtos.CartDTO;
import sample.dtos.RoomDTO;
/**
 *
 * @author ASUS
 */
public class AddServlet extends HttpServlet {
    private final String SUCCESS = "detail.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(AddServlet.class.getName());
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
            String quantity = request.getParameter("txtNumber");
            int category = Integer.parseInt(request.getParameter("roomType"));
            
            HttpSession session = request.getSession();
            List<RoomDTO> listRoom = (List<RoomDTO>) session.getAttribute("LIST_ROOM");
            RoomDTO room = listRoom.get(category);
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if(cart == null){
                cart = new CartDTO();
            }
            cart.add(room, Integer.parseInt(quantity));
            
            long currenTime = System.currentTimeMillis();
            Date arriveDate = new Date(currenTime);
            Date returnDate = new Date(currenTime + 24*60*60*1000);
            
            session.setAttribute("ARRIVE_DATE", arriveDate);
            session.setAttribute("RETURN_DATE", returnDate);
            session.setAttribute("CART", cart);
            url = SUCCESS;
        } catch (Exception e) {
            logger.error(e);
        }finally{
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
