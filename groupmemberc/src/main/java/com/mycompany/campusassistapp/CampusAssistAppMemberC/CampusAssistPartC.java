package com.mycompany.campusassistapp.CampusAssistAppMemberC;

/**
 * Main class for Part C - Admin-side Analytics and FAQ management. Now updated
 * to match Group Member A and B integration.
 *
 * Author: Marcos Vinicius
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class CampusAssistPartC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize FAQ Manager
        FAQManager faqManager = new FAQManager();
        faqManager.addFAQ("How do I book a session?", "Use the support request option in the main menu.");
        faqManager.addFAQ("Can I cancel an appointment?", "Yes, please contact support staff at least 24 hours in advance.");

        // Simulated Appointment and Feedback Lists
        List<Appointment> appointmentList = new ArrayList<>();
        List<Feedback> feedbackList = new ArrayList<>();

        // Add dummy appointment data
        appointmentList.add(new Appointment(LocalDateTime.now().minusDays(2), SupportCategory.MENTAL_HEALTH));
        appointmentList.add(new Appointment(LocalDateTime.now().minusDays(1), SupportCategory.ACADEMIC_SUPPORT));
        appointmentList.add(new Appointment(LocalDateTime.now(), SupportCategory.MENTAL_HEALTH));

        // Add dummy feedback data
        feedbackList.add(new Feedback(5));
        feedbackList.add(new Feedback(4));
        feedbackList.add(new Feedback(2));

        // Create Analytics object with the dummy data
        Analytics analytics = new Analytics(appointmentList, feedbackList);

        int choice;
        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View FAQs");
            System.out.println("2. Add FAQ");
            System.out.println("3. Update FAQ");
            System.out.println("4. Remove FAQ");
            System.out.println("5. View Appointment Analytics");
            System.out.println("6. View Feedback Analytics");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 ->
                    faqManager.displayFAQs();
                case 2 -> {
                    System.out.print("Enter the question: ");
                    String q = scanner.nextLine();
                    System.out.print("Enter the answer: ");
                    String a = scanner.nextLine();
                    faqManager.addFAQ(q, a);
                }
                case 3 -> {
                    System.out.print("Enter FAQ ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new question: ");
                    String newQ = scanner.nextLine();
                    System.out.print("Enter new answer: ");
                    String newA = scanner.nextLine();
                    faqManager.updateFAQ(updateId, newQ, newA);
                }
                case 4 -> {
                    System.out.print("Enter FAQ ID to remove: ");
                    int deleteId = scanner.nextInt();
                    faqManager.deleteFAQ(deleteId);
                }
                case 5 ->
                    analytics.showAnalytics();
                case 6 ->
                    analytics.showAnalytics();
                case 7 ->
                    System.out.println("Exiting...");
                default ->
                    System.out.println("Invalid option.");
            }
        } while (choice != 7);
    }
}
