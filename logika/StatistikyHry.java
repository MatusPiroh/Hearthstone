package logika;

import javax.swing.JOptionPane;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Metóda slúži na zapisovanie a načítanie štatistiky zo súboru.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class StatistikyHry {
    private int hracVyhry;
    private int protihracVyhry;
    private static final String CESTA = "data.txt";

    /**
     * Konštruktor zavolá o načítanie dát zo súboru.
     */
    public StatistikyHry() {
        this.nacitaj();
    }

    /**
     * Metóda pridá výhru hráčovi a zavolá metódu na uloženie nových údajov.
     * @param protivnik na zistenie, ktorému hráčovi sa má výhra pridať
     */
    public void pridajVyhru(boolean protivnik) {
        if (protivnik) {
            this.protihracVyhry++;
        } else {
            this.hracVyhry++;
        }
        this.uloz();
    }

    /**
     * Metóda uloží počet výhier hráča a protihráča do súboru.
     */
    public void uloz() {
        try (DataOutputStream zapisovac = new DataOutputStream(new FileOutputStream(StatistikyHry.CESTA))) {
            zapisovac.writeInt(this.hracVyhry);
            zapisovac.writeInt(this.protihracVyhry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metóda načíta počet výhier hráča a protihráča zo súboru.
     */
    public void nacitaj() {
        File file = new File(StatistikyHry.CESTA);
        if (file.exists()) {
            try (DataInputStream citac = new DataInputStream(new FileInputStream(StatistikyHry.CESTA))) {
                this.hracVyhry = citac.readInt();
                this.protihracVyhry = citac.readInt();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Metóda vypíše aktuálne štatistiky hráčov pre užívateľov.
     */
    public void vypisStatistiky() {
        JOptionPane.showMessageDialog(null, "Hrac vyhral " + this.hracVyhry + " krát a Protivnik vyhral " + this.protihracVyhry + " krát");
    }
}
