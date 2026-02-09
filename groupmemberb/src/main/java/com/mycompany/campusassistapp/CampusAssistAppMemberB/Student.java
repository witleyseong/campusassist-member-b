/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Represents a student user in the CampusAssist system.
 * Students log in with their credentials and can be tied to appointments and feedback.
 *
 * Author: Witley
 *
 * Used by:
 * - Group Member A: for student login and session booking.
 * - Group Member B: for referencing students in appointments and feedback entries.
 */
public class Student extends User {

    /**
     * Constructor to initialize a student user.
     *
     * @param username the student's login ID
     * @param password the student's password
     * @param name     the full name of the student
     */
    public Student(String username, String password, String name) {
        super(username, password, name); // Inherited from abstract User
    }

    /**
     * Implements student-specific login logic.
     * Matches input credentials with stored values.
     *
     * @param username input username
     * @param password input password
     * @return true if match is successful; otherwise false
     */
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns the name of the student (used in displays).
     */
    @Override
    public String getName() {
        return name;
    }
}
