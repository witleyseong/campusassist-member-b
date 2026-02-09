/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Handles basic login functionality for CampusAssist.
 * Works for both Admin and Student user types, using polymorphism.
 *
 * Author: Witley
 */
public class LoginManager {

    /**
     * Verifies the login credentials against a list of available users.
     *
     * @param username input username
     * @param password input password
     * @param users array of known users (can be Admins or Students)
     * @return matching User object if found; otherwise, returns null
     */
    public static User login(String username, String password, User[] users) {
        for (User user : users) {
            // Calls the login() method defined in each subclass (Admin, Student, etc.)
            if (user.login(username, password)) {
                return user; // Login successful
            }
        }
        return null; // No match found — login failed
    }
}
