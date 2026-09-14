package hrac;

import java.util.ArrayList;
import java.util.List;

/**
 * Generická trieda pre vytvorenie zoznamov.
 * @param <E> Typ predmetu v zozname
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class VseobecnyZoznam<E> {
    private static final int MAX_POCET = 6;
    private final ArrayList<E> zoznam;

    /**
     * Konštruktor vytvorí nový ArrayList.
     */
    public VseobecnyZoznam() {
        this.zoznam = new ArrayList<>();
    }

    /**
     * Metóda vráti zoznam položiek.
     * @return vráti List.
     */
    public List<E> getZoznam() {
        return this.zoznam;
    }

    /**
     * Metóda pridá položku do zoznamu.
     * @param polozka predstavuje položku, ktorá sa má pridať do zoznamu
     */
    public void pridaj(E polozka) {
        if (this.zoznam.size() < VseobecnyZoznam.MAX_POCET) {
            this.zoznam.add(polozka);
        }
    }

    /**
     * Metóda odstráni položku zo zoznamu.
     * @param polozka predstavuje položku, ktorá sa má odstrániť zo zoznamu
     */
    public void odober(E polozka) {
        for (int i = 0; i < VseobecnyZoznam.MAX_POCET; i++) {
            if (this.zoznam.get(i) == polozka) {
                this.zoznam.remove(polozka);
                break;
            }
        }
    }
}
