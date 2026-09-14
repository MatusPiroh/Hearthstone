package hrac;

import karty.Karta;
import karty.kartaBojovnik.EpickyMurlock;
import karty.kartaBojovnik.Taunt;
import karty.kartaBojovnik.KartaBojovnik;
import karty.kartaBojovnik.TypKartyBojovnika;
import karty.kartaPopis.PlusDvaMana;
import karty.kartaPopis.PlusDvaZivot;
import karty.kartaPopis.PlusStyriMana;
import karty.kartaPopis.ResetSchopnostiKarta;
import karty.kartaPopis.SialenaKarta;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Trieda vytvára balíček kariet.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Balicek {

    private ArrayList<Karta> balicek;

    /**
     * Konštruktor vytvorí balíček, načíta doňho karty a zamieša ich.
     */
    public Balicek() {
        this.balicek = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            this.balicek.add(new KartaBojovnik(TypKartyBojovnika.ZAKLADNY_GOBLIN));
            this.balicek.add(new KartaBojovnik(TypKartyBojovnika.EPICKY_GOBLIN));
            this.balicek.add(new Taunt(TypKartyBojovnika.LEGENDARNY_GOBLIN));
            this.balicek.add(new KartaBojovnik(TypKartyBojovnika.ZAKLADNY_MURLOCK));
            this.balicek.add(new EpickyMurlock(TypKartyBojovnika.EPICKY_MURLOCK));
            this.balicek.add(new Taunt(TypKartyBojovnika.LEGENDARNY_MURLOCK));
            this.balicek.add(new Taunt(TypKartyBojovnika.ODVAZNY_RYTIER));
            this.balicek.add(new KartaBojovnik(TypKartyBojovnika.ZAKLADNY_TROL));
            this.balicek.add(new KartaBojovnik(TypKartyBojovnika.EPICKY_TROL));
            this.balicek.add(new Taunt(TypKartyBojovnika.LEGENDARNY_TROL));
            this.balicek.add(new SialenaKarta());
            this.balicek.add(new PlusDvaZivot());
            this.balicek.add(new PlusDvaMana());
            this.balicek.add(new ResetSchopnostiKarta());
            this.balicek.add(new PlusStyriMana());
        }
        this.zamiesaj();
    }

    /**
     * Metóda vráti poslednú kartu z balíčka, ak taká karta existuje.
     * @return vráti Optional, ktorý bude prázdny alebo bude mať v sebe nejakú kartu.
     */
    public Optional<Karta> potiahniKartu() {
        if (!this.balicek.isEmpty()) {
            var karta = Optional.of(this.balicek.getLast());
            this.balicek.remove(this.balicek.getLast());
            return karta;
        }
        return Optional.empty();
    }

    /**
     * Metóda slúži na zamiešanie kariet.
     */
    private void zamiesaj() {
        Random rand = new Random();
        var pomocna = new ArrayList<Karta>();
        var velkostBalicku = this.balicek.size() - 1;
        for (int i = 0; i < velkostBalicku; i++) {
            int nahodneCislo = rand.nextInt(this.balicek.size());
            pomocna.add(this.balicek.get(nahodneCislo));
            this.balicek.remove(this.balicek.get(nahodneCislo));
        }
        this.balicek = pomocna;
    }
}
