/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberB;

/**
 * Manages all appointments from the Admin side (Group Member B).
 * Allows viewing, approving, cancelling, and rescheduling appointments.
 *
 * Author: Witley
 */

import com.mycompany.campusassistapp.CampusAssistAppMemberA.Appointment;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.Student;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.SupportCategory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AppointmentManager {

    private final List<Appointment> appointments;

    /**
     * Constructor that receives a reference to the shared list of appointments.
     *
     * ️ Integration Note:
     * This design choice avoids duplication and ensures real-time consistency
     * between Student (A) and Admin (B) views of the same appointments.
     */
    public AppointmentManager(List<Appointment> sharedAppointments) {
        this.appointments = sharedAppointments;
    }

    /**
     * Allows adding a new appointment to the list.
     * Usually used in test/demo environments.
     */
    public void addAppointment(Appointment app) {
        appointments.add(app);
    }

    /**
     * Displays all appointments in the system.
     * Admin can use this to view sessions before approving/rescheduling.
     */
    public void viewAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println("ID: " + a.getId()
                    + ", Student: " + a.getStudent().getName()
                    + ", Category: " + a.getCategory()
                    + ", Time: " + a.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                    + ", Status: " + a.getStatus());
        }
    }

    /**
     * Allows admin to update the status of a specific appointment (e.g., Approve, Cancel).
     *
     * @param id the ID of the appointment
     * @param newStatus the new status string (e.g., "Approved", "Cancelled")
     */
    public void updateStatus(int id, String newStatus) {
        for (Appointment a : appointments) {
            if (a.getId() == id) {
                a.setStatus(newStatus);
                System.out.println("✅ Status updated to: " + newStatus);
                return;
            }
        }
        System.out.println("❌ Appointment not found.");
    }

    /**
     * Returns the full appointment list.
     * Useful for analytics or testing purposes.
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Allows the admin to reschedule a specific appointment by choosing a new date and time.
     *
     * @param id the ID of the appointment to reschedule
     * @param scanner the Scanner object used for input
     */
    public void reschedule(int id, Scanner scanner) {
        for (Appointment a : appointments) {
            if (a.getId() == id) {
                if (!a.isEditable()) {
                    System.out.println("❌ This appointment is " + a.getStatus() + " and cannot be modified.");
                    return;
                }

                // Step 1: Choose a date
                List<String> availableDates = getAvailableDates();
                if (availableDates.isEmpty()) {
                    System.out.println("❌ No available dates.");
                    return;
                }

                System.out.println("Available Dates:");
                for (int i = 0; i < availableDates.size(); i++) {
                    System.out.println((i + 1) + ". " + availableDates.get(i));
                }

                System.out.print("Select a date number: ");
                int dateChoice = scanner.nextInt();
                scanner.nextLine();

                if (dateChoice < 1 || dateChoice > availableDates.size()) {
                    System.out.println("❌ Invalid date selection.");
                    return;
                }

                String selectedDate = availableDates.get(dateChoice - 1);

                // Step 2: Choose a time
                List<String> availableTimes = getAvailableTimesForDate(selectedDate);
                if (availableTimes.isEmpty()) {
                    System.out.println("❌ No available time slots.");
                    return;
                }

                System.out.println("Available Time Slots on " + selectedDate + ":");
                for (int i = 0; i < availableTimes.size(); i++) {
                    System.out.println((i + 1) + ". " + availableTimes.get(i));
                }

                System.out.print("Select a time slot number: ");
                int timeChoice = scanner.nextInt();
                scanner.nextLine();

                if (timeChoice < 1 || timeChoice > availableTimes.size()) {
                    System.out.println("❌ Invalid time selection.");
                    return;
                }

                // Combine selected date and time
                String newDateTimeStr = selectedDate + " " + availableTimes.get(timeChoice - 1);
                LocalDateTime newDateTime = LocalDateTime.parse(newDateTimeStr, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

                // Update appointment
                a.setDateTime(newDateTime);
                a.setStatus("Pending"); // Reset status after rescheduling
                System.out.println("✅ Appointment rescheduled to " + newDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                return;
            }
        }

        System.out.println("❌ Appointment not found.");
    }

    /**
     * Generates a list of valid booking dates (weekdays only) for the next two weeks.
     */
    private List<String> getAvailableDates() {
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate end = today.plusWeeks(2);

        for (LocalDate date = today; !date.isAfter(end); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                dates.add(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
        }
        return dates;
    }

    /**
     * Returns available time slots for a selected date by checking existing bookings.
     */
    private List<String> getAvailableTimesForDate(String dateStr) {
        List<String> times = new ArrayList<>();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String[] slots = {"09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00"};

        for (String time : slots) {
            boolean taken = false;
            LocalTime t = LocalTime.parse(time);

            for (Appointment a : appointments) {
                if (a.getDateTime().toLocalDate().equals(date)
                        && a.getDateTime().toLocalTime().equals(t)) {
                    taken = true;
                    break;
                }
            }

            if (!taken) {
                times.add(time);
            }
        }
        return times;
    }
}
