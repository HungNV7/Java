/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import sample.daos.BookDAO;
import sample.daos.HistoryDAO;
import sample.dtos.BookDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Hung Nguyen
 */
public class HistoryDTO {

    private HashMap<String, CartDTO> history;

    public HistoryDTO() {
    }

    public void getHistory(String userID) {
        try {
            HistoryDAO dao = new HistoryDAO();
            history = dao.getHistory(userID);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    
     public void getOrderList() {
        try {
            HistoryDAO dao = new HistoryDAO();
            history = dao.getHistory();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public HashMap<String, CartDTO> getHistory() {
        return history;
    }

    public void setHistory(HashMap<String, CartDTO> history) {
        this.history = history;
    }
    

}
