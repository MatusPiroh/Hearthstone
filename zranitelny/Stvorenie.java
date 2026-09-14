package zranitelny;

import javax.swing.JButton;

/**
 * Abstraktná trieda slúži, ako predloha pre triedy, ktoré budú pracovať zo životom.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public abstract class Stvorenie extends JButton {

    private int zivot;
    private boolean mozemZahrat;

    /**
     * Konštruktor načíta život a nastaví základné nastavenie pre správne fungovanie JButton.
     * @param zivot na načítanie života Stvorenia
     */
    public Stvorenie(int zivot) {
        this.zivot = zivot;
        this.mozemZahrat = false;
        this.setLayout(null);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setVisible(true);
        this.setEnabled(true);
    }

    /**
     * Metóda vráti život stvorenia.
     * @return život stvorenia
     */
    public int getZivot() {
        return this.zivot;
    }

    /**
     * Metóda zisťuje či môžeme alebo nemôžeme zahrať stvorenie.
     * @return možnosť zahrať hrdinu.
     */
    public boolean mozemZahrat() {
        return this.mozemZahrat;
    }

    /**
     * Metóda zmení život hrdinu o zadaný parameter.
     * @param kolko zisťuje o koľko sa má život zväčšiť.
     */
    public void zmenZivot(int kolko) {
        this.zivot += kolko;
    }

    /**
     * Metóda slúži na nastavenie toho, či sa stvorenie môže alebo nemôže zahrať.
     * @param mozem na nastavenie či sa stvorenie môže alebo nemôže zahrať
     */
    public void setMozemZahrat(boolean mozem) {
        this.mozemZahrat = mozem;
    }

}
