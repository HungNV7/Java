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
import sample.daos.UserDAO;
import sample.utils.Encryption;

/**
 *
 * @author ASUS
 */
public class ResetServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(ResetServlet.class.getName());
    private final String SUCCESS = "login.jsp";
    private final String ERROR = "reset.jsp";

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
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            String encryptedEmail = request.getParameter("email");
            UserDAO userDAO = new UserDAO();
            String email = "";
            List<String> listUserID = userDAO.getAllUser();
            for (String userID : listUserID) {
                String tmp = Encryption.toHexString(Encryption.getSHA(userID));
                if(tmp.equals(encryptedEmail)){
                    email = userID;
                    break;
                }
            }
            HttpSession session = request.getSession();
            if(password.equals(rePassword)){
                String encrytedPass = Encryption.toHexString(Encryption.getSHA(password));
                
                userDAO.updatePassword(email, encrytedPass);
                url = SUCCESS;
            }else{
                session.setAttribute("RESET_FAIL", "Two password must be the same!");
            }
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
