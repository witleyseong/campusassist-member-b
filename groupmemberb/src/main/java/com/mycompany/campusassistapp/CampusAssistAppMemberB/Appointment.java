/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberA;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an appointment booked by a student for a specific support category.
 * This class is used by both:
 * - Group Member A (for students to create/manage appointments)
 * - Group Member B (for admins to view/reschedule/cancel/approve appointments)
 *
 * Author: Witley
 */
public class Appointment {

    private static int counter = 1; // Used to auto-generate unique appointment IDs

    private final int id; // Unique appointment ID
    private final Student student; // Student who booked the appointment
    private final SupportCategory category; // Type of support (Mental Health, etc.)
    private LocalDateTime dateTime; // Date and time of the session
    private String status; // Status: Pending, Approved, Cancelled, Closed

    /**
     * Constructor for creating a new appointment.
     * Default status is "Pending" until approved by admin.
     */
    public Appointment(Student student, SupportCategory category, LocalDateTime dateTime) {
        this.id = counter++;
        this.student = student;
        this.category = category;
        this.dateTime = dateTime;
        this.status = "Pending"; // Default state
    }

    // === Getters and Setters ===

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public SupportCategory getCategory() {
        return category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Allows rescheduling the appointment by updating the date/time.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Updates the appointment status (e.g., Approved, Cancelled).
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Checks if the appointment is editable.
     * Admin can only reschedule/cancel if not already closed or cancelled.
     */
    public boolean isEditable() {
        return !status.equalsIgnoreCase("Closed") && !status.equalsIgnoreCase("Cancelled");
    }

    /**
     * Custom string representation of the appointment for display in the console.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "ID: " + id +
                ", Student: " + student.getName() +
                ", Category: " + category +
                ", Date: " + dateTime.format(formatter) +
                ", Status: " + status;
    }
}
