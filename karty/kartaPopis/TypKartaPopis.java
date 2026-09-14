package karty.kartaPopis;

/**
 * V triede sa definuje typ karty, ktorá má v sebe popis.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public enum TypKartaPopis {
    PLUS_DVA_MANA("Cervena", 0, "Pridá +2 many"),
    PLUS_DVA_ZIVOT("Cervena", 2, "Pridá +2 života"),
    PLUS_STYRI_MANA("Cervena", 2, "Pridá +4 many"),
    RESET_SCHOPNOST_KARTA("Zelena", 1, "Reset schopnosti"),
    SIALENA_KARTA("Zelena", 3, "Pridá náhodný život");


    private final String farba;
    private final int cenaKarty;
    private final String popis;

    /**
     * Konštruktor inicializuje údaje o kartách.
     * @param farba farba karty
     * @param cenaKarty cena karty
     * @param popis popis karty
     */
    TypKartaPopis(String farba, int cenaKarty, String popis) {
        this.farba = farba;
        this.cenaKarty = cenaKarty;
        this.popis = popis;
    }

    /**
     * Metóda vráti farbu karty.
     * @return farba karty
     */
    public String getFarba() {
        return this.farba;
    }

    /**
     * Metóda vráti cenu karty.
     * @return cena karty
     */
    public int getCenaKarty() {
        return this.cenaKarty;
    }

    /**
     * Metóda vráti popis karty
     * @return popis karty
     */
    public String getPopis() {
        return this.popis;
    }
}
