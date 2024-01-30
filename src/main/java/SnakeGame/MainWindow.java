package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private int mWidth = 800;
    private int mHeight = 800;

    private static MainWindow mWindow = null;

    private MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//pencere kapatılırsa programa da kapatma mesajı gönerir
        setDimension(mWidth, mHeight);
        setResizable(false); //oyun penceresinin büyütülmemesini ve sabit kalmasını sağlar

        Snake snake = new Snake();
        add(snake);
    }

   // Yalnızca bir tane pencere oluştrulmasını sağlar
    public static MainWindow Window() {
        if (mWindow == null)
            mWindow = new MainWindow();
        return mWindow;
    }

    private void setDimension(int width, int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int posX = Math.max(0, (screenSize.width - width) / 2);
        int posY = Math.max(0, (screenSize.height - height) / 2);


        setSize(Math.min(width, screenSize.width - 100), Math.min(height, screenSize.height - 100));

        setLocation(posX, posY);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
