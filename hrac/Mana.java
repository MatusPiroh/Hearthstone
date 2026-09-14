package hrac;

/**
 * Trieda slúži na prácu s manou.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Mana {
    private int mana;

    /**
     * Konštruktor nastavý hodnotu many.
     */
    public Mana() {
        this.mana = 1;
    }

    /**
     * Metóda vráti aktuálnu hodnotu many.
     * @return aktuálna hodnota many
     */
    public int getAktualnaMana() {
        return this.mana;
    }

    /**
     * Metóda slúži na zmenu veľkosti many o hodnotu z parametra.
     * @param oKolko číslo o, ktoré sa zmení hodnota many
     */
    public void zmenManu(int oKolko) {
        this.mana += oKolko;
    }

    /**
     * Metóda nastaví hodnotu many na hodnotu parametra.
     * @param kolko číslo na, ktoré sa má zmeniť mana.
     */
    public void setMana(int kolko) {
        this.mana = kolko;
    }
}
