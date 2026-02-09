/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campusassistapp.CampusAssistAppMemberA;

/**
 * Handles session bookings, cancellations, and listing appointments.
 * @author jhenifer
 */

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class SessionManager {
    private final List<Appointment> appointments = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
// Time slots hardcoded for weekdays
    private static final List<LocalTime> TIME_SLOTS = Arrays.asList(
            LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0), LocalTime.of(12, 0),
            LocalTime.of(13, 0), LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0)
    );

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void requestSession(Student student) {
        System.out.println("Select Category: 1. Mental Health  2. Academic Support  3. Financial Aid");
        int choice = scanner.nextInt(); scanner.nextLine();
        if (choice < 1 || choice > 3) {
            System.out.println("Invalid category selection.");
            return;
        }
        SupportCategory category = SupportCategory.values()[choice - 1];
        LocalDate targetDate = LocalDate.now().plusDays(1);

        List<LocalTime> availableSlots = getAvailableTimeSlots(targetDate);

        if (availableSlots.isEmpty()) {
            System.out.println("No time slots available for tomorrow.");
            return;
        }

        System.out.println("Choose a time slot for tomorrow:");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }

        int timeChoice = scanner.nextInt(); scanner.nextLine();
        if (timeChoice < 1 || timeChoice > availableSlots.size()) {
            System.out.println("Invalid time slot.");
            return;
        }

        LocalDateTime dateTime = LocalDateTime.of(targetDate, availableSlots.get(timeChoice - 1));
        appointments.add(new Appointment(student, category, dateTime));
        System.out.println("Appointment booked for " + category + " on " + dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy 'at' hh:mm a")));
    }

    public void viewAppointments(Student student) {
        List<Appointment> studentAppointments = getAppointmentsForStudent(student);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy 'at' hh:mm a");

        if (studentAppointments.isEmpty()) {
            System.out.println("You have no appointments.");
            return;
        }

        System.out.println("Your Appointments:");
        System.out.println("(Completed = eligible for feedback)");

        for (int i = 0; i < studentAppointments.size(); i++) {
            Appointment a = studentAppointments.get(i);
            String status = a.isUpcoming() ? "(Upcoming)" : "(Completed)";
            System.out.println((i + 1) + ". " + a.getCategory() + " at " + a.getDateTime().format(fmt) + " " + status);
        }

        System.out.println("\nWould you like to cancel an upcoming appointment? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            List<Appointment> upcomingAppointments = new ArrayList<>();
            for (Appointment a : studentAppointments) {
                if (a.isUpcoming()) upcomingAppointments.add(a);
            }

            if (upcomingAppointments.isEmpty()) {
                System.out.println("No upcoming appointments to cancel.");
                return;
            }

            System.out.println("Select an appointment to cancel:");
            for (int i = 0; i < upcomingAppointments.size(); i++) {
                Appointment app = upcomingAppointments.get(i);
                System.out.println((i + 1) + ". " + app.getCategory() + " at " + app.getDateTime().format(fmt));
            }

            try {
                int cancelChoice = Integer.parseInt(scanner.nextLine());
                if (cancelChoice >= 1 && cancelChoice <= upcomingAppointments.size()) {
                    Appointment toCancel = upcomingAppointments.get(cancelChoice - 1);
                    System.out.print("Are you sure you want to cancel this appointment? Type 'yes' to confirm: ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes")) {
                        appointments.remove(toCancel);
                        System.out.println("Appointment cancelled.");
                    } else {
                        System.out.println("Cancellation aborted.");
                    }
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public List<Appointment> getAppointmentsForStudent(Student student) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getStudent().getStudentId().equals(student.getStudentId())) {
                result.add(a);
            }
        }
        return result;
    }

    public void bookCustomAppointment(Student student) {
        while (true) {
            System.out.println("Enter appointment date (yyyy-MM-dd) within next two weeks:");
            String inputDate = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusWeeks(2))) {
                    System.out.println("Date out of range. Try again.");
                    continue;
                }
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    System.out.println("Weekday bookings only.");
                    continue;
                }

                System.out.println("Select Category: 1. Mental Health  2. Academic Support  3. Financial Aid");
                int choice = scanner.nextInt(); scanner.nextLine();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid category selection.");
                    return;
                }
                SupportCategory category = SupportCategory.values()[choice - 1];

                List<LocalTime> availableSlots = getAvailableTimeSlots(date);
                if (availableSlots.isEmpty()) {
                    System.out.println("No slots available for that date.");
                    return;
                }

                System.out.println("Available Time Slots:");
                for (int i = 0; i < availableSlots.size(); i++) {
                    System.out.println((i + 1) + ". " + availableSlots.get(i));
                }

                int timeChoice = scanner.nextInt(); scanner.nextLine();
                if (timeChoice < 1 || timeChoice > availableSlots.size()) {
                    System.out.println("Invalid time slot.");
                    return;
                }

                LocalDateTime dateTime = LocalDateTime.of(date, availableSlots.get(timeChoice - 1));
                appointments.add(new Appointment(student, category, dateTime));
                System.out.println("Appointment confirmed for " + category + " on " + dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy 'at' hh:mm a")));
                break;
            } catch (Exception e) {
                System.out.println("Invalid input format. Please use yyyy-MM-dd.");
            }
        }
    }

    private List<LocalTime> getAvailableTimeSlots(LocalDate date) {
        List<LocalTime> bookedSlots = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDateTime().toLocalDate().equals(date)) {
                bookedSlots.add(a.getDateTime().toLocalTime());
            }
        }

        List<LocalTime> available = new ArrayList<>();
        for (LocalTime slot : TIME_SLOTS) {
            if (!bookedSlots.contains(slot)) {
                available.add(slot);
            }
        }
        return available;
    }
}

