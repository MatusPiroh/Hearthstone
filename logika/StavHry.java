package logika;

/**
 * Trieda definuje výpis pre výhercu hry.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public enum StavHry {
    VYHRAL_HRAC_1("Vyhral hráč 1. Gratulujem"),
    VYHRAL_PROTIHRAC("Vyhral protihráč. Gratulujem");

    private final String popis;

    /**
     * Konštruktor načíta z parametra popis pre výhercu.
     * @param popis na načítanie popisu
     */
    StavHry(String popis) {
        this.popis = popis;
    }

    /**
     * Metóda vracia popis.
     * @return popis výhercu
     */
    public String getPopis() {
        return this.popis;
    }
}
