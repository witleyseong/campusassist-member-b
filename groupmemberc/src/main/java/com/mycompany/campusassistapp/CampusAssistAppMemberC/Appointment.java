package com.mycompany.campusassistapp.CampusAssistAppMemberC;

/**
 * Simplified Appointment class for Analytics usage.
 * Independent from Member A and B modules.
 *
 * @author Marcos Vinicius
 * 
 */

import java.time.LocalDateTime;
public class Appointment {
    private LocalDateTime dateTime;
    private SupportCategory category;

    public Appointment(LocalDateTime dateTime, SupportCategory category) {
        this.dateTime = dateTime;
        this.category = category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public SupportCategory getSupportCategory() {
        return category;
    }
}
