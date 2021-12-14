package com.PetSim;

import javax.swing.*;
import java.io.File;

public class Bird extends Animal{

    Bird() {
        super("Bird", "/Images/PixelBird.png", new File("/Images/PixelBird.png"));
        this.setPetImage(new ImageIcon(getClass().getResource(getPetImagePath())));
    }
}
