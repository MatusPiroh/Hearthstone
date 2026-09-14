package karty.kartaPopis;

import hrac.Hrac;

/**
 * Trieda dedí od triedy KartaPopis a predstavuje konkrétnu kartu, ktorá pridá hráčovi manu.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class PlusDvaMana extends KartaPopis {

    /**
     * Konštruktor využíva konštruktor predka na vytvorenie karty podľa zadaného typu.
     */
    public PlusDvaMana() {
        super(TypKartaPopis.PLUS_DVA_MANA);
    }

    /**
     * Metóda zvýši manu hráča.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        hrac1.getMana().zmenManu(2);
    }

}
