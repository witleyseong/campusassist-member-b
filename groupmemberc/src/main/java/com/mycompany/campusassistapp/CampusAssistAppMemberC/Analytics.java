package com.mycompany.campusassistapp.CampusAssistAppMemberC;

/**
 * Analytics class Now includes showAnalytics() to view appointment and feedback
 * trends together.
 *
 * Author: Marcos Vinicius (updated with integration support)
 */
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Analytics {

    private final List<Appointment> appointments;
    private final List<Feedback> feedbacks;

    public Analytics(List<Appointment> appointments, List<Feedback> feedbacks) {
        this.appointments = appointments;
        this.feedbacks = feedbacks;
    }

    public void showAnalytics() {
        showAppointmentTrends();
        showFeedbackAnalysis();
    }

    //  Méthod new Analytics.java
    public void updateData(List<Appointment> appointments, List<Feedback> feedbacks) {
        this.appointments.addAll(appointments);
        this.feedbacks.addAll(feedbacks);

    }

    private void showAppointmentTrends() {
        System.out.println("\n=== Appointment Trends ===");

        Map<SupportCategory, Integer> categoryCounts = new HashMap<>();
        Map<Integer, Integer> hourlyCounts = new HashMap<>();

        for (Appointment appt : appointments) {
            categoryCounts.put(appt.getSupportCategory(), categoryCounts.getOrDefault(appt.getSupportCategory(), 0) + 1);
            int hour = appt.getDateTime().getHour();
            hourlyCounts.put(hour, hourlyCounts.getOrDefault(hour, 0) + 1);
        }

        System.out.println("Support Categories Booked:");
        for (Map.Entry<SupportCategory, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " bookings");
        }

        System.out.println("\nPeak Appointment Hours:");
        for (Map.Entry<Integer, Integer> entry : hourlyCounts.entrySet()) {
            System.out.println(entry.getKey() + ":00 -> " + entry.getValue() + " bookings");
        }
    }

    private void showFeedbackAnalysis() {
        System.out.println("\n=== Feedback Analysis ===");

        int positive = 0, negative = 0;
        double totalRating = 0;

        for (Feedback feedback : feedbacks) {
            int rating = feedback.getRating();
            totalRating += rating;
            if (rating >= 4) {
                positive++;
            } else if (rating <= 2) {
                negative++;
            }
        }

        double average = feedbacks.isEmpty() ? 0 : totalRating / feedbacks.size();

        System.out.printf("Average Rating: %.2f (max-rating 5)\n", average);
        System.out.println("Positive Reviews (4-5): " + positive);
        System.out.println("Negative Reviews (1-2): " + negative);
    }
}
