/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.BookDAO;
import sample.dtos.BookDTO;
import sample.dtos.ErrorBookDTO;

/**
 *
 * @author Hung Nguyen
 */
public class AddNewBookController extends HttpServlet {

    private final String SUCCESS = "home.jsp";
    private final String ERROR = "newBook.jsp";

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
            String bookID = request.getParameter("txtBookID");
            String bookName = request.getParameter("txtBookName");
            String author = request.getParameter("txtAuthor");
            String quantity = request.getParameter("txtQuantity");
            String price = request.getParameter("txtPrice");
            String datePublic = request.getParameter("txtPublicDate");
            String linkImage = request.getParameter("txtLinkImage");
            System.out.println(linkImage);
            System.out.println(datePublic);

            ErrorBookDTO error = new ErrorBookDTO();
            boolean flag = true;
            BookDAO dao = new BookDAO();

            if (dao.getBook(bookID) != null) {
                error.setErrorBookID("Book ID is duplicated!");
                flag = false;
            }
            if (bookID.isEmpty()) {
                error.setErrorBookID("Book ID is required!");
                flag = false;
            }
            if (bookName.isEmpty()) {
                error.setErrorBookName("Book name is required!");
                flag = false;
            }
            if (author.isEmpty()) {
                error.setErrorAuthor("Author is required!");
                flag = false;
            }
            if (!quantity.matches("\\d+") || quantity.matches("[0]+")) {
                error.setErrorQuantity("Quantity must be a positive number");
                flag = false;
            }
            if (!price.matches("\\d+") || price.matches("[0]+")) {
                error.setErrorPrice("Price must be a positive number");
                flag = false;
            }
            if(!linkImage.contains(".png") && !linkImage.contains(".jpg")){
                error.setErrorLinkImage("Image file is invalid!");
                flag=false;
            }
            if(linkImage.isEmpty()){
                error.setErrorLinkImage("Image file is required!");
                flag=false;
            }
            if(datePublic.isEmpty()){
                error.setErrorPublicDate("Public date is required!");
                flag=false;
            }

            if (flag) {
                Date date = Date.valueOf(datePublic);
                BookDTO book=new BookDTO(bookID, bookName, author, Integer.parseInt(price), Integer.parseInt(quantity), date, linkImage);
                dao.addBook(book);
                HttpSession session=request.getSession();
                List<BookDTO> list=(List<BookDTO>)session.getAttribute("LIST_BOOK");
                if(list!=null){
                    list.add(book);
                }
                session.setAttribute("LIST_BOOK", list);
                
                url = SUCCESS;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_ADD_NEW_BOOK", error);
            }

        } catch (Exception e) {
            log("Error in AddNewBookController: " + e.toString());
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
