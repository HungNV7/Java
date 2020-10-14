/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sample.Utils.DBAccess;
import sample.tablemodel.StockTableModel;

/**
 *
 * @author Admin
 */
public class StockDAO {
     public void getDataFromDB(StockTableModel model){
    }
     
     public void delete(){
        Connection conn=null;
        Statement stm=null;
        try {
            conn=DBAccess.getConnect();
            stm=conn.createStatement();
            String sql="DELETE FROM Stock";
            stm.executeUpdate(sql);
            
             if(conn!=null) conn.close();
            if(stm!=null) stm.close();
        } catch (Exception e) {
            System.out.println(e);
        }
     }
     
     public void insertToDB(StockTableModel model){
         delete();
        Connection conn=null;
        Statement stm=null;
        try{
            conn=DBAccess.getConnect();
            stm=conn.createStatement();
            conn.setAutoCommit(false);
            for(int i=0; i<model.getRowCount(); i++){
                int id=Integer.parseInt((String)model.getValueAt(i, 0));
                String name=(String)model.getValueAt(i, 1);
                name=name.equals("")?"NULL": "'"+name+"'";
                String address=(String)model.getValueAt(i, 2);
                address=address.equals("")?"NULL": "'"+address+"'";
                String date=(String)model.getValueAt(i, 3);
                date=date.equals("")?"NULL": "'"+date+"'";
                String note=(String)model.getValueAt(i, 4);
                    note=note.equals("")?"NULL": "'"+note+"'";
                
                String sql="INSERT INTO Stock VALUES ("+id+","+name+","+address+", CONVERT(DATE, "+date+", 103), "+note+")";
                stm.addBatch(sql);
            }
            stm.executeBatch();     
            conn.commit();
            JOptionPane.showMessageDialog(null, "Insert successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed!");
            System.out.println(e);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }  
     }
}
