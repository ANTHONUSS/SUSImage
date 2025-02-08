package fr.ANTHONUSApps.Image;

import javax.swing.*;
import java.io.*;

public class Image {
    private Pixel[][] pixels;
    private int largeur;
    private int hauteur;
    private String nom;

    public Image(Pixel[][] pixels, String nom) {
        this.pixels = pixels;
        this.largeur = pixels.length;
        this.hauteur = pixels[0].length;
        this.nom = nom;

        //System.out.println("Image créée: " + this);
    }

    public Image(String path){
        if (!path.endsWith(".sus")) {
            System.err.println("Invalid file format");
            return;
        }

        System.out.println("Loading image...");

        File file = new File(path);

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            largeur = dis.readInt();
            hauteur = dis.readInt();

            nom = dis.readUTF();

            pixels = new Pixel[hauteur][largeur];

            for (int i = 0; i < hauteur; i++) {
                for (int j = 0; j < largeur; j++) {
                    int posX = dis.readInt();
                    int posY = dis.readInt();
                    int couleur_rouge = dis.readInt();
                    int couleur_vert = dis.readInt();
                    int couleur_bleu = dis.readInt();
                    int couleur_alpha = dis.readInt();

                    pixels[i][j] = new Pixel(posX, posY, couleur_rouge, couleur_vert, couleur_bleu, couleur_alpha);
                }
            }

            System.out.println("Image loaded: " + this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficher(){
        if (pixels == null) {
            System.err.println("No image to display");
            return;
        }

        System.out.println("Opening image...");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(nom);

        ImagePanel imagePanel = new ImagePanel(pixels);
        frame.add(imagePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("Image opened");
    }

    public void save(String path) {
        System.out.println("Saving image...");

        File file = new File(path);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {

            dos.writeInt(largeur);
            dos.writeInt(hauteur);

            dos.writeUTF(nom);

            for (int i = 0; i < hauteur; i++) {
                for (int j = 0; j < largeur; j++) {
                    Pixel pixel = pixels[j][i];
                    dos.writeInt(pixel.getPosX());
                    dos.writeInt(pixel.getPosY());
                    dos.writeInt(pixel.getCouleur_rouge());
                    dos.writeInt(pixel.getCouleur_vert());
                    dos.writeInt(pixel.getCouleur_bleu());
                    dos.writeInt(pixel.getCouleur_alpha());
                }
            }

            System.out.println("Image saved: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Nom : " + nom + "\n" +
                "Largeur : " + largeur + "\n" +
                "Hauteur : " + hauteur;
    }

}
