package zranitelny.hrdinovia;

import hrac.Hrac;

import java.util.Random;

/**
 * Trieda dedí od triedy Hrdina a predstavuje konkrétneho hrdinu.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Warrior extends Hrdina {

    /**
     * Koštruktor využíva konštruktor predka a nastaví hodnotu života.
     */
    public Warrior() {
        super(25);
    }

    /**
     * Metóda zvýši život hrdinu, odstráni náhodného bojovníka hráča a zruší nabitie hrdinu.
     * @param hrac hráč, s ktorým sa má vykonať daná akcia
     */
    @Override
    public void pouziSchopnost(Hrac hrac) {
        this.zmenZivot(4);
        if (!hrac.getZoznamBojovnikov().isEmpty()) {
            Random random = new Random();
            var cislo = random.nextInt(hrac.getZoznamBojovnikov().size());
            hrac.getZoznamBojovnikov().remove(cislo);
        }
        this.setMozemZahrat(false);
    }
}
