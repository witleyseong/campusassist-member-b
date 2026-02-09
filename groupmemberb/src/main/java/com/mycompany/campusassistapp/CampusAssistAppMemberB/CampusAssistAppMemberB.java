/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Main class for the Admin side of CampusAssist (Group Member B).
 * Responsible for handling login, admin menu, FAQ editing, and viewing analytics.
 *
 * Author: Witley
 */

import com.mycompany.campusassistapp.CampusAssistAppMemberA.SessionManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.Appointment;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.SupportCategory;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.Student;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.FeedbackManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberC.FAQManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberC.Analytics;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CampusAssistAppMemberB {

    // === Core components used by the admin interface ===
    private final AppointmentManager appointmentManager;
    private final FeedbackManager feedbackManager;
    private final FAQManager faqManager;
    private final Analytics analytics;
    private final ConsoleUI consoleUI;
    private final User[] users;

    /**
     * Constructor for Admin application.
     * Receives managers shared between parts A, B, and C.
     *
     *  Integration Note:
     * One challenge was combining separate modules from A and C while preserving modularity.
     * This constructor centralizes all shared managers so they can be reused in the admin menu.
     */
    public CampusAssistAppMemberB(AppointmentManager appointmentManager, FeedbackManager feedbackManager, FAQManager faqManager, Analytics analytics) {
        this.appointmentManager = appointmentManager;
        this.feedbackManager = feedbackManager;
        this.faqManager = faqManager;
        this.analytics = analytics;
        this.consoleUI = new ConsoleUI();

        // Create a default admin account for demo purposes
        Admin admin = new Admin("admin1", "adminpass", "Mr. Smith");
        this.users = new User[]{admin};
    }

    /**
     * Starts the Admin panel.
     * Handles login, and then launches the admin menu if login is successful.
     */
    public void startAdminApp(SessionManager sessionManager, FeedbackManager feedbackManager) {
        Scanner sc = new Scanner(System.in);
        User loggedIn = null;

        // Loop until a valid admin logs in
        while (loggedIn == null) {
            System.out.println("\nWelcome to CampusAssist Admin Panel");
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            // Verify credentials using shared LoginManager
            User user = LoginManager.login(username, password, users);

            // Check user type
            if (user instanceof Admin adminUser) {
                loggedIn = adminUser;

                // Launch Admin Menu
                consoleUI.adminMenu(adminUser, appointmentManager, feedbackManager, faqManager, analytics);
            } else {
                System.out.println("❌ Invalid login. Try again.");
            }
        }
    }
}
