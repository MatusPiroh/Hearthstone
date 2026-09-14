package zranitelny.hrdinovia;

import hrac.Hrac;
import java.util.Random;

/**
 * Trieda dedí od triedy Hrdina a predstavuje konkrétneho hrdinu.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Druid extends Hrdina {
    /**
     * Koštruktor využíva konštruktor predka a nastaví hodnotu života.
     */
    public Druid() {
        super(20);
    }

    /**
     * Metóda zvýši život hrdinovi, odstráni náhodnú kartu hráčovi z ruky a zruší nabitie hrdinu.
     * @param hrac hráč, s ktorým sa má vykonať daná akcia
     */
    @Override
    public void pouziSchopnost(Hrac hrac) {
        this.zmenZivot(3);
        if (!hrac.getZoznamKariet().isEmpty()) {
            Random random = new Random();
            var cislo = random.nextInt(hrac.getZoznamKariet().size());
            hrac.getZoznamKariet().remove(cislo);
        }
        this.setMozemZahrat(false);
    }

}
