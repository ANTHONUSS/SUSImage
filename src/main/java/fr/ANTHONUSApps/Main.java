package fr.ANTHONUSApps;

import fr.ANTHONUSApps.Image.Image;
import fr.ANTHONUSApps.Image.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("No arguments provided");
        } else if (args.length < 3) {
            if (args[0].equals("convert")) {
                String path = args[1];
                Image image = convertIMG(path);
                if (image != null) {
                    image.save(path.substring(0, path.length() - 4) + ".sus");
                }
            } else if (args[0].equals("open")) {
                String path = args[1];
                Image image = new Image(path);
                image.afficher();
            }

        } else {
            System.err.println("Too much arguments provided");
        }
    }

    private static Image convertIMG(String path) {
        if (!path.endsWith(".png") && !path.endsWith(".jpg") && !path.endsWith(".jpeg")) {
            System.err.println("Invalid file format");
            return null;
        }

        System.out.println("Converting image...");

        try {
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("File not found");
                return null;
            }

            BufferedImage image = ImageIO.read(file);

            int largeur = image.getWidth();
            int hauteur = image.getHeight();

            Pixel[][] pixels = new Pixel[largeur][hauteur];

            for (int i = 0; i < largeur; i++) {
                for (int j = 0; j < hauteur; j++) {
                    int pixel = image.getRGB(i, j);

                    int alpha = (pixel >> 24) & 0xff;
                    int rouge = (pixel >> 16) & 0xff;
                    int vert = (pixel >> 8) & 0xff;
                    int bleu = pixel & 0xff;

                    pixels[i][j] = new Pixel(i, j, rouge, vert, bleu, alpha);
                }
            }

            System.out.println("Image converted");

            return new Image(pixels, file.getName().substring(0, file.getName().length() - 4));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}