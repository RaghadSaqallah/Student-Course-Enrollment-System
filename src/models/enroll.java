/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

/**
 *
 * @author AL
 */
public class enroll {
    
    private int enroll_id;
    private int student_id;
    private int course_id;
    private String enrollDate;

    public enroll(int student_id, int course_id, String enrollDate) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.enrollDate = enrollDate;
    }
    
    
    

    public enroll() {
    }

    public enroll(int enroll_id, int student_id, int course_id, String enrollDate) {
        this.enroll_id = enroll_id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.enrollDate = enrollDate;
    }

    
    
    
    
    public int getEnroll_id() {
        return enroll_id;
    }

    public void setEnroll_id(int enroll_id) {
        this.enroll_id = enroll_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Override
    public String toString() {
        return "enroll{" + "enroll_id=" + enroll_id + ", student_id=" + student_id + ", course_id=" + course_id + ", enrollDate=" + enrollDate + '}';
    }
    
    

}
