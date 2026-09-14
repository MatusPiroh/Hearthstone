package karty.kartaPopis;

import hrac.Hrac;

import java.util.Random;

/**
 * Trieda dedí od triedy KartaPopis a predstavuje konkrétnu kartu, ktorá pridá alebo odoberie život hráčovi.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class SialenaKarta extends KartaPopis {
    /**
     * Konštruktor využíva konštruktor predka na vytvorenie karty podľa zadaného typu.
     */
    public SialenaKarta() {
        super(TypKartaPopis.SIALENA_KARTA);
    }

    /**
     * Metóda vygeneruje náhodné číslo do 10, ak je vygenerované číslo párne tak hráčovi odoberie život, ale ak je nepárne tak hráčovi pridá život.
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        Random random = new Random();
        var cislo = random.nextInt(10);
        if (cislo % 2 == 0) {
            hrac1.getHrdina().zmenZivot(-cislo);
        } else {
            hrac1.getHrdina().zmenZivot(cislo);
        }
    }
}
