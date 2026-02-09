/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberA;

/**
 * Represents feedback provided by a student after a completed appointment.
 * Includes a feedback message and a star rating (1-5).
 * 
 * Author: Jhenifer
 */
public class Feedback {
    private Appointment appointment;
    private String message;
    private int rating;

    public Feedback(Appointment appointment, String message, int rating) {
        this.appointment = appointment;
        this.message = message;
        this.rating = rating;
    }

    // Getters for feedback attributes
    public Appointment getAppointment() { return appointment; } //Getter to retrieve associated Appointment object
    public String getMessage() { return message; } //Getter for student's feedback message
    public int getRating() { return rating; } //Getter for star rating (1-5)
}



