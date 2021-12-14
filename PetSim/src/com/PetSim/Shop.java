package com.PetSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Shop implements ActionListener {

    JFrame shopFrame;
    JLabel title;
    JMenuBar menuBar;
    JMenu mainMenu;
    JMenu inventoryMenu;
    JMenuItem mainMenuItem;
    JMenuItem inventoryItem;
    JPanel mainPanel; // all panels go in the main panel
    JPanel[] petPanels = new JPanel[9]; // each pet has a panel
    JLabel petType;
    JLabel petPrice;
    JButton buyButton;
    JLabel imageContainer;
    HashMap<JButton, Animal> buttonMap = new HashMap<>();

    Shop() {
        shopFrame = new JFrame();
        shopFrame.setTitle("Pet Simulator");
        shopFrame.setSize(600, 600);
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setLayout(new BorderLayout());
        shopFrame.setResizable(false);

        title = new JLabel("Shop");
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
        inventoryMenu = new JMenu("Inventory");

        mainMenuItem = new JMenuItem("Main Menu");
        inventoryItem = new JMenuItem("Inventory");

        mainMenuItem.addActionListener(this);
        inventoryItem.addActionListener(this);

        mainMenu.add(mainMenuItem);
        inventoryMenu.add(inventoryItem);

        menuBar.add(mainMenu);
        menuBar.add(inventoryMenu);

        shopFrame.add(sidePanel1, BorderLayout.LINE_END);
        shopFrame.add(sidePanel2, BorderLayout.LINE_START);
        shopFrame.add(bottomPanel, BorderLayout.PAGE_END);
        shopFrame.add(mainPanel, BorderLayout.CENTER);
        shopFrame.add(title, BorderLayout.NORTH);
        shopFrame.setJMenuBar(menuBar);

        // Makes 9 panels
        for (int i = 0; i<= 8; i++) {
            petPanels[i] = new JPanel();
            petPanels[i].setBackground(new Color(190, 190, 190));
            petPanels[i].setPreferredSize(new Dimension(50, 50));
            petPanels[i].setLayout(new BoxLayout(petPanels[i], BoxLayout.Y_AXIS));
            mainPanel.add(petPanels[i]);
        }


        int counter = 0;

        for (Animal animal : PetSimulator.getUser().availableAnimals) {

            petType = new JLabel();
            petType.setText(animal.getSpecies());
            petType.setPreferredSize(new Dimension(40, 10));
            petType.setAlignmentX(Component.CENTER_ALIGNMENT);

            petPrice = new JLabel("Price: " + animal.getPrice());
            petPrice.setPreferredSize(new Dimension(40, 10));
            petPrice.setAlignmentX(Component.CENTER_ALIGNMENT);

            buyButton = new JButton("Purchase");
            buyButton.setPreferredSize(new Dimension(40, 10));
            buyButton.setFocusable(false);
            buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            buyButton.addActionListener(this);

            imageContainer = new JLabel(animal.getPetImage());
            imageContainer.setPreferredSize(new Dimension(40, 40));
            //ImageHandler.resize(animal.getImgFile(), animal.getPetImage());
            animal.setPetImage(ImageHandler.resize(animal.getImgFile(), animal.getPetImage()));
            imageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

            buttonMap.put(buyButton, animal);

            petPanels[counter].add(petType);
            petPanels[counter].add(Box.createRigidArea(new Dimension(0, 3)));
            petPanels[counter].add(imageContainer);
            petPanels[counter].add(petPrice);
            petPanels[counter].add(buyButton);
            counter++;
        }

        shopFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == mainMenuItem) {
            new MainMenu(PetSimulator.getUser().getName());
            shopFrame.dispose();
            System.out.println("You opened the main menu");
        }

        if (e.getSource() == inventoryItem) {
            //open inventory
            new Inventory();
            shopFrame.dispose();
            System.out.println("You opened the inventory");
        }

        for (Map.Entry <JButton, Animal> entry: buttonMap.entrySet()) {

            JButton button = entry.getKey();
            Animal animal = entry.getValue();

            if (e.getSource() == button) {

                if (PetSimulator.getUser().hasEnoughMoney(animal.getPrice())) { // check for enough money

                    String chosenName = JOptionPane.showInputDialog("Name your new pet: ");

                    while (chosenName == null || chosenName.equals("")) {
                        chosenName = JOptionPane.showInputDialog("Name your new pet: ");
                    }

                    PetSimulator.getUser().buyAnimal(animal);
                    button.setEnabled(false);
                    animal.setName(chosenName);
                }
            }
        }
    }
}
