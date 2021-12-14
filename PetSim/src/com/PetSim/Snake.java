package com.PetSim;

import javax.swing.*;
import java.io.File;

public class Snake extends Animal{

    Snake() {
        super("Snake", "/Images/PixelSnake.png", new File("/Images/PixelSnake.png"));
        this.setPetImage(new ImageIcon(getClass().getResource(getPetImagePath())));
    }
}
