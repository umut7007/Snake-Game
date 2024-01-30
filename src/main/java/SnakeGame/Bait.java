package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bait extends JLabel {
    public int mWidht = 15;

    Bait() {
        setPosition(120, 120);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(12));
        g2.draw(rect);
        g2.setColor(Color.yellow);
        g2.fill(rect);

    }

    public void setPosition(int PosX, int PosY) {
        setBounds(PosX, PosY, mWidht, mWidht);
    }
}
