/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.UserDAO;
import sample.utils.Encryption;
import sample.utils.SendingEmail;

/**
 *
 * @author ASUS
 */
public class SendReqChangePassServlet extends HttpServlet {

    private final String SUCCESS = "login.jsp";
    private final String ERROR = "error.jsp";
    private Logger logger = Logger.getLogger(SendReqChangePassServlet.class.getName());

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
            String email = request.getParameter("email");
            UserDAO userDAO = new UserDAO();
            if (userDAO.checkEmail(email)) {
                SendingEmail sendingEmail = new SendingEmail(email);
                String encrytedEmail = Encryption.toHexString(Encryption.getSHA(email));
                String link = "http://localhost:8080/Hotel_Booking/reset.jsp?none=" + encrytedEmail;
                sendingEmail.sendReqChangePassword(link);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("EMAIL_INVALID", "Email is not valid!");
            }
            url = SUCCESS;
        } catch (Exception e) {
            logger.error(e);
        } finally {
            response.sendRedirect(url);
        }
    }

    ;

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
