package com.PetSim;

public enum Status {

    // based on sum of stats
    DEAD (0),
    HORRIBLE (50),
    BAD(100),
    MODERATE(150),
    GOOD(200),
    GREAT(250),
    AMAZING (300);

    public final int mood;

    Status (int mood) {
        this.mood = mood;
    }
}
