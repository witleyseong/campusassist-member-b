package com.mycompany.campusassistapp.CampusAssistAppMemberC;

/**
 * FAQ Manager class
 * Now includes adminManageFAQs(Scanner scanner) to allow Admin to manage FAQs.
 *
 * Author: Marcos Vinicius (updated with integration support)
 */

import java.util.*;

public class FAQManager {
    private List<FAQ> faqs = new ArrayList<>();
    private int nextId = 1;

    public void addFAQ(String question, String answer) {
        faqs.add(new FAQ(nextId++, question, answer));
    }

    public void updateFAQ(int id, String newQuestion, String newAnswer) {
        for (FAQ faq : faqs) {
            if (faq.getId() == id) {
                faq.setQuestion(newQuestion);
                faq.setAnswer(newAnswer);
                return;
            }
        }
        System.out.println("FAQ not found.");
    }

    public void deleteFAQ(int id) {
        faqs.removeIf(faq -> faq.getId() == id);
    }

    public void displayFAQs() {
        if (faqs.isEmpty()) {
            System.out.println("No FAQs available.");
        } else {
            for (FAQ faq : faqs) {
                System.out.println(faq);
            }
        }
    }

    // New method for Admin to manage FAQs
    public void adminManageFAQs(Scanner scanner) {
        while (true) {
            System.out.println("\n=== FAQ Management ===");
            System.out.println("1. View All FAQs");
            System.out.println("2. Add FAQ");
            System.out.println("3. Update FAQ");
            System.out.println("4. Delete FAQ");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    displayFAQs();
                }
                case "2" -> {
                    System.out.print("Enter Question: ");
                    String question = scanner.nextLine();
                    System.out.print("Enter Answer: ");
                    String answer = scanner.nextLine();
                    addFAQ(question, answer);
                    System.out.println("✅ FAQ added successfully.");
                }
                case "3" -> {
                    displayFAQs();
                    System.out.print("Enter FAQ ID to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter New Question: ");
                    String newQuestion = scanner.nextLine();
                    System.out.print("Enter New Answer: ");
                    String newAnswer = scanner.nextLine();
                    updateFAQ(id, newQuestion, newAnswer);
                    System.out.println("✅ FAQ updated successfully.");
                }
                case "4" -> {
                    displayFAQs();
                    System.out.print("Enter FAQ ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    deleteFAQ(id);
                    System.out.println("✅ FAQ deleted successfully.");
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("❌ Invalid input. Try again.");
            }
        }
    }
}
