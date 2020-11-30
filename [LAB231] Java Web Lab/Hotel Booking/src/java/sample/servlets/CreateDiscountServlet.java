/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlets;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.daos.DiscountDAO;
import sample.dtos.DiscountDTO;
import sample.dtos.ErrorDiscountDTO;

/**
 *
 * @author ASUS
 */
public class CreateDiscountServlet extends HttpServlet {

    private final String SUCCESS = "home.jsp";
    private final String ERROR = "createDiscount.jsp";
    private Logger logger = Logger.getLogger(CreateDiscountServlet.class.getName());

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
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String txtPercentage = request.getParameter("percentage");
            String txtDate = request.getParameter("date");
            Date expiryDate = null;
            boolean check = true;
            DiscountDAO discountDAO = new DiscountDAO();

            ErrorDiscountDTO errorDicountDTO = new ErrorDiscountDTO();
            if (code.isEmpty()) {
                errorDicountDTO.setErrorCode("Discount code is required!");
                check = false;
            }
            if(!discountDAO.checkCode(code)){
                errorDicountDTO.setErrorCode("Discount code is duplicated!");
                check = false;
            }
            if (name.isEmpty()) {
                errorDicountDTO.setErrorName("Name is required!");
                check = false;
            }
            if (txtDate.isEmpty()) {
                errorDicountDTO.setErrorDate("Expiry Date is required!");
                check = false;
            } else {
                expiryDate = Date.valueOf(txtDate);
                long current = System.currentTimeMillis();
                Date now = new Date(current);
                if (expiryDate.before(now)) {
                    errorDicountDTO.setErrorDate("Expiry Date must be after now!");
                    check = false;
                }
            }
            HttpSession session = request.getSession();
            if(check){
                DiscountDTO discountDTO = new DiscountDTO(code, name, Integer.parseInt(txtPercentage), null, expiryDate );
                discountDAO.insert(discountDTO);
                url = SUCCESS;
            }else{
                session.setAttribute("DISCOUNT_FAIL", errorDicountDTO);
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
