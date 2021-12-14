package com.PetSim;

import javax.swing.*;
import java.io.File;

public class Cat extends Animal{

    Cat() {
        super("Cat", "/Images/PixelCat.png", new File("/Images/PixelCat.png"));
        this.setPetImage(new ImageIcon(getClass().getResource(getPetImagePath())));
    }
}
