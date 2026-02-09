/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Handles the text-based admin interface.
 * Displays options to manage appointments, view feedback, FAQs, and analytics.
 *
 * Author: Witley
 */

import com.mycompany.campusassistapp.CampusAssistAppMemberC.FAQManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberC.Analytics;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.FeedbackManager;

import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the Admin menu after successful login.
     * Allows management of appointments, feedback, FAQs, and analytics.
     *
     * ️ Integration Note:
     * This method had to be updated to support features developed by Group Member C.
     * It now includes options to manage FAQs and view analytics, which were previously unavailable in Member B.
     */
    public void adminMenu(Admin admin, AppointmentManager appointmentManager, FeedbackManager feedbackManager, FAQManager faqManager, Analytics analytics) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Appointments");
            System.out.println("2. Approve Appointment");
            System.out.println("3. Reschedule Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View Feedback");
            System.out.println("6. Manage FAQs");     // Added for Group Member C
            System.out.println("7. View Analytics"); // Added for Group Member C
            System.out.println("0. Logout");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the input buffer

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAll Appointments:");
                    appointmentManager.viewAllAppointments();
                }
                case 2 -> {
                    System.out.println("\nAvailable Appointments for Approval:");
                    appointmentManager.viewAllAppointments();
                    System.out.print("Enter Appointment ID to Approve: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    appointmentManager.updateStatus(id, "Approved");
                }
                case 3 -> {
                    System.out.println("\nAvailable Appointments for Rescheduling:");
                    appointmentManager.viewAllAppointments();
                    System.out.print("Enter Appointment ID to Reschedule: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    appointmentManager.reschedule(id, scanner);
                }
                case 4 -> {
                    System.out.println("\nAvailable Appointments for Cancellation:");
                    appointmentManager.viewAllAppointments();
                    System.out.print("Enter Appointment ID to Cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    appointmentManager.updateStatus(id, "Cancelled");
                }
                case 5 -> {
                    System.out.println("\nStudent Feedback:");
                    feedbackManager.viewFeedbackSummary();
                }
                case 6 -> {
                    // Opens the FAQ management interface (integrated from Group C)
                    manageFAQs(faqManager);
                }
                case 7 -> {
                    // Opens the analytics summary interface (integrated from Group C)
                    viewAnalytics(analytics);
                }
                case 0 -> {
                    System.out.println("Logging out from Admin...");
                    return; // Exit back to main menu
                }
                default -> System.out.println("❌ Invalid option. Please choose a number from 0 to 7.");
            }
        }
    }

    /**
     * Opens the FAQ management tool.
     * Allows admin to view, add, edit, or delete FAQs.
     */
    private void manageFAQs(FAQManager faqManager) {
        System.out.println("\n=== FAQ Management ===");
        faqManager.adminManageFAQs(scanner);
    }

    /**
     * Opens the analytics summary view.
     * Displays data trends related to appointments and feedback.
     */
    private void viewAnalytics(Analytics analytics) {
        System.out.println("\n=== Appointment and Feedback Analytics ===");
        analytics.showAnalytics();
    }
}
