package com.PetSim;

import javax.swing.*;
import java.io.File;

public class Lizard extends Animal{

    Lizard() {
        super("Lizard", "/Images/PixelLizard.png", new File("/Images/PixelLizard.png"));
        this.setPetImage(new ImageIcon(getClass().getResource(getPetImagePath())));
    }
}
