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
import sample.daos.HotelDAO;
import sample.dtos.HotelDTO;

/**
 *
 * @author ASUS
 */
public class SearchServlet extends HttpServlet {

    private final String SUCCESS = "search.jsp";
    private final String ERROR = "home.jsp";
    private Logger logger = Logger.getLogger(SearchServlet.class.getName());

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
            String nameOrArea = request.getParameter("txtNameOrArea");
            String txtArriveDate = request.getParameter("txtArriveDate");
            String txtReturnDate = request.getParameter("txtReturnDate");
            String txtRooms = request.getParameter("txtRooms");
            HttpSession session = request.getSession();
            if (txtArriveDate.isEmpty() || txtReturnDate.isEmpty()) {
                session.setAttribute("SEARCH_FAIL", "Please enter date!");
            } else {
                Date arriveDate = Date.valueOf(txtArriveDate);
                Date returnDate = Date.valueOf(txtReturnDate);
                if (!arriveDate.after(returnDate)) {
                    HotelDAO hotelDAO = new HotelDAO();
                    List<HotelDTO> list = hotelDAO.getListSearch(nameOrArea, arriveDate, Integer.parseInt(txtRooms));

                    session.setAttribute("LIST_SEARCH", list);
                    url = SUCCESS;
                } else {
                    session.setAttribute("SEARCH_FAIL", "Check in date must be befor check out date");
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
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
