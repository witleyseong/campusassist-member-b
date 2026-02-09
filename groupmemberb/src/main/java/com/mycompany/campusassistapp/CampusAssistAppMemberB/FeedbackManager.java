/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

import java.util.*;

/**
 * Manages a list of all feedback entries submitted by students.
 * Used exclusively by Group Member B (Admin side) to review feedback.
 *
 * Author: Witley
 */
public class FeedbackManager {

    private final List<Feedback> feedbackList = new ArrayList<>(); // Stores all feedback entries

    /**
     * Adds a new Feedback object to the list.
     * This would typically be called from the student-side once feedback is submitted.
     *
     * @param fb the feedback to add
     */
    public void addFeedback(Feedback fb) {
        feedbackList.add(fb);
    }

    /**
     * Displays a summary of all feedback entries.
     * Shows the student's name, rating, and comment.
     * Also calculates and prints the average rating.
     */
    public void viewFeedbackSummary() {
        int total = 0;
        int count = feedbackList.size();

        // Loop through each feedback entry and print details
        for (Feedback fb : feedbackList) {
            total += fb.getRating();
            System.out.println("Student: " + fb.getStudent().getName() +
                               " | Rating: " + fb.getRating() +
                               " | Comment: " + fb.getComments());
        }

        // Calculate and display average score
        double avg = count > 0 ? (double) total / count : 0;
        System.out.println("Average Rating: " + avg);
    }
}



