package karty;

import hrac.Hrac;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Abstraktná trieda slúži, ako predloha pre karty hry, pričom dedí od triedy JButton.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public abstract class Karta extends JButton {

    private final int cenaKarty;

    /**
     * Konštruktor načíta farbu a cenuKarty, nastaví veľkosť a rozmiestnenie jednotlivých častí karty.
     * @param farba načíta farbu karty
     * @param cenaKarty načíta cenu karty
     */
    public Karta(String farba, int cenaKarty) {
        this.cenaKarty = cenaKarty;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(120, 120));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBackground(this.getColor(farba));

        JLabel cenaKartyLabel = new JLabel("" + this.cenaKarty);
        cenaKartyLabel.setFont(new Font("Italic", Font.ITALIC, 20));
        cenaKartyLabel.setForeground(new Color(0, 0, 0));
        cenaKartyLabel.setBounds(5, 5, 30, 30);
        this.add(cenaKartyLabel);
    }

    /**
     * Podľa názvu farby v String-ovej forme vráti farbu, ktorá sa používa v zafarbení karty.
     * @param farba slúži na zistenie o akú farbu ide.
     * @return vráti farbu, použiteľnú, ako pozadie karty.
     */
    private Color getColor(String farba) {
        return switch (farba) {
            case "Modra" -> new Color(173, 239, 252);
            case "Cervena" -> new Color(250, 179, 179);
            case "Zelena" -> new Color(182, 250, 179);
            case "Zlata" -> new Color(250, 248, 179);
            default -> new Color(0, 0, 0);
        };
    }

    /**
     * Vráti cenu karty.
     * @return cena karty
     */
    public int getCenaKarty() {
        return this.cenaKarty;
    }

    /**
     * Abstraktná metóda, ktorá určuje čo sa má stať po zahraní karty.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    public abstract void zahraj(Hrac hrac1);
}
