package com.PetSim;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Animal {

    private String name;
    private long timeGot;

    private ImageIcon petImage;
    private String imgPath;
    private File imgFile;

    private boolean inHouse = true;
    private boolean isSleeping = false;

    private int hunger = 100;
    private int fun = 100;
    private int sleepiness = 100;

    private final int MAX_HUNGER = 100;
    private final int MAX_SLEEPINESS = 100;
    private final int MAX_FUN = 100;

    private long timeLastSlept = 0;
    private long timeLastInside = 0;

    private Status status;

    private final String species;
    private final int price = 100;


    Animal(String species, String imgPath, File imgFile) {
        this.species = species;
        this.imgPath = imgPath;
        this.imgFile = imgFile;
    }


    // Eat methods
    public void eat() {
        if (this.hunger < MAX_HUNGER) {
            PetSimulator.getUser().feed(1);
            this.hunger += 5; // Increase hunger
            this.fun -= 10; // Decrease fun

            if (this.hunger > MAX_HUNGER) {
                this.hunger = 100;
            }
        }
    }

    public int getHunger() {
        return this.hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }


    // Sleep methods
    public void sleep() {
        if (this.sleepiness < MAX_SLEEPINESS && (inHouse)) {
            timeLastSlept = System.currentTimeMillis();
        } else {
            JOptionPane.showMessageDialog(null, "Error putting pet to sleep", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void wakeUp() {
        if (isSleeping) {
            int timeSlept = (int) ((System.currentTimeMillis()) - timeLastSlept) / 1000; // seconds sleeping
            isSleeping = false;
            this.sleepiness += timeSlept/2; // Increase sleepiness
            this.hunger -= timeSlept; // Decrease hunger
            System.out.println("Animal slept for " + timeSlept + " seconds");
            int moneyGained = (timeSlept / 12); // 5 money per minute sleeping
            PetSimulator.getUser().gainMoney(moneyGained);

            if (this.sleepiness > MAX_SLEEPINESS) {
                this.sleepiness = 100;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Error waking up pet", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int getSleepiness() {
        return this.sleepiness;
    }

    public void setSleepiness(int sleepiness) {
        this.sleepiness = sleepiness;
    }

    public String getCurrentSleepStatus() {
        if (isSleeping) {
            return "sleeping";
        }
        else {
            return "awake";
        }
    }


    // Fun methods
    public void putOutside() {
        if (this.inHouse && !(isSleeping)) {
            this.inHouse = false;
            timeLastInside = System.currentTimeMillis();
        }
        else { //already outside
            JOptionPane.showMessageDialog(null, "Error putting pet outside", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void putInside() {
        if (!this.inHouse) {
            int timeOutside = (int) ((System.currentTimeMillis()) - timeLastInside) / 1000; // seconds outside
            System.out.println(this.inHouse);
            if (timeOutside > 300) { // left outside for 5 minutes
                JOptionPane.showMessageDialog(null, "You left " + name + "outside for too long and they have left you :(", "Error", JOptionPane.WARNING_MESSAGE);
                leave();
            }
            else {
                System.out.println("Pet back inside");
                this.inHouse = true;
                this.fun += timeOutside/2; // Increase fun
                this.sleepiness -= timeOutside; // Decrease sleepiness
                System.out.println("Animal outside for " + timeOutside + " seconds");
                int moneyGained = (timeOutside / 12); // 5 money per minute outside
                PetSimulator.getUser().gainMoney(moneyGained);
            }

            if (this.fun > MAX_FUN) {
                this.fun = 100;
            }
        }
        else {
            System.out.println(this.inHouse);
            JOptionPane.showMessageDialog(null, "Error putting pet inside", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int getFun() {
        return this.fun;
    }

    public void setFun(int fun) {
        this.fun = fun;
    }

    public String getCurrentOutsideStatus() {
        if (inHouse) {
            return "inside";
        }
        else {
            return "outside";
        }
    }


    // Name methods
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Getter methods
    public Status getStatus() {

        int moodSum = this.hunger + this.sleepiness + this.fun;

        for (Status e : Status.values()) {
            if (moodSum <= e.mood) {

                if (moodSum == 0) { // if pet is dead
                    killPet();
                    break;
                }
                else {
                    return e;
                }
            }
        }
        return Status.AMAZING; // if status is between 250 and 300
    }

    public String getSpecies() {return this.species;}

    public int getPrice() {return this.price;}



    // Kill/leave methods
    public void killPet() {
        System.out.println(name + " has died");
        PetSimulator.getUser().loseAnimal(this);
        JOptionPane.showMessageDialog(null, "Your pet has died", ":(", JOptionPane.WARNING_MESSAGE);
    }

    public void leave() {
        System.out.println(name + " left");
        PetSimulator.getUser().loseAnimal(this);
        JOptionPane.showMessageDialog(null, "Your pet has left because you left it outside for too long", ":(", JOptionPane.WARNING_MESSAGE);
    }


    // Age methods
    public int getAge() {
        int age = (int) (System.currentTimeMillis() - this.timeGot) / 30000; // Age updates 1 every 30 seconds
        return age;
    }

    public void setAge(long timeGot) {
        this.timeGot = timeGot;
    }

    // Image methods
    public ImageIcon getPetImage() {
        return this.petImage;
    }

    public void setPetImage(ImageIcon image) {
        this.petImage = image;
    }

    public String getPetImagePath() {
        return this.imgPath;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public File getImgFile() {
        return this.imgFile;
    }
}
