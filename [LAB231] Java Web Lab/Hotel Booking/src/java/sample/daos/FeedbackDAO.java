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
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.FeedbackDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class FeedbackDAO {

    private Logger logger = Logger.getLogger(FeedbackDAO.class.getName());

    public void insert(FeedbackDTO feedback) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblFeedback(roomID, feedback, star, userID) VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, feedback.getRoom());
                stm.setString(2, feedback.getFeedback());
                stm.setInt(3, feedback.getStar());
                stm.setString(4, feedback.getUserName());

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

    public List<FeedbackDTO> getFeedback(String hotelID) throws SQLException {
        List<FeedbackDTO> list = new ArrayList<FeedbackDTO>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT feedbackID, feedback, roomID, star, U.name FROM tblFeedback F, tblUser U\n"
                        + "WHERE F.userID = U.userID AND roomID IN (\n"
                        + "SELECT ID FROM tblRoom\n"
                        + "WHERE hotelID = ?\n"
                        + ")";
                stm = conn.prepareStatement(sql);
                stm.setString(1, hotelID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString("feedbackID");
                    String detail = rs.getString("feedback");
                    String roomID = rs.getString("roomID");
                    int star = rs.getInt("star");
                    String userName = rs.getString("name");
                    list.add(new FeedbackDTO(id, detail, roomID, userName, star));
                }
            }
        } catch (Exception e) {
            logger.error("getFeedback: " + e);
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
}
