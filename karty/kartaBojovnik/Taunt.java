package karty.kartaBojovnik;

import hrac.Hrac;

/**
 * Trieda dedí od triedy KartaBojovnik a predstavuje konkrétnu kartu, ktorá slúži na vytvorenie bojovníka.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Taunt extends KartaBojovnik {

    /**
     * Koštruktor využíva konštruktor predka a nastaví kartu, podľa jej typu.
     * @param typKartyBojovnika predstavuje typ karty, ktorý sa má vytvoriť
     */
    public Taunt(TypKartyBojovnika typKartyBojovnika) {
        super(typKartyBojovnika);
    }

    /**
     * Metóda vytvorí nového bojovníka, pridá ho do hráčovho zoznamu bojovníkov a nastaví ho na takzvaného taunta.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        super.zahraj(hrac1);
        hrac1.getMana().zmenManu(1);
        hrac1.getZoznamBojovnikov().getLast().setTaunt(true);
    }
}
