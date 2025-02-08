package fr.ANTHONUSApps.Image;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private final Pixel[][] pixels;
    private final int largeur;
    private final int hauteur;

    public ImagePanel(Pixel[][] pixels) {
        this.pixels = pixels;

        hauteur = pixels.length;
        largeur = pixels[0].length;

        setPreferredSize(new Dimension(largeur, hauteur));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                Pixel pixel = pixels[j][i];
                Color color = new Color(pixel.getCouleur_rouge(), pixel.getCouleur_vert(), pixel.getCouleur_bleu(), pixel.getCouleur_alpha());
                g.setColor(color);
                g.fillRect(i, j, 1, 1);
            }
        }
    }


}
