package com.PetSim;

import javax.swing.*;
import java.io.File;

public class Dog extends Animal{

    Dog() {
        super("Dog", "/Images/PixelDog.png", new File("/Images/PixelDog.png"));
        this.setPetImage(new ImageIcon(getClass().getResource(getPetImagePath())));
    }
}
