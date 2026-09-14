import grafickeRozhranie.UvodnyPanel;
import logika.StatistikyHry;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Hlavná trieda s main metódou, ktorá vytvorí okno a pridá do neho úvodný panel.
 *
 * @author Matúš Piroh
 */
void main() {
  SwingUtilities.invokeLater(() -> {
    try {
      UIManager.setLookAndFeel((UIManager.getSystemLookAndFeelClassName()));
    } catch (Exception ex) {
      System.err.println("Nastala chyba " + ex.getMessage());
    }
    javax.swing.JFrame okno = new JFrame("Hearthstone");
    okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    okno.add(new UvodnyPanel(okno, new StatistikyHry()));

    okno.pack();
    okno.setLocationRelativeTo(null);
    okno.setVisible(true);
  });
}
