/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.CartDTO;
import sample.dtos.RoomDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class RoomDAO {

    private Logger logger = Logger.getLogger(RoomDAO.class.getName());

    public List<RoomDTO> getListRoom(String hotelID) throws SQLException {
        List<RoomDTO> list = new ArrayList<RoomDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT ID, H.name, C.name, price\n"
                        + "FROM tblRoom R, tblHotel H, tblCategory C\n"
                        + "WHERE R.hotelID = H.hotelID AND R.categoryID = C.categoryID AND R.hotelID = ? ORDER BY price ASC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, hotelID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("ID");
                    double price = rs.getFloat("price");
                    String categoryName = rs.getString(3);
                    String hotelName = rs.getString(2);
                    list.add(new RoomDTO(id, hotelName, categoryName, price));
                }
            }
        } catch (Exception e) {
            logger.error("getList: " + e);
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
        return list;
    }

    public List<String> checkValid(CartDTO cart) throws SQLException {
        List<String> list = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement stm = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                cstm = conn.prepareCall("{call findAvailableRoom(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                cstm.setDate("Date", cart.getArriveDate());
                 cstm.execute();
                 
                for (String id : cart.getCart().keySet()) {
                    String sql = "SELECT quantity - booking AS remain FROM tblRoom\n"
                            + "WHERE ID = ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, id);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        int remain = rs.getInt("remain");
                        String hotelName = cart.getCart().get(id).getHotelName();
                        String categoryName = cart.getCart().get(id).getCategoryName();
                        if (remain == 0) {
                            list.add(hotelName + " - " + categoryName + " has no room!");
                        } else {
                            if (remain < cart.getCart().get(id).getQuantity()) {
                                list.add(hotelName + " - " + categoryName + " has " + remain + " only!");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("checkValid: " + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cstm != null) {
                cstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public void addOrderDetail(CartDTO cart) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                for (RoomDTO room : cart.getCart().values()) {
                    String sql = "INSERT INTO tblOrderDetail(roomID, orderID, amount, price) VALUES(?,?,?,?)";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, room.getId());
                    stm.setString(2, cart.getId());
                    stm.setInt(3, room.getQuantity());
                    stm.setFloat(4, (float) room.getPrice());
                    
                    stm.executeUpdate();
                }
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
}
