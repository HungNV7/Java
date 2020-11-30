/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.FeedbackDAO;
import sample.daos.HotelDAO;
import sample.daos.RoomDAO;
import sample.dtos.FeedbackDTO;
import sample.dtos.HotelDTO;
import sample.dtos.RoomDTO;
import sun.security.pkcs11.wrapper.Functions;
/**
 *
 * @author ASUS
 */
public class DetailServlet extends HttpServlet {
    private final String SUCCESS = "detail.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(DetailServlet.class.getName());
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
            HotelDAO hotelDAO = new HotelDAO();
            HotelDTO hotel = hotelDAO.getHotel(id);
            
            RoomDAO roomDAO = new RoomDAO();
            List<RoomDTO> list =roomDAO.getListRoom(id);
            HttpSession session = request.getSession();
            session.removeAttribute("FEEDBACK");
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<FeedbackDTO> feedback = feedbackDAO.getFeedback(id);
            session.setAttribute("DETAIL_HOTEL", hotel);
            session.setAttribute("FEEDBACK", feedback);
            session.setAttribute("LIST_ROOM", list);
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
