package zranitelny.hrdinovia;

import hrac.Hrac;

/**
 * Trieda dedí od triedy Hrdina a predstavuje konkrétneho hrdinu.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Mage extends Hrdina {
    /**
     * Konštruktor využíva konštruktor predka a nastaví hodnotu života.
     */
    public Mage() {
        super(30);
    }

    /**
     * Metóda zvýši manu hráčovi z parametra a zruší nabitie hrdinu.
     * @param hrac hráč, s ktorým sa má vykonať daná akcia
     */
    @Override
    public void pouziSchopnost(Hrac hrac) {
        hrac.getMana().zmenManu(2);
        this.setMozemZahrat(false);
    }
}
