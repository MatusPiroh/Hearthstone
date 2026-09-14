package logika;

import karty.kartaPopis.KartaPopis;
import vynimky.NespravnaAkciaException;
import zranitelny.Bojovnik;
import hrac.Hrac;
import karty.Karta;
import zranitelny.Stvorenie;
import java.util.Optional;

/**
 * Trieda vytvára základnú hernú logiku.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Hra {
    private final StatistikyHry statistikyHry;
    private Hrac aktualnyHrac;
    private Hrac protivnik;
    private int pocitadloMany;
    private int pocetUkonciKola;
    private final Hrac hornyHrac;
    private Bojovnik utociaciBojovnik;
    private Stvorenie zranitelnyCiel;

    /**
     * Konštruktor vytvorí hráčov s ich hrdinami.
     * @param statistikyHry pre prácu so štatistikami hry
     * @param inicializacia na zistenie hrdinov pre jednotlivých hráčov.
     */
    public Hra(StatistikyHry statistikyHry, Inicializacia inicializacia) {
        this.aktualnyHrac = new Hrac(inicializacia.getHrac().getHrdina());
        this.protivnik = new Hrac(inicializacia.getProtihrac().getHrdina());
        this.statistikyHry = statistikyHry;
        this.hornyHrac = this.aktualnyHrac;
        this.pocitadloMany = 1;
        this.pocetUkonciKola = 1;
        this.utociaciBojovnik = null;
        this.zranitelnyCiel = null;
    }

    /**
     * Metóda vráti protivníka.
     * @return protivník
     */
    public Hrac getProtivnik() {
        return this.protivnik;
    }

    /**
     * Metóda vráti aktuálneho hráča
     * @return aktuálny hráč
     */
    public Hrac getAktualnyHrac() {
        return this.aktualnyHrac;
    }

    /**
     * Metóda vráti hráča, ktorý sa nachádza hore na ploche.
     * @return horný hráč
     */
    public Hrac getHornyHrac() {
        return this.hornyHrac;
    }

    /**
     * Metóda vráti útočiaceho bojovníka.
     * @return útočiaci bojovník
     */
    public Bojovnik getUtociaciBojovnik() {
        return this.utociaciBojovnik;
    }

    /**
     * Metóda ukončí kolo tak, že aktualizuje aktuálneho hráča, pridá mu manu a kartu, nakoniec nastaví jeho hrdinu, ako nabitého a jeho bojovníkov, ako hrateľných.
     */
    public void ukonciKolo() {
        var pomocna = this.aktualnyHrac;
        this.aktualnyHrac = this.protivnik;
        this.protivnik = pomocna;

        if (this.pocitadloMany >= 10) {
            this.aktualnyHrac.getMana().setMana(10);
        } else {
            if (this.pocetUkonciKola % 2 == 0) {
                this.pocitadloMany++;
            }
            this.aktualnyHrac.getMana().setMana(this.pocitadloMany);
        }

        var karta = this.aktualnyHrac.getBalicek().potiahniKartu();
        if (karta.isPresent()) {
            this.aktualnyHrac.pridajKartu(karta.get());
        } else {
            this.aktualnyHrac.getHrdina().zmenZivot(-3);
        }
        this.protivnik.getHrdina().setMozemZahrat(false);
        this.aktualnyHrac.getHrdina().setMozemZahrat(true);
        for (Bojovnik bojovnik : this.aktualnyHrac.getZoznamBojovnikov()) {
            bojovnik.setMozemZahrat(true);
        }
        if (this.utociaciBojovnik != null) {
            this.utociaciBojovnik = null;
        }
        this.pocetUkonciKola++;
    }

    /**
     * Metóda zisťuje či už sa hra skončila alebo nie.
     * @return vracia stavHry ak hra už skončila, inak prázdny Optional
     */
    public Optional<StavHry> vratVyhercu() {
        if (this.protivnik.getHrdina().getZivot() <= 0) {
            if (this.hornyHrac == this.protivnik) {
                this.statistikyHry.pridajVyhru(true);
                return Optional.of(StavHry.VYHRAL_PROTIHRAC);
            } else {
                this.statistikyHry.pridajVyhru(false);
                return Optional.of(StavHry.VYHRAL_HRAC_1);
            }
        } else if (this.aktualnyHrac.getHrdina().getZivot() <= 0) {
            if (this.hornyHrac == this.aktualnyHrac) {
                this.statistikyHry.pridajVyhru(true);
                return Optional.of(StavHry.VYHRAL_PROTIHRAC);
            } else {
                this.statistikyHry.pridajVyhru(false);
                return Optional.of(StavHry.VYHRAL_HRAC_1);
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Metóda určuje kedy a, ako sa karta môže zahrať.
     * @param hrac hrac, ktorého karta sa má zahrať
     * @param karta karta, ktorá sa má zahrať
     * @throws NespravnaAkciaException na ošetrenie stavu, kedy sa karta nemá zahrať-ak nie je na ťahu, nemá manu, alebo ak má plný zoznam bojovníkov
     */
    public void zahrajKartu(Hrac hrac, Karta karta) throws NespravnaAkciaException {
        if (hrac != this.aktualnyHrac) {
            throw new NespravnaAkciaException("Nie si na ťahu");
        }

        if (hrac.getMana().getAktualnaMana() < karta.getCenaKarty()) {
            throw new NespravnaAkciaException("Nemáš dostatok many");
        }

        if (hrac.getZoznamBojovnikov().size() == 6 && !(karta instanceof KartaPopis)) {
            throw new NespravnaAkciaException("Plný počet bojovníkov na hernej ploche");
        }

        hrac.getMana().zmenManu(-karta.getCenaKarty());
        hrac.odstranKartu(karta);
        karta.zahraj(this.aktualnyHrac);
    }

    /**
     * Metóda určuje kedy a, ako sa schopnosť môže použiť.
     * @param hrac hrac ktorého schopnosť sa má zahrať
     * @throws NespravnaAkciaException na ošetrenie stavu, kedy sa schopnosť nemá použiť-ak hráč neklikol na svojho hrdinu, alebo ak ho toto kolo už nemôže zahrať
     */
    public void pouziSchopnost(Hrac hrac) throws NespravnaAkciaException {
        if (hrac != this.aktualnyHrac) {
            throw new NespravnaAkciaException("Toto nie je tvoj hrdina");
        }

        if (!hrac.getHrdina().mozemZahrat()) {
            throw new NespravnaAkciaException("Toto kolo už znova nemôžeš použiť túto schopnosť");
        }
        hrac.getHrdina().pouziSchopnost(hrac);
    }

    /**
     * Metóda určuje kedy a, ako sa môže bojovník použiť.
     */
    public void zahrajBojovnika() {
        if (this.utociaciBojovnik != null && this.zranitelnyCiel != null) {
            if (this.zranitelnyCiel instanceof Bojovnik zranitelnyBojovnik) {
                this.utociaciBojovnik.zmenZivot(-zranitelnyBojovnik.getUtok());
                zranitelnyBojovnik.zmenZivot(-this.utociaciBojovnik.getUtok());
                if (this.utociaciBojovnik.getZivot() <= 0) {
                    this.aktualnyHrac.odstranBojovnika(this.utociaciBojovnik);
                }
                if (zranitelnyBojovnik.getZivot() <= 0) {
                    this.protivnik.odstranBojovnika(zranitelnyBojovnik);
                }
            } else {
                this.zranitelnyCiel.zmenZivot(-this.utociaciBojovnik.getUtok());
            }
            this.utociaciBojovnik.setMozemZahrat(false);
            this.utociaciBojovnik = null;
            this.zranitelnyCiel = null;
        }
    }

    /**
     * Metóda zisťuje, či aktuálny hráč môže vybrať bojovníka.
     * @param bojovnik bojovník na zistenie
     * */
    public void vyberUtocnika(Bojovnik bojovnik) {
        if (this.aktualnyHrac.getZoznamBojovnikov().contains(bojovnik)) {
            if (bojovnik.mozemZahrat()) {
                this.utociaciBojovnik = bojovnik;
            }
        }
    }

    /**
     * Metóda zisťuje, či hráč môže vybrať stvorenie z parametra.
     * @param stvorenie stvorenie na zistenie
     */
    public void vyberCiel(Stvorenie stvorenie) {
        if (stvorenie instanceof Bojovnik bojovnik) {
            if (this.protivnik.getZoznamBojovnikov().contains(bojovnik)) {
                if (bojovnik.jeTaunt()) {
                    this.zranitelnyCiel = stvorenie;
                    this.zahrajBojovnika();
                    return;
                }
                for (Bojovnik bojovnik1 : this.protivnik.getZoznamBojovnikov()) {
                    if (bojovnik1.jeTaunt()) {
                        return;
                    }
                }
                this.zranitelnyCiel = stvorenie;
                this.zahrajBojovnika();
                return;
            }
        }
        if (this.protivnik.getHrdina() == stvorenie) {
            this.zranitelnyCiel = stvorenie;
            this.zahrajBojovnika();
        }
    }

}
