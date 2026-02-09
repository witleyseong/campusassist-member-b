package com.mycompany.campusassistapp.CampusAssistAppMemberA;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Appointment class represents a scheduled one-on-one session
 * between a student and a specific support category (e.g., Mental Health, Academic Support).
 * It includes logic to check appointment status (upcoming, completed, editable).
 * 
 * Author: jhenifer
 */
public class Appointment {
    // Static counter to generate unique IDs for appointments
    private static int counter = 1;
    
    // Unique appointment ID
    private final int id;
    private final Student student;
    private final SupportCategory category;
    private LocalDateTime dateTime;
    private String status;

    // Constructor creates a new appointment and initializes fields
    public Appointment(Student student, SupportCategory category, LocalDateTime dateTime) {
        this.id = counter++;
        this.student = student;
        this.category = category;
        this.dateTime = dateTime.withMinute(0).withSecond(0).withNano(0); // Normalize minutes and seconds
        this.status = "Pending"; // Default status when created
    }

    // Getters for appointment attributes
    public int getId() { return id; }
    public Student getStudent() { return student; }
    public SupportCategory getCategory() { return category; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }

    /**
     * Setter to update the dateTime using a LocalDateTime object.
     * Used in appointment rescheduling.
     */
    public void setDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime.withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * Setter to update the dateTime using a formatted string.
     * Expected format: "dd-MM-yyyy HH:mm"
     */
    public void setDateTime(String newDateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(newDateTimeString, formatter);
    }

    public void setStatus(String status) { this.status = status; }

    /**
     * Checks if the appointment is editable (not closed or cancelled).
     */
    public boolean isEditable() {
        return !status.equalsIgnoreCase("Closed") && !status.equalsIgnoreCase("Cancelled");
    }

    /**
     * Checks if the appointment is scheduled for a future date/time.
     */
    public boolean isUpcoming() {
        return dateTime.isAfter(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0));
    }

    /**
     * Checks if the appointment is already completed (past date/time).
     */
    public boolean isCompleted() {
        return dateTime.isBefore(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0));
    }

    /**
     * Overrides equals to allow comparison between two Appointment objects.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Appointment)) return false;
        Appointment other = (Appointment) obj;
        return student.getStudentId().equals(other.student.getStudentId())
                && dateTime.equals(other.dateTime)
                && category == other.category;
    }

    /**
     * Overrides hashCode to maintain consistency with equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(student.getStudentId(), dateTime, category);
    }

    /**
     * Returns a user-friendly description of the appointment.
     */
    @Override
    public String toString() {
        return category + " at " + dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy 'at' hh:mm a"));
    }
}

