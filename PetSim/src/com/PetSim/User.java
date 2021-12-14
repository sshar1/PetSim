package com.PetSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;

public class User implements ActionListener {

    private String name;
    private int food = 15;
    public HashSet<Animal> inventory = new HashSet<>();
    public HashSet<Animal> availableAnimals = new HashSet<>(Arrays.asList(new Dog(), new Cat(), new Bird(), new Hamster(), new Lizard(), new Snake()));
    private int money = 200;

    JFrame logInFrame;
    JLabel title;
    JTextField nameField;
    JButton submitButton;

    User() {

        title = new JLabel("Welcome to Pet Simulator!");
        title.setPreferredSize(new Dimension(300, 60));
        title.setFont(new Font("Arial", Font.PLAIN, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));
        nameField.setHorizontalAlignment(JLabel.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        logInFrame = new JFrame();
        logInFrame.setTitle("Pet Simulator");
        logInFrame.setSize(420, 420);
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logInFrame.setLayout(new FlowLayout());

        logInFrame.add(title);
        logInFrame.add(nameField);
        logInFrame.add(submitButton);

        logInFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            System.out.println("Your name is " + nameField.getText());

            if ((nameField.getText() == null) || (nameField.getText().trim().equals(""))) {
                JOptionPane.showMessageDialog(null, "Please input a valid name", "Error", JOptionPane.WARNING_MESSAGE);

            } else {

                // set name and open main menu
                this.name = nameField.getText();
                submitButton.setEnabled(false);
                nameField.setEditable(false);

                logInFrame.dispose();
                new MainMenu(this.name);
            }
        }
    }

    public String getName() {
        return name;
    }


    // Inventory methods
    public void buyAnimal(Animal animal) {
            inventory.add(animal);
            availableAnimals.remove(animal);
            loseMoney(animal.getPrice());
            animal.setAge(System.currentTimeMillis());

            Thread thread = new Thread(){
                public void run(){
                    while (true) {

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        animal.setFun(animal.getFun() - 2);
                        animal.setSleepiness(animal.getSleepiness() - 2);
                        animal.setHunger(animal.getHunger() - 3);
                    }
                }
            };

        thread.start();
    }

    public void loseAnimal(Animal animal) {
            inventory.remove(animal);
            availableAnimals.add(animal);
    }


    // Money methods
    public void loseMoney(int moneyLost) {
        if (this.money - moneyLost >= 0) {
            this.money -= moneyLost;
        }
        else {
            JOptionPane.showMessageDialog(null, "You need more money to make this purchase", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void gainMoney(int moneyGained) {
        this.money += moneyGained;
    }

    public boolean hasEnoughMoney(int moneyLost) {
        if (this.money - moneyLost >= 0) {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "You need more money to purchase", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public int getMoney() {
        return money;
    }


    // Food methods
    public void feed(int foodGiven) {

        if (this.food - foodGiven >= 0) {
            this.food -= foodGiven;
        }
        else {
            JOptionPane.showMessageDialog(null, "You need more food", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int getFood() {
        return this.food;
    }

    public void gainFood(int foodGained) {
        this.food += foodGained;
    }
}
