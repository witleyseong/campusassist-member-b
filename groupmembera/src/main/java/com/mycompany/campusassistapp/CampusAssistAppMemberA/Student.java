/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberA;

/**
 * Represents a student using the CampusAssist system.
 * Each student has a unique ID, a name, and a password.
 * The password is used during login for basic authentication.
 * 
 * Author: jhenifer
 */
public class Student {
    private String studentId;
    private String name;
    private String password;

    public Student(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
    }
// Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
}


