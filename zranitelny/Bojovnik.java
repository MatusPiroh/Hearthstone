package zranitelny;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Objects;

/**
 * Trieda vytvára bojovníka, s ktorým sa bude dať útočit.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class Bojovnik extends Stvorenie {
    private final int utok;
    private final JLabel utokLabel;
    private final JLabel zivotLabel;
    private boolean taunt;

    /**
     * Konštruktor načíta útok a život z parametra a nastaví veľkosť karty.
     * @param utok predstavuje útok bojovníka
     * @param zivot predstavuje život bojovníka
     * @param cestaKObrazku predstavuje cestu k obrázku
     * @param taunt zisťuje či bojovník bude alebo nebude taunt
     */
    public Bojovnik(int utok, int zivot, String cestaKObrazku, boolean taunt) {
        super(zivot);
        this.utok = utok;
        this.taunt = taunt;
        this.setPreferredSize(new Dimension(120, 120));

        this.setBackground(this.getFarba());
        this.zivotLabel = new JLabel();
        this.zivotLabel.setFont(new Font("Italic", Font.ITALIC, 20));
        this.zivotLabel.setForeground(new Color(0, 0, 0));
        if (this.getZivot() > 9) {
            this.zivotLabel.setBounds(90, 75, 50, 30);
        } else {
            this.zivotLabel.setBounds(101, 75, 50, 30);
        }
        this.add(this.zivotLabel);

        this.utokLabel = new JLabel("");
        this.utokLabel.setFont(new Font("Italic", Font.ITALIC, 20));
        this.utokLabel.setForeground(new Color(0, 0, 0));
        this.utokLabel.setBounds(5, 75, 50, 30);
        this.add(this.utokLabel);
        this.obnovUtokZivot();

        ImageIcon obrazok = new ImageIcon(Objects.requireNonNull(getClass().getResource(cestaKObrazku)));
        JLabel ikona = new JLabel(obrazok);
        ikona.setBounds(10, 20, 100, 50);
        this.add(ikona);
    }

    /**
     * Vráti farbu podľa toho, či môžeme alebo nemôžeme zahrať hrdinu.
     * @return farba karty
     */
    public Color getFarba() {
        if (this.jeTaunt()) {
            return Color.GREEN;
        } else if (this.mozemZahrat()) {
            return Color.WHITE;
        } else {
            return Color.MAGENTA;
        }
    }

    /**
     * Metóda vráti to, či daný bojovník je, alebo nie je taunt.
     * @return či je alebo nie je taunt
     */
    public boolean jeTaunt() {
        return this.taunt;
    }

    /**
     * Metóda vráti veľkosť útoku bojovníka
     * @return veľkosť útoku bojovníka
     */
    public int getUtok() {
        return this.utok;
    }

    /**
     * Metóda obnoví vykreslenie útoku a života bojovníka, podľa aktuálnych hodnôt života a útoku.
     */
    public void obnovUtokZivot() {
        this.setBackground(this.getFarba());
        this.zivotLabel.setText("" + this.getZivot());
        this.utokLabel.setText("" + this.utok);
    }

    /**
     * Metóda nastavuje to, či bojovník je alebo nie je taunt.
     * @param taunt na natavenie taunta
     */
    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }
}
