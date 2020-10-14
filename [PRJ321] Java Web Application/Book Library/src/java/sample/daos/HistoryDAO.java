/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import sample.dtos.BookDTO;
import sample.dtos.CartDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Hung Nguyen
 */
public class HistoryDAO {

    public HashMap<String, CartDTO> getHistory(String userID) throws Exception {
        HashMap<String, CartDTO> result = new HashMap<String, CartDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID, rentDate, returnDate FROM tblOrder WHERE userID=? AND status=1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getNString("orderID");
                    Date rentDate = rs.getDate("rentDate");
                    Date returnDate = rs.getDate("returnDate");
                    HashMap<String, BookDTO> list = new HashMap<String, BookDTO>();

                    String sql2 = "SELECT bookID, amount FROM tblOrderDetail WHERE orderID=?";
                    PreparedStatement stm2 = conn.prepareStatement(sql2);
                    stm2.setString(1, orderID);
                    ResultSet rs2 = stm2.executeQuery();
                    BookDAO dao = new BookDAO();
                    while (rs2.next()) {
                        BookDTO book = dao.getBook(rs2.getNString("bookID"));
                        book.setQuantity(rs2.getInt("amount"));
                        list.put(rs2.getNString("bookID"), book);
                    }
                    CartDTO cart = new CartDTO(list, orderID, userID, rentDate, returnDate);
                    result.put(orderID, cart);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public HashMap<String, CartDTO> getHistory() throws Exception {
        HashMap<String, CartDTO> result = new HashMap<String, CartDTO>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm=conn.createStatement();
                String sql = "SELECT userID, orderID, rentDate, returnDate FROM tblOrder WHERE status=1";

                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String userID=rs.getNString("userID");
                    String orderID = rs.getNString("orderID");
                    Date rentDate = rs.getDate("rentDate");
                    Date returnDate = rs.getDate("returnDate");
                    HashMap<String, BookDTO> list = new HashMap<String, BookDTO>();

                    String sql2 = "SELECT bookID, amount FROM tblOrderDetail WHERE orderID=?";
                    PreparedStatement stm2 = conn.prepareStatement(sql2);
                    stm2.setString(1, orderID);
                    ResultSet rs2 = stm2.executeQuery();
                    BookDAO dao = new BookDAO();
                    while (rs2.next()) {
                        BookDTO book = dao.getBook(rs2.getNString("bookID"));
                        book.setQuantity(rs2.getInt("amount"));
                        list.put(rs2.getNString("bookID"), book);
                    }
                    CartDTO cart = new CartDTO(list, orderID, userID, rentDate, returnDate);
                    result.put(orderID, cart);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
