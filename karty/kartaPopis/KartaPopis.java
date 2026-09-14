package karty.kartaPopis;

import karty.Karta;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Abstraktná trieda vytvára šablónu pre karty, ktoré budú mať v sebe popis a budú slúžiť, ako spelly.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public abstract class KartaPopis extends Karta {

    private final String popis;

    /**
     * Konštruktor načíta typ karty a podľa neho vytvorí danú kartu.
     * @param kartaPopis typ karty, ktorá má popis
     */
    public KartaPopis(TypKartaPopis kartaPopis) {
        super(kartaPopis.getFarba(), kartaPopis.getCenaKarty());
        this.popis = kartaPopis.getPopis();
    }

    /**
     * Metóda slúži na vykreslenie karty, metóda je kompletne vygenerovaná umelou inteligenciou.
     * https://chatgpt.com/c/6a02cff1-c9c8-8329-bc40-10619610ac73
     * @param g the <code>Graphics</code> object to protect, používa sa na získanie štetca a plátna na kreslenie
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g.create();
        int sirka = this.getWidth();
        int vyska = this.getHeight();

        int velkostFontu = 20;
        graphics2D.setFont(new Font("Bold", Font.BOLD, velkostFontu));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        while (fontMetrics.stringWidth(this.popis) > sirka - 10 && velkostFontu > 6) {
            velkostFontu--;
            graphics2D.setFont(new Font("Bold", Font.BOLD, velkostFontu));
            fontMetrics = graphics2D.getFontMetrics();
        }

        int x = (sirka - fontMetrics.stringWidth(this.popis)) / 2;
        int y = (vyska / 2) + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;

        graphics2D.setColor((new Color(0, 0, 0)));
        graphics2D.drawString(this.popis, x, y);

        graphics2D.dispose();
    }
}
