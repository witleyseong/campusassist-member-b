/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberA;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides reminder services for students about upcoming appointments within 24 hours.
 * 
 * Author: Jhenifer
 */
public class ReminderService {
    private final SessionManager sessionManager;

    public ReminderService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Displays reminders for a student's upcoming appointments within the next 24 hours.
     */
    public void displayReminders(Student student) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusHours(24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy 'at' h:mm a");

        boolean hasReminder = false;
        for (Appointment a : sessionManager.getAppointmentsForStudent(student)) {
            if (a.isUpcoming() && a.getDateTime().isBefore(next24Hours)) {
                hasReminder = true;
                System.out.println("\n🔔 Reminder:");
                System.out.println("You have an upcoming " + a.getCategory().toString().replace("_", " ") +
                        " session scheduled for " + a.getDateTime().format(formatter) + ".");
            }
        }

        if (!hasReminder) {
            System.out.println("\nNo sessions within the next 24 hours.");
        }
    }
}



