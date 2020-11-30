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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.CartDTO;
import sample.dtos.RoomDTO;
import sample.dtos.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class CartDAO {

    private Logger logger = Logger.getLogger(CartDAO.class.getName());

    public void addOrder(CartDTO cart, UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblOrder(userID, arrriveDate, returnDate, total, discountID, finalTotal, statusID) VALUES(?,?,?,?,?,?,3)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setDate(2, cart.getArriveDate());
                stm.setDate(3, cart.getReturnDate());
                stm.setDouble(4, cart.getTotal());
                stm.setString(5, cart.getDiscountID());
                stm.setDouble(6, cart.getFinalTotal());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("addOrder(): " + e);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getOrderID() throws SQLException {
        String id = "";
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.createStatement();
                String sql = "SELECT TOP 1 orderID FROM tblOrder ORDER BY bookingDate DESC";
                rs = stm.executeQuery(sql);
                if (rs.next()) {
                    id = rs.getString("orderID");
                }
            }
        } catch (Exception e) {
            logger.error("getOrderID(): " + e);
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
        return id;
    }

    public List<CartDTO> getHistory(String userID) throws SQLException {
        List<CartDTO> listCart = new ArrayList<CartDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID, discountID, arrriveDate, returnDate, finalTotal, statusID, bookingDate\n"
                        + "FROM tblOrder WHERE userID LIKE ? AND (statusID = 4 OR statusID = 5 OR statusID = 7) ORDER BY orderID DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    Date arriveDate = rs.getDate("arrriveDate");
                    Date returnDate = rs.getDate("returnDate");
                    String discountCode = rs.getString("discountID");
                    double finalTotal = rs.getDouble("finalTotal");
                    int statusID = rs.getInt("statusID");
                    Date bookingDate = rs.getDate("bookingDate");
                    HashMap<String, RoomDTO> cart = new HashMap<String, RoomDTO>();
                    CartDTO cartDTO = new CartDTO(orderID, userID, cart, 0, finalTotal, discountCode, arriveDate, returnDate, statusID, bookingDate);
                    listCart.add(cartDTO);
                }

                for (CartDTO cartDTO : listCart) {
                    sql = "SELECT OD.roomID, H.name, C.name, OD.amount, OD.price\n"
                            + "FROM tblHotel H, tblCategory C, tblRoom R, tblOrderDetail OD\n"
                            + "WHERE H.hotelID = R.hotelID AND C.categoryID = R.categoryID AND  OD.roomID = R.ID\n"
                            + "AND OD.orderID = ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, cartDTO.getId());
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String roomID = rs.getString("roomID");
                        String hotelName = rs.getString(1);
                        String categoryName = rs.getString(2);
                        int amount = rs.getInt("amount");
                        double price = Double.parseDouble(new DecimalFormat("###########.##").format(rs.getDouble("price")));
                        cartDTO.getCart().put(roomID, new RoomDTO(roomID, hotelName, categoryName, price, amount));
                    }
                }

            }
        } catch (Exception e) {
            logger.error("getHistory(): " + e);
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
        return listCart;
    }

    public void updateStatus(String cartID, int statusID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblOrder SET statusID = ? WHERE orderID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, statusID);
                stm.setString(2, cartID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("updaetStatus(): " + e);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> searchHistory(String id, Date date, String userID) throws SQLException {
        List<String> listID = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "";
                if (id.isEmpty()) {
                    sql = "SELECT orderID\n"
                            + "FROM tblOrder WHERE CAST(bookingDate AS DATE) = ? AND userID = ? AND (statusID = 4 OR statusID = 5 OR statusId = 7)";
                    stm = conn.prepareStatement(sql);
                    stm.setDate(1, date);
                    stm.setString(2, userID);
                }
                if (date == null) {
                    sql = "SELECT orderID\n"
                            + "FROM tblOrder WHERE orderID LIKE ? AND userID = ? AND (statusID = 4 OR statusID = 5 OR statusId = 7)";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, id);
                    stm.setString(2, userID);
                }
                if (!id.isEmpty() && date != null) {
                    sql = "SELECT orderID\n"
                            + "FROM tblOrder WHERE CAST(bookingDate AS DATE) = CAST(? AS DATE) AND orderID LIKE ? AND userID = ? AND (statusID = 4 OR statusID = 5 OR statusId = 7)";
                    stm = conn.prepareStatement(sql);
                    stm.setDate(1, date);
                    stm.setString(2, id);
                    stm.setString(3, userID);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    listID.add(orderID);
                }
            }
        } catch (Exception e) {
            logger.error("searchHistory(): " + e);
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
        return listID;
    }

}
