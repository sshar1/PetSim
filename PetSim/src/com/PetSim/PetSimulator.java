package com.PetSim;

public class PetSimulator {

    private static User user;

    public static void main(String[] args) {
        user = new User();
    }

    public static User getUser() {
        return user;
    }
}
