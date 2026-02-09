/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Abstract base class for all user types in the CampusAssist system.
 * Defines shared fields and forces subclasses to implement login behavior.
 *
 * Used by:
 * - Student (Group A)
 * - Admin (Group B)
 *
 * Author: Witley
 */
public abstract class User {

    protected String username;  // Unique user login
    protected String password;  // Password (stored in plain text for simplicity)
    protected String name;      // Full display name

    /**
     * Constructor for any User.
     * Sets common identity properties used in both Admin and Student classes.
     */
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    /**
     * Gets the username (used for login matching).
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the full display name (used in menus, summaries, etc.).
     */
    public String getName() {
        return name;
    }

    /**
     * Forces subclasses to implement their own login logic.
     *
     * @param username input
     * @param password input
     * @return true if credentials match
     */
    public abstract boolean login(String username, String password);
}
