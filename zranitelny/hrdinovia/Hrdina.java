package zranitelny.hrdinovia;

import hrac.Hrac;
import zranitelny.Stvorenie;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Abstraktná trieda slúži na vytvorenie predlohy pre hrdinov.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public abstract class Hrdina extends Stvorenie {
    private final JLabel zivot;
    private final JLabel mana;

    /**
     * Konštruktor načíta parameter zivot a vykreslí jednotlivé časti hrdinu.
     * @param zivot život hrdinu
     */
    public Hrdina(int zivot) {
        super(zivot);
        this.setPreferredSize(new Dimension(110, 80));

        this.setBackground(new Color(194, 197, 204));
        this.zivot = new JLabel("");
        this.zivot.setFont(new Font("Italic", Font.ITALIC, 24));
        this.zivot.setForeground(new Color(0, 0, 0));
        this.zivot.setBounds(6, 10, 110, 40);
        this.add(this.zivot);
        this.mana = new JLabel("");
        this.mana.setFont(new Font("Italic", Font.ITALIC, 24));
        this.mana.setForeground(new Color(0, 0, 0));
        this.mana.setBounds(6, 30, 110, 40);
        this.add(this.mana);
    }

    /**
     * Metóda slúži na prekreslenie hodnoty života a many hrdinu.
     * @param hrac hráč, ktorému sa majú dané veci prekresliť.
     */
    public void obnovZivotMana(Hrac hrac) {
        this.zivot.setText("Zivot: " + hrac.getHrdina().getZivot());
        this.mana.setText("Mana: " + hrac.getMana().getAktualnaMana());
        this.repaint();
    }

    /**
     * Abstraktná metóda slúži na použitie schopnosti hrdinu.
     * @param hrac hráč, s ktorým sa má vykonať daná akcia
     */
    public abstract void pouziSchopnost(Hrac hrac);
}
