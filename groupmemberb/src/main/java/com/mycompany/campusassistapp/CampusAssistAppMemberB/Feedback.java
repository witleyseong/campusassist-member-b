/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Represents feedback provided by a student after attending a support session.
 * This class is used by Group Member B (Admin side) to view student opinions and ratings.
 *
 * Author: Witley
 */
public class Feedback {

    private final Student student; // The student who submitted the feedback
    private final int rating;      // Rating from 1 to 5
    private final String comments; // Optional written comments

    /**
     * Constructor to create a feedback entry.
     *
     * @param student        the student who gives the feedback
     * @param appointmentId  (currently unused, could be useful for linking in future)
     * @param rating         the rating score (1–5)
     * @param comments       any written feedback from the student
     */
    public Feedback(Student student, int appointmentId, int rating, String comments) {
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    /**
     * Gets the numeric rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the comment text provided by the student.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Gets the student who submitted the feedback.
     */
    public Student getStudent() {
        return student;
    }
}
