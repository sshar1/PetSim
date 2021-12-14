package com.PetSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {

    JFrame mainFrame;
    JMenuBar menuBar;
    JLabel title;
    JLabel moneyLabel;
    JLabel foodLabel;
    JButton buyFoodButton;
    JMenu inventoryMenu;
    JMenu shopMenu;
    JMenuItem inventoryItem;
    JMenuItem shopItem;

    MainMenu(String name) {

        mainFrame = new JFrame();
        mainFrame.setTitle("Pet Simulator");
        mainFrame.setSize(420, 420);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setResizable(false);

        title = new JLabel("Hello, " + name);
        title.setPreferredSize(new Dimension(300, 60));
        title.setFont(new Font("Arial", Font.PLAIN, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);

        moneyLabel = new JLabel("Coins: " + PetSimulator.getUser().getMoney());
        moneyLabel.setPreferredSize(new Dimension(300, 60));
        moneyLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        moneyLabel.setHorizontalAlignment(JLabel.CENTER);
        moneyLabel.setVerticalAlignment(JLabel.CENTER);

        foodLabel = new JLabel("Food: " + PetSimulator.getUser().getFood());
        foodLabel.setPreferredSize(new Dimension(300, 60));
        foodLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        foodLabel.setHorizontalAlignment(JLabel.CENTER);
        foodLabel.setVerticalAlignment(JLabel.BOTTOM);

        buyFoodButton = new JButton("Buy 1 food");
        buyFoodButton.setPreferredSize(new Dimension(100, 20));
        buyFoodButton.setHorizontalAlignment(JButton.CENTER);
        buyFoodButton.setVerticalAlignment(JButton.BOTTOM);
        buyFoodButton.setFocusable(false);


        menuBar = new JMenuBar();

        inventoryMenu = new JMenu("Inventory");
        shopMenu = new JMenu("Shop");

        inventoryItem = new JMenuItem("Inventory");
        shopItem = new JMenuItem("Shop");

        inventoryItem.addActionListener(this);
        shopItem.addActionListener(this);

        inventoryMenu.add(inventoryItem);
        shopMenu.add(shopItem);

        menuBar.add(inventoryMenu);
        menuBar.add(shopMenu);


        mainFrame.add(title);
        mainFrame.add(moneyLabel);
        mainFrame.add(foodLabel);
        mainFrame.add(buyFoodButton);

        mainFrame.setJMenuBar(menuBar);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inventoryItem) {
            new Inventory();
            mainFrame.dispose();
            System.out.println("You opened the inventory");
        }

        if (e.getSource() == shopItem) {
            new Shop();
            mainFrame.dispose();
            System.out.println("You opened the shop");
        }

        if (e.getSource() == buyFoodButton) {
            PetSimulator.getUser().loseMoney(5);
            PetSimulator.getUser().gainFood(1);
        }
    }

}
