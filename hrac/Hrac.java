package hrac;

import zranitelny.hrdinovia.Hrdina;
import zranitelny.Bojovnik;
import karty.Karta;
import java.util.List;

/**
 * Trieda spája jednotlivé časti, z ktorých pozostáva hráč.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Hrac {
    private final Mana mana;
    private final Balicek balicek;
    private final VseobecnyZoznam<Karta> zoznamKariet;
    private final VseobecnyZoznam<Bojovnik> zoznamBojovnikov;
    private final Hrdina hrdina;

    /**
     * Konštruktor načíta hrdinu a vytvorí svoje jednotlivé časti.
     * @param hrdina predstavuje hrdinu hráča
     */
    public Hrac(Hrdina hrdina) {
        this.balicek = new Balicek();
        this.mana = new Mana();
        this.zoznamKariet = new VseobecnyZoznam<>();
        this.zoznamBojovnikov = new VseobecnyZoznam<>();
        this.hrdina = hrdina;
    }

    /**
     * Metóda vráti manu hráča.
     * @return mana hráča
     */
    public Mana getMana() {
        return this.mana;
    }

    /**
     * Metóda vráti balíček kariet.
     * @return balíček kariet
     */
    public Balicek getBalicek() {
        return this.balicek;
    }

    /**
     * Metóda vráti hrdinu hráča.
     * @return hrdina hráčov hrdina
     */
    public Hrdina getHrdina() {
        return this.hrdina;
    }

    /**
     * Metóda vráti zoznamu kariet hráča.
     * @return zoznam kariet hráča
     */
    public List<Karta> getZoznamKariet() {
        return this.zoznamKariet.getZoznam();
    }

    /**
     * Metóda vráti zoznamu bojovníkov hráča.
     * @return zoznam bojovníkov hráča
     */
    public List<Bojovnik> getZoznamBojovnikov() {
        return this.zoznamBojovnikov.getZoznam();
    }

    /**
     * Metóda pridá kartu do zoznamu kariet.
     * @param karta karta, ktorá sa pridá do zoznamu kariet
     */
    public void pridajKartu(Karta karta) {
        this.zoznamKariet.pridaj(karta);
    }

    /**
     * Metóda odstráni kartu zo zoznamu kariet.
     * @param karta karta na odstránenie
     */
    public void odstranKartu(Karta karta) {
        this.zoznamKariet.odober(karta);
    }

    /**
     * Metóda odstráni bojovníka zo zoznamu bojovníkov.
     * @param bojovnik predstavuje bojovníka na odstránenie
     */
    public void odstranBojovnika(Bojovnik bojovnik) {
        this.zoznamBojovnikov.odober(bojovnik);
    }
}
