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
import org.apache.log4j.Logger;
import sample.dtos.DiscountDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class DiscountDAO {

    private Logger logger = Logger.getLogger(DiscountDAO.class.getName());

    public DiscountDTO checkDiscountCode(String discountCode) throws SQLException {
        DiscountDTO discount = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT discountName, percentage, expirateDate, createDate FROM tblDiscount WHERE discountID = ? AND expirateDate > CAST(GETDATE() AS DATE)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, discountCode);
                
                rs = stm.executeQuery();
                if(rs.next()){
                    String name = rs.getString("discountName");
                    int percentage = rs.getInt("percentage");
                    Date expirateDate = rs.getDate("expirateDate");
                    Date createDate = rs.getDate("createDate");
                    
                    discount = new DiscountDTO(discountCode, name, percentage, createDate, expirateDate);
                }
            }
        } catch (Exception e) {
            logger.error("checkDiscountCode(): " + e);
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
        return discount;
    }
    
    public boolean checkCode(String discountCode) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT discountName FROM tblDiscount WHERE discountID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, discountCode);
                
                rs = stm.executeQuery();
                if(rs.next()){
                    check = false;
                }
            }
        } catch (Exception e) {
            logger.error("checkCode(): " + e);
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
    
    public void insert(DiscountDTO discount) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblDiscount(discountID, discountName, percentage, expirateDate) VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, discount.getId());
                stm.setString(2, discount.getName());
                stm.setInt(3, discount.getPercentage());
                stm.setDate(4, discount.getExpireDate());
                 
                stm.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("insert(): " + e);
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
