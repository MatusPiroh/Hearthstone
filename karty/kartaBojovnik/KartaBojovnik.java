package karty.kartaBojovnik;

import zranitelny.Bojovnik;
import hrac.Hrac;
import karty.Karta;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Trieda vytvára karty, ktoré budú vytvárať bojovníka po zahraní.
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class KartaBojovnik extends Karta {
    private final int utok;
    private final int zivot;
    private final String cestaObrazok;

    /**
     * Konštruktor načíta typ karty a podľa neho vytvorí danú kartu.
     * @param typKartyBojovnika slúži na načítanie dát o type karty
     */
    public KartaBojovnik(TypKartyBojovnika typKartyBojovnika) {
        super(typKartyBojovnika.getFarba(), typKartyBojovnika.getCena());

        this.cestaObrazok = typKartyBojovnika.getCestaObrazok();
        this.utok = typKartyBojovnika.getUtok();
        this.zivot = typKartyBojovnika.getZivot();

        JLabel utokLabel = new JLabel("Utok: " + this.utok);
        utokLabel.setFont(new Font("Bold", Font.BOLD, 20));
        utokLabel.setBounds(5, 35, 90, 30);
        this.add(utokLabel);

        JLabel zivotLabel = new JLabel("Zivot: " + this.zivot);
        zivotLabel.setFont(new Font("Bold", Font.BOLD, 20));
        zivotLabel.setBounds(5, 55, 90, 30);
        this.add(zivotLabel);
    }

    /**
     * Metóda pridá nového bojovníka do zoznamu bojovníkov hráča
     * @param hrac1 na načítanie hráča, s ktorým sa má daná akcia udiať
     */
    @Override
    public void zahraj(Hrac hrac1) {
        hrac1.getZoznamBojovnikov().add(new Bojovnik(this.utok, this.zivot, this.cestaObrazok, false));
    }
}
