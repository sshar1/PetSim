package com.PetSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PetPage implements ActionListener {

    Animal pet;

    JFrame pageFrame;
    JLabel pageTitle;
    JLabel imageContainer;
    JLabel description;

    JLabel name;
    JLabel age;
    JLabel status;
    JLabel species;

    JLabel sleepLevel;
    JLabel funLevel;
    JLabel hungerLevel;

    JButton sleepButton;
    JButton wakeUpButton;

    JButton putOutsideButton;
    JButton putInsideButton;

    JButton feedButton;

    HashMap<JButton, String> buttonMap = new HashMap<>();
    
    PetPage(Animal animal) {

        pet = animal;

        pageFrame = new JFrame();
        pageFrame.setTitle("Pet Simulator");
        pageFrame.setSize(600, 400);
        pageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GroupLayout layout = new GroupLayout(pageFrame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        pageFrame.getContentPane().setLayout(layout);

        pageTitle = new JLabel(animal.getName() + "'s Page");
        pageTitle.setPreferredSize(new Dimension(300, 60));
        pageTitle.setFont(new Font("Arial", Font.PLAIN, 22));
        pageTitle.setHorizontalAlignment(JLabel.CENTER);
        pageTitle.setVerticalAlignment(JLabel.TOP);

        imageContainer = new JLabel("Pet image goes here");
        imageContainer.setPreferredSize(new Dimension(100, 100));

        description = new JLabel();
        description.setText(animal.getName() + " is currently " + animal.getCurrentOutsideStatus() + " and is " + animal.getCurrentSleepStatus());
        description.setPreferredSize(new Dimension(440, 60));

        // Basic stats (under image)
        name = new JLabel(animal.getName());
        name.setPreferredSize(new Dimension(100, 20));

        age = new JLabel("Age: " + animal.getAge());
        age.setPreferredSize(new Dimension(100, 20));

        status = new JLabel(" Status: " + animal.getStatus());
        status.setPreferredSize(new Dimension(100, 20));

        species = new JLabel("Species: " + animal.getSpecies());
        species.setPreferredSize(new Dimension(100, 20));

        // Headings
        sleepLevel = new JLabel("Sleepiness: " + animal.getSleepiness());
        sleepLevel.setPreferredSize(new Dimension(200, 30));

        funLevel = new JLabel("Fun: " + animal.getFun());
        funLevel.setPreferredSize(new Dimension(200, 30));

        hungerLevel = new JLabel("Hunger: " + animal.getHunger());
        hungerLevel.setPreferredSize(new Dimension(200, 30));

        // Buttons
        sleepButton = new JButton("Put to sleep");
        sleepButton.setPreferredSize(new Dimension(20, 10));
        sleepButton.setFocusable(false);
        sleepButton.addActionListener(this);

        wakeUpButton = new JButton("Wake up");
        wakeUpButton.setPreferredSize(new Dimension(20, 10));
        wakeUpButton.setFocusable(false);
        wakeUpButton.addActionListener(this);

        putOutsideButton = new JButton("Take outside");
        putOutsideButton.setPreferredSize(new Dimension(20, 10));
        putOutsideButton.setFocusable(false);
        putOutsideButton.addActionListener(this);

        putInsideButton = new JButton("Bring inside");
        putInsideButton.setPreferredSize(new Dimension(20, 10));
        putInsideButton.setFocusable(false);
        putInsideButton.addActionListener(this);

        feedButton = new JButton("Feed");
        feedButton.setPreferredSize(new Dimension(20, 10));
        feedButton.setFocusable(false);
        feedButton.addActionListener(this);

        buttonMap.put(sleepButton, "Sleep");
        buttonMap.put(wakeUpButton, "Wake up");
        buttonMap.put(putOutsideButton, "Outside");
        buttonMap.put(putInsideButton, "Inside");
        buttonMap.put(feedButton, "Feed");



        layout.setHorizontalGroup(
                layout.createSequentialGroup() // if this is sequential and the other is parallel, it looks like one column
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addComponent(imageContainer)
                                .addComponent(name)
                                .addComponent(age)
                                .addComponent(status)
                                .addComponent(species)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)) // change title and description group to center
                                .addComponent(pageTitle)
                                .addComponent(description)
                                .addComponent(sleepLevel)
                                .addComponent(sleepButton)
                                .addComponent(funLevel)
                                .addComponent(putOutsideButton)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addComponent(wakeUpButton)
                                .addComponent(putInsideButton)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addComponent(hungerLevel)
                                .addComponent(feedButton)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(pageTitle)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addComponent(imageContainer)
                                .addComponent(description)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addComponent(name)
                                .addComponent(sleepLevel)
                                .addComponent(hungerLevel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addComponent(age)
                                .addComponent(sleepButton)
                                .addComponent(wakeUpButton)
                                .addComponent(feedButton)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addComponent(status)
                                .addComponent(funLevel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addComponent(species)
                                .addComponent(putOutsideButton)
                                .addComponent(putInsideButton)
        );


        pageFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Map.Entry <JButton, String> entry: buttonMap.entrySet()) {

            JButton button = entry.getKey();
            String methodName = entry.getValue();

            if (e.getSource() == button) {

                switch (methodName) {

                    case "Sleep":
                        pet.sleep();
                        updateText();
                        break;
                    case "Wake up":
                        pet.wakeUp();
                        updateText();
                        break;
                    case "Outside":
                        pet.putOutside();
                        updateText();
                        break;
                    case "Inside":
                        pet.putInside();
                        updateText();
                        break;
                    case "Feed":
                        pet.eat();
                        updateText();
                        break;
                }
            }
        }

    }

    private void updateText() {
        description.setText(pet.getName() + " is currently " + pet.getCurrentOutsideStatus() + " and is " + pet.getCurrentSleepStatus());
        status.setText(" Status: " + pet.getStatus());
        funLevel.setText("Fun: " + pet.getFun());
        hungerLevel.setText("Hunger: " + pet.getHunger());
        sleepLevel.setText("Sleepiness: " + pet.getSleepiness());
    }
}
