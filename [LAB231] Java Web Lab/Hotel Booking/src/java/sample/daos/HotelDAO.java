/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.HotelDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class HotelDAO {

    private Logger logger = Logger.getLogger(HotelDAO.class.getName());

    public List<HotelDTO> getList() throws SQLException {
        List<HotelDTO> list = new ArrayList<HotelDTO>();
        Connection conn = null;
        Statement stm = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                cstm = conn.prepareCall("{call findAvailableRoom(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                long currentTime = System.currentTimeMillis();
                Date date = new Date(currentTime);
                cstm.setDate("Date", date);
                 cstm.execute();
                
                stm = conn.createStatement();
                String sql = "SELECT hotelID, name, address, phone, image\n"
                        + "FROM tblHotel\n"
                        + "WHERE hotelID IN (\n"
                        + "SELECT hotelID FROM tblRoom\n"
                        + "GROUP BY hotelID\n"
                        + "HAVING SUM(quantity) > SUM (booking)\n"
                        + ")";
                rs = stm.executeQuery(sql);

                while (rs.next()) {
                    String id = rs.getString("hotelID");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String image = rs.getString("image");
                    list.add(new HotelDTO(id, name, address, phone, image));
                }
            }
        } catch (Exception e) {
            logger.error("getList(): " + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if(cstm != null){
                cstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<HotelDTO> getListSearch(String nameOrArea, Date arriveDate, int rooms) throws SQLException {
        List<HotelDTO> list = new ArrayList<HotelDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                cstm = conn.prepareCall("{call findAvailableRoom(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                cstm.setDate("Date", arriveDate);
               cstm.execute();

                String sql = "SELECT hotelID, name, address, phone, image\n"
                        + "FROM tblHotel\n"
                        + "WHERE hotelID IN (\n"
                        + "SELECT hotelID FROM tblRoom\n"
                        + "GROUP BY hotelID\n"
                        + "HAVING SUM(quantity) >= SUM(booking) + ?\n"
                        + ") AND (name LIKE ? OR address LIKE ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, rooms);
                stm.setString(2, "%" + nameOrArea + "%");
                stm.setString(3, "%" + nameOrArea + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("hotelID");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String image = rs.getString("image");
                    list.add(new HotelDTO(id, name, address, phone, image));
                }
            }
        } catch (Exception e) {
            logger.error("getListSearch: " + e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if(cstm != null){
                cstm.close();
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
    
    public HotelDTO getHotel(String id) throws SQLException {
        HotelDTO hotel = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name, address, phone, image FROM tblHotel WHERE hotelID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String image = rs.getString("image");
                    hotel = new HotelDTO(id, name, address, phone, image);
                }
            }
        } catch (Exception e) {
            logger.error("getList(): " + e);
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
        return hotel;
    }
}
