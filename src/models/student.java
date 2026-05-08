/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

/**
 *
 * @author Raghad Saqallah
 */
public class student {
  private int student_id;
    private String name;
    private String major;

    public student() {
    }

    public student(int student_id, String name, String major) {
        this.student_id = student_id;
        this.name = name;
        this.major = major;
    }
    
    
    
    

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "studentDAO{" + "student_id=" + student_id + ", name=" + name + ", major=" + major + '}';
    }
}
