/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AL
 */
public class coursesDAO {
    
        // return list of the courses ids to put in the comboBox

     public List<Integer> getCourseID(){
    
         List<Integer> list = new ArrayList();
        try {
            String sql = "SELECT course_id FROM courses";
            Connection conn= DBConnection.getInstance().getConnection();
            Statement stat =conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
              Integer courseId=   rs.getInt("course_id");
              list.add(courseId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
