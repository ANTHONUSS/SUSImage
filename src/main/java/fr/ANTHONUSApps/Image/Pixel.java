package fr.ANTHONUSApps.Image;

public class Pixel {
    private final int posX;
    private final int posY;
    private final int couleur_rouge;
    private final int couleur_vert;
    private final int couleur_bleu;
    private final int couleur_alpha;

    public Pixel(int posX, int posY, int couleur_rouge, int couleur_vert, int couleur_bleu, int couleur_alpha) {
        this.posX = posX;
        this.posY = posY;
        this.couleur_rouge = couleur_rouge;
        this.couleur_vert = couleur_vert;
        this.couleur_bleu = couleur_bleu;
        this.couleur_alpha = couleur_alpha;

        //System.out.println("Pixel créé: " + this);
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getCouleur_rouge() {
        return couleur_rouge;
    }
    public int getCouleur_vert() {
        return couleur_vert;
    }
    public int getCouleur_bleu() {
        return couleur_bleu;
    }
    public int getCouleur_alpha() {
        return couleur_alpha;
    }

    @Override
    public String toString() {
        return "Position X: " + posX + "\n" +
                "Position Y: " + posY + "\n" +
                "Couleur Rouge: " + couleur_rouge + "\n" +
                "Couleur Vert: " + couleur_vert + "\n" +
                "Couleur Bleu: " + couleur_bleu + "\n" +
                "Couleur Alpha: " + couleur_alpha + "\n";
    }
}
