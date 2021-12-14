package com.PetSim;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    public static ImageIcon resize(File imgFile, ImageIcon petImg) {


        /*
        Image image = petImg.getImage();
        BufferedImage buffered = (BufferedImage) image;
        Image dimg = buffered.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);

         */

        Image image = petImg.getImage();
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);


        /*
        BufferedImage bi = new BufferedImage(petImg.getIconWidth(), petImg.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = bi.createGraphics();
        petImg.paintIcon(null, g, 0,0);
        g.dispose();

        bi.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(bi);

         */


        /*
        Image image = petImg.getImage();
        BufferedImage resizedImg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.drawImage(image, 0, 0, 20, 20, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
         */



        /*
        BufferedImage img = null;

        try {
            img = ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        return new ImageIcon(dimg);

         */
    }
}
