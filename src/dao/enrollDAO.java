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
import models.enroll;
import java.sql.PreparedStatement;

/**
 *
 * @author Raghad Saqallah
 */
public class enrollDAO {

    public List<enroll> findAll() {  // return all records
        List<enroll> list = new ArrayList();
        try {
            String sql = "SELECT * FROM enroll";
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                Integer enrollID = rs.getInt("enroll_id");
                Integer studentID = rs.getInt("student_id");
                Integer courseID = rs.getInt("course_id");
                String enrollDate = rs.getString("enroll_date");

                enroll e = new enroll(enrollID, studentID, courseID, enrollDate);
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(studentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertOne(enroll o) { // insert a record
        String sql = "INSERT INTO enroll (student_id, course_id, enroll_date) VALUES (?, ?, ?)";

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getStudent_id());
            ps.setInt(2, o.getCourse_id());
            ps.setString(3, o.getEnrollDate());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(enrollDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean update(enroll o) { // update a record
        String sql = "UPDATE enroll SET student_id = ?, course_id = ?, enroll_date = ? WHERE enroll_id = ?";

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, o.getStudent_id());
            ps.setInt(2, o.getCourse_id());
            ps.setString(3, o.getEnrollDate());
            ps.setInt(4, o.getEnroll_id());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(enrollDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteRecorde(enroll o) {  // delete a record
        String sql = "DELETE FROM enroll WHERE enroll_id=?";
        Connection conn;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getEnroll_id());
            int noOfRows = ps.executeUpdate();
            return noOfRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(enrollDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean isEnrolled(int studentID, int courseID) { // is the recorde already exist
    String sql = "SELECT COUNT(*) FROM enroll WHERE student_id = ? AND course_id = ?";
    try {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentID);
        ps.setInt(2, courseID);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; 
        }
    } catch (SQLException ex) {
        Logger.getLogger(enrollDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

}
