/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import config.DBConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.enroll;

/**
 *
 * @author AL
 */
public class studentDAO {
    
    // return list of the students ids to put in the comboBox
    public List<Integer> getStudentID(){
    
         List<Integer> list = new ArrayList();
        try {
            String sql = "SELECT student_id FROM student";
            Connection conn= DBConnection.getInstance().getConnection();
            Statement stat =conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
              Integer studentId=   rs.getInt("student_id");
              list.add(studentId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    }
    
   

  
    
    
    

