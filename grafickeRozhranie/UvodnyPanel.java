package grafickeRozhranie;

import logika.StatistikyHry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

/**
 * Trieda vytvára úvodný panel, ktorý slúži na zapnutie hry, odchod alebo na prehľad štatistík hry.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class UvodnyPanel extends JPanel {
    private HraciPanel hraciPanel;

    /**
     * Konštruktor vytvorí úvodnú plochu hry.
     * @param okno používa sa prekreslenie okna v prípade stlačenia tlačidla, ktorým chceme hru zapnúť.
     * @param statistikyHry slúži na prácu so štatistikami hry
     */
    public UvodnyPanel(JFrame okno, StatistikyHry statistikyHry) {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(new Color(223, 183, 137));

        this.vytvorTlacitkoHraj(okno, statistikyHry);
        this.vytvorTlacitkoKoniec();
        this.vytvorTalictkoStatistiky(statistikyHry);

        this.setVisible(true);
    }

    /**
     * Metóda vytvorí tlačidlo, ktoré vytvorí novú partiu hry.
     * @param okno slúži na odstránenie tohto panelu a pridanie panelu novej hry
     * @param statistikyHry slúži na zápis výhercu
     */
    private void vytvorTlacitkoHraj(JFrame okno, StatistikyHry statistikyHry) {
        JButton hraj = new JButton("Hraj");
        hraj.setPreferredSize(new Dimension(150, 40));
        hraj.setFont(new Font("Bold", Font.BOLD, 25));
        hraj.setFocusPainted(false);
        hraj.setOpaque(false);

        hraj.addActionListener(e -> {
            this.hraciPanel = new HraciPanel(okno, this, statistikyHry);
            okno.remove(this);
            okno.add(this.hraciPanel.getPanel());
            okno.pack();
            okno.revalidate();
        });

        this.add(hraj);
    }

    /**
     * Metóda vytvorí tlačidlo, ktoré ukončí hru.
     */
    private void vytvorTlacitkoKoniec() {
        JButton koniec = new JButton("Koniec");
        koniec.setPreferredSize(new Dimension(150, 40));
        koniec.setFont(new Font("Bold", Font.BOLD, 25));
        koniec.setFocusPainted(false);
        koniec.setOpaque(false);

        koniec.addActionListener(e -> {
            System.exit(0);
        });

        this.add(koniec);
    }

    /**
     * Metóda vytvorí tlačidlo, ktoré slúži na zobrazenie štatistiky hry.
     * @param statistikyHry slúži na načítanie a výpis štatistiky
     */
    private void vytvorTalictkoStatistiky(StatistikyHry statistikyHry) {
        JButton statistiky = new JButton("Štatistiky");
        statistiky.setPreferredSize(new Dimension(150, 40));
        statistiky.setFont(new Font("Bold", Font.BOLD, 25));
        statistiky.setOpaque(false);
        statistiky.setFocusPainted(false);

        statistiky.addActionListener(e -> {
            statistikyHry.nacitaj();
            statistikyHry.vypisStatistiky();
        });

        this.add(statistiky);
    }
}