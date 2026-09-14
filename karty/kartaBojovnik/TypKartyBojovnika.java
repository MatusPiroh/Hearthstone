package karty.kartaBojovnik;

/**
 * V triede sa definujú typy karty pre bojovníkov.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public enum TypKartyBojovnika {
    ZAKLADNY_MURLOCK("Modra", 1, 2, 1, "/01_zakladny_murlok.png"),
    EPICKY_MURLOCK("Zelena", 4, 6, 1, "/02_epicky_murlok.png"),
    LEGENDARNY_MURLOCK("Zlata", 7, 9, 4, "/03_legendarny_murlok.png"),
    ZAKLADNY_GOBLIN("Modra", 1, 1, 1, "/04_zakladny_goblin.png"),
    EPICKY_GOBLIN("Zelena", 3, 2, 5, "/05_epicky_goblin.png"),
    LEGENDARNY_GOBLIN("Zlata", 6, 3, 10, "/06_legendarny_goblin.png"),
    ODVAZNY_RYTIER("Zlata", 10, 10, 10, "/07_odvazny_rytier.png"),
    ZAKLADNY_TROL("Modra", 2, 3, 2, "/08_zakladny_trol.png"),
    EPICKY_TROL("Zelena", 5, 5, 5, "/09_epicky_trol.png"),
    LEGENDARNY_TROL("Zlata", 8, 9, 7, "/10_legendarny_trol.png");

    private final String farba;
    private final String cestaObrazok;
    private final int zivot;
    private final int utok;
    private final int cena;

    /**
     * Koštruktor inicializuje údaje o kartách.
     * @param farba predstavuje farbu karty
     * @param cena predstavuje cenu karty
     * @param utok predstavuje útok budúceho bojovníka
     * @param zivot predstavuje život budúceho bojovníka
     * @param cestaObrazok predstavuje cestu k obrázku
     */
    TypKartyBojovnika(String farba, int cena, int utok, int zivot, String cestaObrazok) {
        this.farba = farba;
        this.cena = cena;
        this.utok = utok;
        this.zivot = zivot;
        this.cestaObrazok = cestaObrazok;
    }

    /**
     * Vracia farbu karty.
     * @return farba karty
     */
    public String getFarba() {
        return this.farba;
    }

    /**
     * Vracia cenu karty.
     * @return cena karty
     */
    public int getCena() {
        return this.cena;
    }

    /**
     * Vracia útok budúceho bojovníka.
     * @return útok budúceho bojovníka
     */
    public int getUtok() {
        return this.utok;
    }

    /**
     * Vracia život budúceho bojovníka.
     * @return život budúceho bojovníka
     */
    public int getZivot() {
        return this.zivot;
    }

    /**
     * Vracia cestu k obrázku.
     * @return cesta o obrázku
     */
    public String getCestaObrazok() {
        return this.cestaObrazok;
    }
}
