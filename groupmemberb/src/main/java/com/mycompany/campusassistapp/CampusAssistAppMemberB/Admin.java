/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Represents the Admin user in the CampusAssist system.
 * Admins can manage appointments, feedback, FAQs, and view analytics.
 *
 * Author: Witley
 */
public class Admin extends User {

    /**
     * Constructor to create an Admin user with login credentials and name.
     * @param username Admin's username (e.g., "admin1")
     * @param password Admin's password (e.g., "adminpass")
     * @param name Admin's display name
     */
    public Admin(String username, String password, String name) {
        super(username, password, name); // Inherits from abstract User class
    }

    /**
     * Authenticates the admin using provided credentials.
     * Called by the login system to verify if this is the correct user.
     *
     * @param username input username
     * @param password input password
     * @return true if credentials match; false otherwise
     */
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Gets the admin's display name.
     * Used when showing welcome messages or logs.
     *
     * @return name of the admin
     */
    public String getDisplayName() {
        return this.name;
    }
}
