package com.PetSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Inventory implements ActionListener {

    JFrame inventoryFrame;
    JLabel title;
    JMenuBar menuBar;
    JMenu mainMenu;
    JMenu shopMenu;
    JMenuItem mainMenuItem;
    JMenuItem shopItem;
    JPanel mainPanel; // all panels go in the main panel
    JPanel[] petPanels = new JPanel[9]; // each pet has a panel
    JLabel petName;
    JLabel petAgeAndStatus;
    JLabel imageContainer;
    JButton petPageButton;
    HashMap<JButton, Animal> buttonMap = new HashMap<>();


    Inventory() {
        inventoryFrame = new JFrame();
        inventoryFrame.setTitle("Pet Simulator");
        inventoryFrame.setSize(600, 600);
        inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventoryFrame.setLayout(new BorderLayout());
        inventoryFrame.setResizable(false);

        title = new JLabel("Inventory");
        title.setPreferredSize(new Dimension(300, 60));
        title.setFont(new Font("Arial", Font.PLAIN, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color (225, 225, 225));
        mainPanel.setPreferredSize(new Dimension(2, 2));
        mainPanel.setLayout(new GridLayout(3, 3, 10, 10));

        JPanel sidePanel1 = new JPanel();
        sidePanel1.setPreferredSize(new Dimension(30, 10));

        JPanel sidePanel2 = new JPanel();
        sidePanel2.setPreferredSize(new Dimension(30, 10));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(10, 60));

        menuBar = new JMenuBar();

        mainMenu = new JMenu("Main Menu");
        shopMenu = new JMenu("Shop");

        mainMenuItem = new JMenuItem("Main Menu");
        shopItem = new JMenuItem("Shop");

        mainMenuItem.addActionListener(this);
        shopItem.addActionListener(this);

        mainMenu.add(mainMenuItem);
        shopMenu.add(shopItem);

        menuBar.add(mainMenu);
        menuBar.add(shopMenu);

        inventoryFrame.add(sidePanel1, BorderLayout.LINE_END);
        inventoryFrame.add(sidePanel2, BorderLayout.LINE_START);
        inventoryFrame.add(bottomPanel, BorderLayout.PAGE_END);
        inventoryFrame.add(mainPanel, BorderLayout.CENTER);
        inventoryFrame.add(title, BorderLayout.NORTH);
        inventoryFrame.setJMenuBar(menuBar);

        // Makes 9 panels
        for (int i = 0; i<= 8; i++) {
            petPanels[i] = new JPanel();
            petPanels[i].setBackground(new Color(190, 190, 190));
            petPanels[i].setPreferredSize(new Dimension(50, 50));
            petPanels[i].setLayout(new BoxLayout(petPanels[i], BoxLayout.Y_AXIS));
            mainPanel.add(petPanels[i]);
        }


        int counter = 0;

        for (Animal animal : PetSimulator.getUser().inventory) {

            petName = new JLabel();
            petName.setText(animal.getName() + " - " + animal.getSpecies());
            petName.setPreferredSize(new Dimension(40, 10));
            petName.setAlignmentX(Component.CENTER_ALIGNMENT);

            imageContainer = new JLabel("Image goes here");
            imageContainer.setPreferredSize(new Dimension(40, 40));
            imageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            petPageButton = new JButton(animal.getName() + "'s page");
            petPageButton.setPreferredSize(new Dimension(20, 10));
            petPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            petPageButton.setFocusable(false);
            petPageButton.addActionListener(this);

            petAgeAndStatus = new JLabel("Age: " + animal.getAge() + "      "+ "Status: " + animal.getStatus(), SwingConstants.CENTER);
            petAgeAndStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
            petAgeAndStatus.setPreferredSize(new Dimension(40, 10));
            
            buttonMap.put(petPageButton, animal);

            Color panelColor = switch (animal.getStatus()) {

                case DEAD:
                    yield new Color(0, 0, 0);
                case HORRIBLE:
                    yield new Color(245, 66, 66); // Red
                case BAD:
                    yield new Color (240, 142, 62); // Orange
                case MODERATE:
                    yield new Color(237, 234, 57); // Yellow
                case GOOD:
                    yield new Color(91, 237, 55); // Green
                case GREAT:
                    yield new Color(57, 237, 237); // Blue
                case AMAZING:
                    yield new Color(195, 55, 230); // Purple
            };

            petPanels[counter].setBackground(panelColor);
            petPanels[counter].add(petName);
            petPanels[counter].add(imageContainer);
            petPanels[counter].add(petPageButton);
            petPanels[counter].add(petAgeAndStatus);
            counter++;
        }

        inventoryFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == mainMenuItem) {
            new MainMenu(PetSimulator.getUser().getName());
            inventoryFrame.dispose();
            System.out.println("You opened the main menu");
        }

        if (e.getSource() == shopItem) {
            new Shop();
            inventoryFrame.dispose();
            System.out.println("You opened the shop");
        }

        for (Map.Entry <JButton, Animal> entry: buttonMap.entrySet()) {

            JButton button = entry.getKey();
            Animal animal = entry.getValue();

            if (e.getSource() == button) {

                new PetPage(animal);
               
            }
        }
    }
}
