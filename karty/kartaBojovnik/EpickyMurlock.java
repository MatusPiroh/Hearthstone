package karty.kartaBojovnik;

import hrac.Hrac;

/**
 * Trieda dedí od KartaBojovnik a predstavuje konkrétnu kartu, ktorá slúži na vytvorenie bojovníka.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class EpickyMurlock extends KartaBojovnik {

    /**
     * Koštruktor využíva konštruktor predka a nastaví kartu, podľa jej typu.
     * @param typKartyBojovnika predstavuje typ karty, ktorý sa má vytvoriť
     */
    public EpickyMurlock(TypKartyBojovnika typKartyBojovnika) {
        super(typKartyBojovnika);
    }

    /**
     * Metóda vytvorí nového bojovníka a pridá ho do hráčovho zoznamu bojovníkov.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        super.zahraj(hrac1);
        hrac1.getHrdina().zmenZivot(1);
    }
}
