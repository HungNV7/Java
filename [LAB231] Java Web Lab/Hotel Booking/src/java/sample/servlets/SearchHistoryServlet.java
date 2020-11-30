/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.CartDAO;
import sample.dtos.CartDTO;
import sample.dtos.UserDTO;
/**
 *
 * @author ASUS
 */
public class SearchHistoryServlet extends HttpServlet {
    private final String SUCCESS = "searchHistory.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(SearchHistoryServlet.class.getName());
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
            String id = request.getParameter("id");
            String txtdate = request.getParameter("date");
            HttpSession session = request.getSession();
            session.setAttribute("SEARCH_HISTORY", null);
            UserDTO user = (UserDTO) session.getAttribute("USER");
            List<CartDTO> listHistory = (List<CartDTO>) session.getAttribute("HISTORY");
            Date date = null;         
            try {
                date = Date.valueOf(txtdate);
            } catch (Exception e) {
            }
            
            CartDAO cartDAO = new CartDAO();
            List<String> orderID = cartDAO.searchHistory(id, date, user.getUserID());
            List<CartDTO> searchCart = new ArrayList<CartDTO>();
            if(!orderID.isEmpty()){
                for (CartDTO cartDTO : listHistory) {
                    if(orderID.contains(cartDTO.getId())){
                       searchCart.add(cartDTO);
                    }
                }
            }
            if(searchCart.size() != 0){
                session.setAttribute("SEARCH_HISTORY", searchCart);
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
