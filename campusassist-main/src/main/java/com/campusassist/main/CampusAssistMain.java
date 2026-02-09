package com.campusassist.main;

/**
 * Main entry point for CampusAssist application.
 * Allows users to interact as Admin (Member B) or Student (Member A).
 * Integrates features from Group Member C (FAQ and Analytics).
 *
 * Authors: Witley + Jhenifer
 */

// === Imports from each group ===
// Group B: Admin functionality
import com.mycompany.campusassistapp.CampusAssistAppMemberB.AppointmentManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberB.CampusAssistAppMemberB;

// Group A: Student-side logic
import com.mycompany.campusassistapp.CampusAssistAppMemberA.CampusAssistAppMemberA;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.SessionManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.Student;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.Appointment;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.SupportCategory;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.FeedbackManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberA.Feedback;

// Group C: Analytics and FAQ modules
import com.mycompany.campusassistapp.CampusAssistAppMemberC.FAQManager;
import com.mycompany.campusassistapp.CampusAssistAppMemberC.Analytics;

// Java utilities
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CampusAssistMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // === Shared data across modules A, B, and C ===
        SessionManager sessionManager = new SessionManager(); // handles appointments for students
        FeedbackManager sharedFeedbackManager = new FeedbackManager(); // handles feedback
        AppointmentManager appointmentManager = new AppointmentManager(sessionManager.getAppointments()); // used by Admin (B)
        FAQManager faqManager = new FAQManager(); // used by both students and admin

        // Pre-load dummy data for demo/testing purposes
        preloadDummyStudentsAndAppointments(sessionManager);
        preloadDummyFAQs(faqManager);
        preloadDummyFeedbacks(sharedFeedbackManager);

        // Integration Challenge:
        // Analytics from Group C expects its *own* Appointment and Feedback classes.
        // To resolve this, we convert the A-style objects into C-style using helper methods below.
        Analytics analytics = new Analytics(
                convertAppointments(sessionManager.getAppointments()),
                convertFeedbacks(sharedFeedbackManager.getFeedbackList())
        );

        // === Initialize application components ===
        CampusAssistAppMemberA studentApp = new CampusAssistAppMemberA(sessionManager, sharedFeedbackManager);
        CampusAssistAppMemberB adminApp = new CampusAssistAppMemberB(appointmentManager, sharedFeedbackManager, faqManager, analytics);

        // === Main Menu loop ===
        while (true) {
            System.out.println("\n=== Welcome to CampusAssist ===");
            System.out.println("1. Login as Admin");
            System.out.println("2. Continue as Student");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String input = sc.nextLine().trim();

            // Route user to correct interface
            switch (input) {
                case "1" -> adminApp.startAdminApp(sessionManager, sharedFeedbackManager);
                case "2" -> studentApp.startStudentApp(faqManager);
                case "3" -> {
                    System.out.println("👋 Goodbye! Exiting CampusAssist.");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid input. Choose 1, 2, or 3.");
            }
        }
    }

    /**
     * Preloads example students and appointments.
     * Used for demonstration purposes only.
     */
    private static void preloadDummyStudentsAndAppointments(SessionManager sessionManager) {
        Student maguinho = new Student("1001", "Maguinho do Piaui", "111");
        Student xuxa = new Student("1002", "Xuxa dos Baixinhos", "222");
        Student pablo = new Student("1003", "Pablo do Arrocha", "333");

        sessionManager.getAppointments().add(new Appointment(maguinho, SupportCategory.MENTAL_HEALTH, LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 0))));
        sessionManager.getAppointments().add(new Appointment(xuxa, SupportCategory.ACADEMIC_SUPPORT, LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(10, 0))));
        sessionManager.getAppointments().add(new Appointment(pablo, SupportCategory.FINANCIAL_AID, LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(11, 0))));

        sessionManager.getAppointments().add(new Appointment(maguinho, SupportCategory.FINANCIAL_AID, LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.of(13, 0))));
        sessionManager.getAppointments().add(new Appointment(xuxa, SupportCategory.MENTAL_HEALTH, LocalDateTime.of(LocalDate.now().minusDays(3), LocalTime.of(14, 0))));
        sessionManager.getAppointments().add(new Appointment(pablo, SupportCategory.ACADEMIC_SUPPORT, LocalDateTime.of(LocalDate.now().minusDays(4), LocalTime.of(15, 0))));
    }

    /**
     * Loads predefined FAQ entries.
     */
    private static void preloadDummyFAQs(FAQManager faqManager) {
        faqManager.addFAQ("How to reset my university password?", "You can reset it on the university portal under 'Account Settings'.");
        faqManager.addFAQ("Where is the Student Support office located?", "The office is in Building A, Room 101.");
        faqManager.addFAQ("How to book an appointment with a counselor?", "Use the CampusAssist app or visit Student Support.");
        faqManager.addFAQ("Can I apply for financial aid online?", "Yes, through the university's Financial Aid portal.");
        faqManager.addFAQ("Where can I find the academic calendar?", "It's available on the university website under 'Academics'.");
    }

    /**
     * Loads dummy feedback examples for testing analytics.
     */
    private static void preloadDummyFeedbacks(FeedbackManager feedbackManager) {
        feedbackManager.addFeedback(new Feedback(
                new Appointment(new Student("1001", "Maguinho do Piaui", "111"), SupportCategory.MENTAL_HEALTH, LocalDateTime.now()),
                "Great support session, very helpful!",
                4
        ));
        feedbackManager.addFeedback(new Feedback(
                new Appointment(new Student("1002", "Xuxa dos Baixinhos", "222"), SupportCategory.ACADEMIC_SUPPORT, LocalDateTime.now()),
                "Counselor was very kind and patient.",
                5
        ));
        feedbackManager.addFeedback(new Feedback(
                new Appointment(new Student("1003", "Pablo do Arrocha", "333"), SupportCategory.FINANCIAL_AID, LocalDateTime.now()),
                "I had issues booking, but support helped me fast.",
                3
        ));
    }

    /**
     * Converts Appointments from Group A format to Group C format.
     * Required because Group C uses its own Appointment class.
     */
    private static List<com.mycompany.campusassistapp.CampusAssistAppMemberC.Appointment> convertAppointments(List<Appointment> originalAppointments) {
        List<com.mycompany.campusassistapp.CampusAssistAppMemberC.Appointment> converted = new ArrayList<>();
        for (Appointment a : originalAppointments) {
            converted.add(new com.mycompany.campusassistapp.CampusAssistAppMemberC.Appointment(
                    a.getDateTime(),
                    com.mycompany.campusassistapp.CampusAssistAppMemberC.SupportCategory.valueOf(a.getCategory().name())
            ));
        }
        return converted;
    }

    /**
     * Converts Feedback from Group A format to Group C format.
     */
    private static List<com.mycompany.campusassistapp.CampusAssistAppMemberC.Feedback> convertFeedbacks(List<Feedback> originalFeedbacks) {
        List<com.mycompany.campusassistapp.CampusAssistAppMemberC.Feedback> converted = new ArrayList<>();
        for (Feedback f : originalFeedbacks) {
            converted.add(new com.mycompany.campusassistapp.CampusAssistAppMemberC.Feedback(f.getRating()));
        }
        return converted;
    }
}
