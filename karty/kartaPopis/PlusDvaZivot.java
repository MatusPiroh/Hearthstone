package karty.kartaPopis;

import hrac.Hrac;

/**
 * Trieda dedí od triedy KartaPopis a predstavuje konkrétnu kartu, ktorá pridá hráčovi život.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class PlusDvaZivot extends KartaPopis {

    /**
     * Konštruktor využíva konštruktor predka na vytvorenie karty podľa zadaného typu.
     */
    public PlusDvaZivot() {
        super(TypKartaPopis.PLUS_DVA_ZIVOT);
    }

    /**
     * Metóda zvýši život hrdinovi.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        hrac1.getHrdina().zmenZivot(2);
    }

}
