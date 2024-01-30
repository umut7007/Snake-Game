package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Box extends JLabel {

    public int mWidht = 20;
    public int mHeight = 20;
    public int mDirection = Direction.RIGHT;

    Box() {
        setBounds(100, 100, mWidht, mHeight);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(1, 1, getWidth()-2, getHeight()-2);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.draw(rect);
        g2.setColor(Color.red);
        g2.fill(rect);

    }

    public void GoLeft() {
        int PosX = getX();
        int PosY = getY();

        PosX -= mWidht;
        setBounds(PosX, PosY, mWidht, mHeight);
    }

    public void GoRight() {
        int PosX = getX();
        int PosY = getY();

        PosX += mWidht;
        setBounds(PosX, PosY, mWidht, mHeight);
    }

    public void GoDown() {
        int PosX = getX();
        int PosY = getY();

        PosY += mHeight;
        setBounds(PosX, PosY, mWidht, mHeight);
    }

    public void GoUp() {
        int PosX = getX();
        int PosY = getY();

        PosY -= mHeight;
        setBounds(PosX, PosY, mWidht, mHeight);
    }

    public Box BoxFormation() {
        Box Boxx = new Box();

        int X = getX();
        int Y = getY();

        Boxx.setBounds(X, Y, mWidht, mHeight);

        Boxx.mDirection = -mDirection;

        Boxx.Movement();

        Boxx.mDirection = mDirection;

        return Boxx;
    }


    public void Movement() {
        if (mDirection == Direction.LEFT)
            GoLeft();
        else if (mDirection == Direction.RIGHT) {
            GoRight();
        } else if (mDirection == Direction.DOWN) {
            GoDown();
        } else if (mDirection == Direction.UP) {
            GoUp();
        }
    }
}
