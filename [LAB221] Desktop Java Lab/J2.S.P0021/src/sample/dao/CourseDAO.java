/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sample.dto.CourseDTO;
import sample.utils.DBAccess;

/**
 *
 * @author Admin
 */
public class CourseDAO {
    DBAccess dbObj;

    public CourseDAO() {
        dbObj=new DBAccess();
    }
    
    public void addCourse(CourseDTO course){
        String msg;
        String sql="INSERT INTO Courses VALUES('"+course.getCode()+"','"+course.getName()+"',"+course.getCredit()+")";
           int result=dbObj.exexuteUpdate(sql);
           if (result>0)
               msg="Add successfully!";
           else
               msg="Failed!";
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public CourseDTO searchCourse(String code){
         String sql="SELECT Name, Credit FROM Courses WHERE Code='"+code+"'";
            ResultSet rs=dbObj.executeQuery(sql);
                try {
                    while (rs.next()){
                    String name=rs.getNString("Name");
                    int credit=rs.getInt("Credit");
                    return new CourseDTO(code, name, credit);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        return null;
    }
    
    public List<CourseDTO> getList() throws SQLException{
        List<CourseDTO> list=new ArrayList<CourseDTO>();
        String sql="SELECT * FROM Courses";
        ResultSet rs=dbObj.executeQuery(sql);
        rs.close();
        while(rs.next()){
            String code=rs.getString("Code");
            String name=rs.getNString("Name");
            int credit=rs.getInt("Credit");
            list.add(new CourseDTO(code, name, credit));
        }
        
        return list;
    }
}
