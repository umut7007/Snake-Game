package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JLabel {
    public Box mHead = new Box();

    public Bait mBait = new Bait();
    public Random mRandom = null;
    public Timer mTimer = null;

    public ArrayList<Box> Liste = new ArrayList<Box>();

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10);
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(10));
        g2.draw(rect);
    }

    Snake() {
        mRandom = new Random(System.currentTimeMillis());
        addKeyListener(new KeyboardControl());

        setFocusable(true);
        mTimer = new Timer(110, new TimerControl());
        mTimer.start();

        Liste.add(mHead);
        for (int i = 1; i < 6; i++) {
            AddQueue();
        }
        add(mBait);
        add(mHead);

    }


    public void AddQueue() {
        Box Boxx = Liste.get(Liste.size() - 1).BoxFormation();
        Liste.add(Boxx);
        add(Boxx);
    }

    public void AddBait() {
        int Width = getWidth() - 30 - mBait.mWidht;
        int Height = getHeight() - 30 - mBait.mWidht;

        int PosX = 10 + Math.abs(mRandom.nextInt()) % Width;
        int PosY = 10 + Math.abs(mRandom.nextInt()) % Height;

        PosX = PosX - PosX % 20;
        PosY = PosY - PosY % 20;

        for (int i = 0; i < Liste.size(); i++) {
            if ((PosX == Liste.get(i).getX()) && (PosY == Liste.get(i).getY())) {
                AddBait();
                return;
            }
        }

        mBait.setPosition(PosX, PosY);
    }

    public void TotalMovement() {
        for (int i = Liste.size() - 1; i > 0; i--) {
            Box Former = Liste.get(i - 1);
            Box Next = Liste.get(i);

            Liste.get(i).Movement();
            Next.mDirection = Former.mDirection;
        }
        mHead.Movement();
    }

    public boolean Collision() {
        int border = 10;

        int width = getWidth();
        int height = getHeight();

        if (mHead.getX() <= border || mHead.getX() + mHead.getWidth() >= width - border) {
            return true;
        }

        if (mHead.getY() <= border || mHead.getY() + mHead.getHeight() >= height - border) {
            return true;
        }
        for (int i = 1; i < Liste.size(); i++) {
            int X = Liste.get(i).getX();
            int Y = Liste.get(i).getY();
            if ((X == mHead.getX()) && (Y == mHead.getY()))
                return true;
        }
        //Yem ve yılanın koordinatlarını kontrol eder
        if ((mBait.getX() == mHead.getX()) && (mBait.getY() == mHead.getY())) {
            AddQueue();
            AddBait();
        }
        return false;
    }

    //Yılanı kontrol etmek  için
    class KeyboardControl implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (mHead.mDirection != Direction.RIGHT)
                    mHead.mDirection = Direction.LEFT;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (mHead.mDirection != Direction.LEFT)
                    mHead.mDirection = Direction.RIGHT;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (mHead.mDirection != Direction.DOWN)
                    mHead.mDirection = Direction.UP;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (mHead.mDirection != Direction.UP)
                    mHead.mDirection = Direction.DOWN;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    class TimerControl implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TotalMovement();
            if (Collision())
                mTimer.stop();
        }

    }

}
