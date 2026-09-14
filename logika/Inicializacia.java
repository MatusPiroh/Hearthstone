package logika;

import hrac.Hrac;
import zranitelny.hrdinovia.Druid;
import zranitelny.hrdinovia.Hrdina;
import zranitelny.hrdinovia.Mage;
import zranitelny.hrdinovia.Warrior;

import javax.swing.JOptionPane;

/**
 * Trieda slúži na inicializáciu základných údajov, ako výber hrdinu a vytvorenie hráčov.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Inicializacia {
    private final Hrac hrac;
    private final Hrac protihrac;

    /**
     * Načíta hrdinov a vytvorí hráčov.
     * @throws IllegalArgumentException ak je zle zadané číslo hrdinu
     */
    public Inicializacia() throws IllegalArgumentException {
        var hrac1Hrdina = Integer.parseInt(JOptionPane.showInputDialog(null, "Hrac vyber si hrdinu 1 Druid, 2 Warrior, 3 Mage"));
        if (hrac1Hrdina != 1 && hrac1Hrdina != 2 && hrac1Hrdina != 3) {
            throw new IllegalArgumentException();
        }
        var protivnikHrdina = Integer.parseInt(JOptionPane.showInputDialog(null, "Protivnik vyber si hrdinu 1 Druid, 2 Warrior, 3 Mage"));
        if (protivnikHrdina != 1 && protivnikHrdina != 2 && protivnikHrdina != 3) {
            throw new IllegalArgumentException();
        }

        this.hrac = new Hrac(this.vyberHrdinu(hrac1Hrdina));
        this.protihrac = new Hrac(this.vyberHrdinu(protivnikHrdina));
    }

    /**
     * Pomocná metóda na vrátenie hrdinu na základne načítaného čísla.
     * @param cislo slúži na zistenie, ktorého hrdinu treba vytvoriť
     * @return vráti hrdinu
     */
    private Hrdina vyberHrdinu(int cislo) {
        if (cislo == 1) {
            return new Druid();
        } else if (cislo == 2) {
            return new Warrior();
        } else {
            return new Mage();
        }
    }

    /**
     * Metóda vráti hráča1.
     * @return vráti hráča
     */
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Metóda vráti protivníka.
     * @return vráti protivníka
     */
    public Hrac getProtihrac() {
        return this.protihrac;
    }
}
