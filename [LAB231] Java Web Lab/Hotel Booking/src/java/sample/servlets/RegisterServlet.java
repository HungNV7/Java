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
import sample.dtos.ErrorUserDTO;
import sample.dtos.UserDTO;
import sample.utils.Encryption;
/**
 *
 * @author ASUS
 */
public class RegisterServlet extends HttpServlet {
    private final String SUCCESS = "login.jsp";
    private final String ERROR = "register.jsp";
    private Logger logger = Logger.getLogger(RegisterServlet.class.getName());
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
            boolean flag = false;

            String userID = request.getParameter("txtUserID");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");

            boolean check = true;
            ErrorUserDTO error = new ErrorUserDTO();

            if (name.length() < 6 || name.length() > 20) {
                check = false;
                error.setErrorName("Name must be 6-20 digits!");
            }
            if (password.length() < 6 || password.length() > 20) {
                check = false;
                error.setErrorPassword("Password must be 6-20 digits!");
            }
            if (!password.equals(rePassword)) {
                check = false;
                error.setErrorRePassword("Two password is not the same!");
            }
            if(phone.isEmpty()){
                check = false;
                error.setErrorPhone("Phone is required!");
            }
            if(!phone.matches("[0-9]+")){
                check = false;
                error.setErrorPhone("Phone must be number string!");
            }
            if(address.isEmpty()){
                check = false;
                error.setErrorAddress("Address is required!");
            }

            UserDAO dao = new UserDAO();
            UserDTO userDTO = dao.checkUserID(userID);
            if(userDTO != null){
                check = false;
                error.setErrorUserID("userID is duplicated!");
            }
            
            if (check) {
                String enryptedPassword = Encryption.toHexString(Encryption.getSHA(password));
                dao.addNew(new UserDTO(userID, name, enryptedPassword, phone, address, "", 1, 2));
                url = SUCCESS;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_REGISTER", error);
            }
        } catch (Exception e) {
            logger.error(e);
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
