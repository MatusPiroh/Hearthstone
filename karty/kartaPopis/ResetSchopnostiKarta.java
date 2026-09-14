package karty.kartaPopis;

import hrac.Hrac;

/**
 * Trieda dedí od triedy KartaPopis a predstavuje konkrétnu kartu, ktorá nastaví, že hráč môže znova použiť schopnosť hrdinu.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class ResetSchopnostiKarta extends KartaPopis {
    /**
     * Konštruktor využíva konštruktor predka na vytvorenie karty podľa zadaného typu.
     */
    public ResetSchopnostiKarta() {
        super(TypKartaPopis.RESET_SCHOPNOST_KARTA);
    }

    /**
     * Metóda nastaví, že hráč môže znova použiť schopnosť hrdinu.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        hrac1.getHrdina().setMozemZahrat(true);
    }
}
