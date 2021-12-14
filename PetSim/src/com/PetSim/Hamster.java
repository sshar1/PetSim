package com.PetSim;

import javax.swing.*;
import java.io.File;

public class Hamster extends Animal {

    Hamster() {
        super("Hamster", "/Images/PixelHamster.png", new File("/Images/PixelHamster.png"));
        this.setPetImage(new ImageIcon(getClass().getResource(getPetImagePath())));
    }
}
