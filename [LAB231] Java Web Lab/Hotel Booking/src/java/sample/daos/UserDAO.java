/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    private Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name, phone, address, createDate, roleID FROM tblUser WHERE userID = ? AND password = ? AND statusID = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int roleID = rs.getInt("roleID");

                    user = new UserDTO(userID, name, password, phone, address, name, 1, roleID);
                }
            }
        } catch (Exception e) {
            logger.error("CheckLogin: " + e);
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
        return user;
    }

    public UserDTO checkUserID(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name, phone, address, createDate, roleID FROM tblUser WHERE userID = ? AND statusID = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int roleID = rs.getInt("roleID");

                    user = new UserDTO(userID, name, "", phone, address, name, 1, roleID);
                }
            }
        } catch (Exception e) {
            logger.error("CheckUserID: " + e);
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
        return user;
    }

    public void addNew(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUser(userID, name, password, phone, address, createDate, statusID, roleID)"
                        + " VALUES(?, ?, ?, ?, ?, CAST(GETDATE() AS DATE), 1, 2)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getName());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getPhone());
                stm.setString(5, user.getAddress());

                stm.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("addNew: " + e);
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
    }

    public void updatePassword(String email, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblUser SET password = ? WHERE userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, email);

                stm.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("updatePassword(): " + e);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> getAllUser() throws SQLException {
        List<String> list = new ArrayList<String>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.createStatement();
                String sql = "SELECT userID FROM tblUser";
                
                rs = stm.executeQuery(sql);
                while(rs.next()){
                    String userID = rs.getString("userID");
                    list.add(userID);
                }
            }
        } catch (Exception e) {
            logger.error("getAllUser(): " + e);
        } finally {
            if(rs != null){
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
    
    public boolean checkEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT name FROM tblUser WHERE statusID = 1 AND userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            logger.error("checkEmail: " + e);
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
        return check;
    }
}
