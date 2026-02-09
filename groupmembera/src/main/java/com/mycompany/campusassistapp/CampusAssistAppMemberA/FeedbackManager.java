package com.mycompany.campusassistapp.CampusAssistAppMemberA;

import java.util.*;
import java.util.stream.Collectors;

/**
 This class handles collecting feedback from students after they complete
 * their support appointments. It ensures feedback is not duplicated and allows
 * students to rate and review their sessions.
 *
 * Author: Jhenifer
 */
public class FeedbackManager {
    private final List<Feedback> feedbackList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public FeedbackManager() {}

    /**
     * Returns the list of all collected feedback.
     */
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    /**
     * Adds a new feedback entry to the system.
     */
    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    /**
     * Allows a student to leave feedback for completed appointments without prior feedback.
     */
    public void leaveFeedback(Student student, SessionManager sessionManager) {
        List<Appointment> pastAppointments = sessionManager.getAppointmentsForStudent(student).stream()
                .filter(a -> a.isCompleted() && !hasFeedback(a))
                .collect(Collectors.toList());

        if (pastAppointments.isEmpty()) {
            System.out.println("No completed appointments available for feedback.");
            return;
        }

        System.out.println("Select appointment to leave feedback:");
        for (int i = 0; i < pastAppointments.size(); i++) {
            System.out.println((i + 1) + ". " + pastAppointments.get(i));
        }

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (choice < 1 || choice > pastAppointments.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Appointment chosenAppointment = pastAppointments.get(choice - 1);

        System.out.println("Enter your feedback message:");
        String message = scanner.nextLine();
        if (message.trim().isEmpty()) {
            System.out.println("Feedback cannot be empty.");
            return;
        }

        int rating;
        do {
            System.out.println("Rate your session (1-5 stars):");
            try {
                rating = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                rating = 0;
            }
        } while (rating < 1 || rating > 5);

        feedbackList.add(new Feedback(chosenAppointment, message, rating));
        System.out.println("✅ Thank you! Feedback submitted.");
    }

    /**
     * Checks whether a feedback entry already exists for a given appointment.
     */
    private boolean hasFeedback(Appointment appointment) {
        return feedbackList.stream().anyMatch(f -> f.getAppointment().equals(appointment));
    }

    /**
     * Displays a summary of all feedback collected including average rating.
     */
    public void viewFeedbackSummary() {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedbacks submitted yet.");
            return;
        }

        double totalRating = 0;
        for (Feedback feedback : feedbackList) {
            totalRating += feedback.getRating();
        }

        double averageRating = totalRating / feedbackList.size();
        System.out.println("Average Rating: " + String.format("%.2f", averageRating));

        System.out.println("\nDetailed Feedback:");
        for (Feedback feedback : feedbackList) {
            System.out.println("- " + feedback.getMessage() + " (Rating: " + feedback.getRating() + ")");
        }
    }
}

