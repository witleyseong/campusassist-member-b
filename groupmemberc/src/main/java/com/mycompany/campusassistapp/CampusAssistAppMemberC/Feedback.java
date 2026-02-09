package com.mycompany.campusassistapp.CampusAssistAppMemberC;

/**
 * Simplified Feedback class for Analytics usage.
 * Independent from Member A and B modules.
 * @author Marcos Vinicius
 */

public class Feedback {
    private int rating;

    public Feedback(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
