package grafickeRozhranie;

import logika.Inicializacia;
import logika.StatistikyHry;
import vynimky.NespravnaAkciaException;
import zranitelny.Bojovnik;
import hrac.Hrac;
import karty.Karta;
import karty.kartaBojovnik.KartaBojovnik;
import logika.Hra;
import zranitelny.hrdinovia.Hrdina;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Trieda vytvára panel, ktorý slúži, ako hracia plocha pre hráčov.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class HraciPanel {
    private JPanel hlavnyPanel;
    private Hra hra;
    private JPanel hrac1Karty;
    private JPanel hrac1Bojovnici;
    private JPanel protivnikBojovnici;
    private JPanel protivnikKarty;
    private final JPanel uvodnyPanel;
    private final JFrame jFrame;

    /**
     * Konštruktor vytvára celkové zoskupenie panelov, v ktorých nastavuje ich veľkosť, farbu, a umiestnenie.
     * @param okno prestavuje okno, kde sá má trieda vykresliť.
     * @param panel panel na vykreslenie
     * @param statistikyHry predstavuje štatistiky hry
     */
    public HraciPanel(JFrame okno, JPanel panel, StatistikyHry statistikyHry) {
        boolean dobreInicializovane = false;
        while (!dobreInicializovane) {
            try {
                var inicializacia = new Inicializacia();
                dobreInicializovane = true;
                this.hlavnyPanel = new JPanel();
                this.hra = new Hra(statistikyHry, inicializacia);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this.hlavnyPanel, "Zle nainicializované údaje, skús ešte raz :)");
            }
        }

        this.jFrame = okno;
        this.uvodnyPanel = panel;

        this.hlavnyPanel.setLayout(new BorderLayout());
        this.hlavnyPanel.setBackground(new Color(223, 183, 137));
        this.vytvorGUI();

        this.obnov(this.hra.getAktualnyHrac());
    }



    /**
     * Pomocná metóda postupne vytvára jednotlivé časti hracej plochy.
     */
    private void vytvorGUI() {
        //hrac1 panel
        JPanel panelHrac1 = new JPanel();
        panelHrac1.setLayout(new BoxLayout(panelHrac1, BoxLayout.Y_AXIS));
        panelHrac1.setOpaque(false);

        Hrdina hrdinaHrac1 = this.vytvorHracTlacitko(this.hra.getAktualnyHrac());
        JPanel prvyRiadok = new JPanel(new FlowLayout(FlowLayout.CENTER));
        prvyRiadok.setOpaque(false);
        prvyRiadok.add(hrdinaHrac1);
        panelHrac1.add(prvyRiadok);

        this.hrac1Karty = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.hrac1Karty.setOpaque(false);
        this.hrac1Karty.setPreferredSize(new Dimension(900, 120));
        panelHrac1.add(this.hrac1Karty);

        this.hrac1Bojovnici = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.hrac1Bojovnici.setOpaque(false);
        this.hrac1Bojovnici.setPreferredSize(new Dimension(900, 120));
        panelHrac1.add(this.hrac1Bojovnici);

        this.hlavnyPanel.add(panelHrac1, BorderLayout.NORTH);

        //stred
        JPanel panelPreUkoncenieKola = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelPreUkoncenieKola.setPreferredSize(new Dimension(190, 70));
        panelPreUkoncenieKola.setOpaque(false);

        JButton ukoncenieKola = this.vytvorTlacitkoUkoncenia();
        ukoncenieKola.addActionListener(e -> {
            this.hra.ukonciKolo();
            for (Karta karta : this.hra.getAktualnyHrac().getZoznamKariet()) {
                if (karta != null) {
                    if (karta.getActionListeners().length == 0) {
                        this.pridajKartu(karta, this.hra.getAktualnyHrac());
                    }
                }
            }
            this.obnov(this.hra.getAktualnyHrac());
        });
        panelPreUkoncenieKola.add(ukoncenieKola);

        this.hlavnyPanel.add(panelPreUkoncenieKola, BorderLayout.CENTER);

        //Panel protivníka
        JPanel panelProtivnik = new JPanel();
        panelProtivnik.setLayout(new BoxLayout(panelProtivnik, BoxLayout.Y_AXIS));
        panelProtivnik.setOpaque(false);

        this.protivnikBojovnici = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.protivnikBojovnici.setOpaque(false);
        this.protivnikBojovnici.setPreferredSize(new Dimension(900, 120));
        panelProtivnik.add(this.protivnikBojovnici);

        this.protivnikKarty = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.protivnikKarty.setOpaque(false);
        this.protivnikKarty.setPreferredSize(new Dimension(900, 120));
        panelProtivnik.add(this.protivnikKarty);

        Hrdina hrdinaProtivnik = this.vytvorHracTlacitko(this.hra.getProtivnik());
        JPanel poslednyRiadok = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        poslednyRiadok.setOpaque(false);
        poslednyRiadok.add(hrdinaProtivnik);
        panelProtivnik.add(poslednyRiadok);

        this.hlavnyPanel.add(panelProtivnik, BorderLayout.SOUTH);
    }

    /**
     * Pomocná metóda vytvorí tlačidlo, ktoré po kliknutí vykoná schopnosť hrdinu.
     * @param hrac na zistenie hrdinu a jeho následné vykreslenie
     * @return vráti hrdinu, aj s funkciami
     */
    private Hrdina vytvorHracTlacitko(Hrac hrac) {
        Hrdina tlacitko = hrac.getHrdina();
        tlacitko.obnovZivotMana(hrac);
        tlacitko.addActionListener(e -> {
            if (this.hra.getUtociaciBojovnik() != null) {
                this.hra.vyberCiel(tlacitko);
                this.obnov(this.hra.getAktualnyHrac());
                return;
            }
            try {
                this.hra.pouziSchopnost(hrac);
            } catch (NespravnaAkciaException ex) {
                JOptionPane.showMessageDialog(this.hlavnyPanel, ex.getMessage());
            }
            this.obnov(this.hra.getAktualnyHrac());
        });

        return tlacitko;
    }

    /**
     * Vytvorí tlačidlo, ktoré slúži na ukončenie kola.
     * @return tlačidlo na ukončenie kola
     */
    private JButton vytvorTlacitkoUkoncenia() {
        JButton tlacitko = new JButton();
        tlacitko.setBackground(new Color(191, 182, 211));
        tlacitko.setPreferredSize(new Dimension(190, 60));
        tlacitko.setBorderPainted(false);
        tlacitko.setFocusPainted(false);
        JLabel text = new JLabel("Ukončenie kola");
        text.setFont(new Font("Bold", Font.BOLD, 20));
        text.setForeground(Color.black);
        tlacitko.add(text);
        return tlacitko;
    }

    /**
     * Metóda obnovuje počet tlačidiel v jednotlivých paneloch, zobrazuje karty hráča, ktorý je na ťahu a obnovuje stav hrdinov.
     * @param hrac1 hovorí, čie karty a bojovníci sa majú sprístupniť.
     */
    public void obnov(Hrac hrac1) {
        this.hrac1Karty.removeAll();
        this.protivnikKarty.removeAll();
        this.hrac1Bojovnici.removeAll();
        this.protivnikBojovnici.removeAll();

        for (Karta karta : hrac1.getZoznamKariet()) {
            if (hrac1 == this.hra.getHornyHrac()) {
                this.hrac1Karty.add(karta);
            } else {
                this.protivnikKarty.add(karta);
            }

        }

        for (Bojovnik bojovnik1: this.hra.getAktualnyHrac().getZoznamBojovnikov()) {
            if (bojovnik1 != null) {
                bojovnik1.obnovUtokZivot();
                if (bojovnik1 == this.hra.getUtociaciBojovnik()) {
                    bojovnik1.setBackground(Color.CYAN);
                }
                if (this.hra.getAktualnyHrac() == this.hra.getHornyHrac()) {
                    this.hrac1Bojovnici.add(bojovnik1);
                } else {
                    this.protivnikBojovnici.add(bojovnik1);
                }
            }
        }

        for (Bojovnik bojovnik2: this.hra.getProtivnik().getZoznamBojovnikov()) {
            bojovnik2.obnovUtokZivot();
            if (bojovnik2 == this.hra.getUtociaciBojovnik()) {
                bojovnik2.setBackground(Color.CYAN);
            }
            if (this.hra.getProtivnik() == this.hra.getHornyHrac()) {
                this.hrac1Bojovnici.add(bojovnik2);
            } else {
                this.protivnikBojovnici.add(bojovnik2);
            }
        }

        this.hra.getAktualnyHrac().getHrdina().obnovZivotMana(this.hra.getAktualnyHrac());
        this.hra.getProtivnik().getHrdina().obnovZivotMana(this.hra.getProtivnik());

        var vyherca = this.hra.vratVyhercu();
        if (vyherca.isPresent()) {
            JOptionPane.showMessageDialog(this.hlavnyPanel, vyherca.get().getPopis());
            this.jFrame.remove(this.hlavnyPanel);
            this.jFrame.add(this.uvodnyPanel);
            this.jFrame.pack();
            this.jFrame.revalidate();
        }

        this.hlavnyPanel.revalidate();
        this.hlavnyPanel.repaint();
    }


    /**
     * Metóda vytvára tlačidlo bojovníka aj s jeho funkciami.
     * @param bojovnik bojovník na pridanie
     * @param hrac hráč, ktorému sa má bojovník pridať
     */
    public void pridajBojovnika(Bojovnik bojovnik, Hrac hrac) {
        bojovnik.addActionListener(e -> {
            this.hra.vyberUtocnika(bojovnik);
            this.hra.vyberCiel(bojovnik);
            this.obnov(this.hra.getAktualnyHrac());
        });
        bojovnik.addMouseListener(new MouseAdapter() {
            /**
             * Po prejdení myšky na bojovníka sa bojovník posunie.
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                if (HraciPanel.this.hra.getAktualnyHrac().getZoznamBojovnikov().contains(bojovnik)) {
                    if (bojovnik.mozemZahrat()) {
                        bojovnik.setBounds(bojovnik.getX(), bojovnik.getY() - 10, bojovnik.getWidth(), bojovnik.getHeight());
                        HraciPanel.this.hlavnyPanel.repaint();
                    }
                }
            }
            /**
             * Po odídení myšky z bojovníka sa bojovník posunie.
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                if (HraciPanel.this.hra.getAktualnyHrac().getZoznamBojovnikov().contains(bojovnik)) {
                    if (bojovnik.mozemZahrat()) {
                        bojovnik.setBounds(bojovnik.getX(), bojovnik.getY() + 10, bojovnik.getWidth(), bojovnik.getHeight());
                        HraciPanel.this.hlavnyPanel.repaint();
                    }
                }
            }
        });
        if (hrac == this.hra.getHornyHrac()) {
            this.hrac1Bojovnici.add(bojovnik);
        } else {
            this.protivnikBojovnici.add(bojovnik);
        }
        this.obnov(this.hra.getAktualnyHrac());

    }

    /**
     * Metóda pridá kartu do panelu hráča, ktorý je aktuálne na ťahu, ak je karta typu KartaBojovnik, tak zavolá metódu na pridanie bojovníka do panelu bojovníkov.
     * @param karta karta, ktorá sa má pridať
     * @param hrac hráč, ktorému sa má karta pridať
     */
    public void pridajKartu(Karta karta, Hrac hrac) {
        karta.addActionListener(e -> {
            try {
                this.hra.zahrajKartu(hrac, karta);
                if (karta instanceof KartaBojovnik) {
                    for (Bojovnik bojovnik : hrac.getZoznamBojovnikov()) {
                        if (bojovnik.getActionListeners().length == 0) {
                            this.pridajBojovnika(bojovnik, hrac);
                        }
                    }
                }
            } catch (NespravnaAkciaException ex) {
                JOptionPane.showMessageDialog(this.hlavnyPanel, ex.getMessage());
            }
            this.obnov(this.hra.getAktualnyHrac());
        });

        karta.addMouseListener(new MouseAdapter() {
            /**
             * Po prejdení myšky na kartu sa karta posunie.
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hrac.getMana().getAktualnaMana() >= karta.getCenaKarty()) {
                    karta.setBounds(karta.getX(), karta.getY() - 10, karta.getWidth(), karta.getHeight());
                    HraciPanel.this.hlavnyPanel.repaint();
                }
            }
            /**
             * Po odídení myšky z karty sa karta posunie.
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e) {
                if (hrac.getMana().getAktualnaMana() >= karta.getCenaKarty()) {
                    karta.setBounds(karta.getX(), karta.getY() + 10, karta.getWidth(), karta.getHeight());
                    HraciPanel.this.hlavnyPanel.repaint();
                }
            }
        });

        if (hrac == this.hra.getAktualnyHrac()) {
            this.hrac1Karty.add(karta);
        } else {
            this.protivnikKarty.add(karta);
        }

        this.obnov(this.hra.getAktualnyHrac());
    }

    public JPanel getPanel() {
        return this.hlavnyPanel;
    }
}
