package com.mycompany.campusassistapp.CampusAssistAppMemberA;

import java.util.*;
import com.mycompany.campusassistapp.CampusAssistAppMemberC.FAQManager;

/**
 * Main class representing the student-side application for CampusAssist.
 * Handles student login, appointment booking, feedback submission, and FAQ viewing.
 *
 * Updated to support external SessionManager, shared FeedbackManager,
 * and FAQManager for Group Member C integration.
 * 
 * Author: Jhenifer
 */

public class CampusAssistAppMemberA {
    private static final Scanner scanner = new Scanner(System.in);
    private final SessionManager sessionManager;
    private final ReminderService reminderService;
    private final FeedbackManager feedbackManager;
    private static final Map<String, Student> students = new HashMap<>();

    /**
     * Constructor accepts managers created externally
     */
    // INTEGRATION NOTE: ReminderService is built based on SessionManager
    public CampusAssistAppMemberA(SessionManager sessionManager, FeedbackManager feedbackManager) {
        this.sessionManager = sessionManager;
        this.reminderService = new ReminderService(sessionManager);
        this.feedbackManager = feedbackManager;
    }

    /**
     * Starts the application, including login and main menu navigation.
     * FAQManager is passed dynamically for modular integration.
     */
    // INTEGRATION NOTE: Added FAQManager (Member C's module) into startStudentApp
    public void startStudentApp(FAQManager faqManager) {
        initializeDummyAppointments(); // (Placeholder for real data)
        initializeDummyStudents();  // Load some hardcoded students

        System.out.println("\nWelcome to CampusAssist - Your Student Support Hub");
        System.out.println("--------------------------------------------------");
        System.out.println("Please log in to continue.\n");

        Student student = null;
        while (student == null) {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            Student s = students.get(studentId);
            if (s != null && s.getPassword().equals(password)) {
                student = s;
            } else {
                System.out.println("Invalid student ID or password. Please try again.\n");
            }
        }

        System.out.println("\nHello, " + student.getName() + "!");
        reminderService.displayReminders(student); // Show upcoming sessions

        // Main Menu loop
        boolean running = true;
        while (running) {
            System.out.println("\n===================== Main Menu =====================");
            System.out.println("1. Book Standard Support Session (Tomorrow)");
            System.out.println("2. View & Cancel My Appointments");
            System.out.println("3. Leave Feedback for Completed Sessions");
            System.out.println("4. Book Custom Appointment (Choose Date/Time)");
            System.out.println("5. View General FAQs"); // INTEGRATION: Added option to access Member C FAQ module
            System.out.println("6. Logout");
            System.out.println("=====================================================");
            System.out.print("Please select an option [1-6]: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {
                case 1 -> sessionManager.requestSession(student);
                case 2 -> sessionManager.viewAppointments(student);
                case 3 -> feedbackManager.leaveFeedback(student, sessionManager);
                case 4 -> sessionManager.bookCustomAppointment(student);
                case 5 -> viewFAQs(faqManager); // INTEGRATION: passing FAQManager dynamically
                case 6 -> {
                    running = false;
                    System.out.println("\nYou have been logged out successfully.");
                    System.out.println("Thank you for using CampusAssist. Have a great day!");
                }
                default -> System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }

    /**
     * Displays general FAQs using the provided FAQManager.
     */
    private void viewFAQs(FAQManager faqManager) {
        // INTEGRATION NOTE: FAQ feature uses functionality provided by Member C
        System.out.println("\n=== General FAQs ===");
        faqManager.displayFAQs();
    }

    /**
     * Retrieves a Student object based on student ID.
     */
    public static Student getStudent(String studentId) {
        return students.get(studentId);
    }

    // Placeholder for future dummy appointments if needed
    private void initializeDummyAppointments() {
    }

    /**
     * Pre-loads dummy student data for login purposes.
     */
    private void initializeDummyStudents() {
        students.put("1001", new Student("1001", "Maguinho do Piaui", "111"));
        students.put("1002", new Student("1002", "Xuxa dos Baixinhos", "222"));
        students.put("1003", new Student("1003", "Pablo do Arrocha", "333"));
    }
}
