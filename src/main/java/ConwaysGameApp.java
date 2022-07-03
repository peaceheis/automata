import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ConwaysGameApp extends Thread {
    public void run() {
        int x_size = 300;
        int y_size = 256;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(x_size*GameOfLife.PIXEL_SIZE, y_size*GameOfLife.PIXEL_SIZE);

        boolean[][] pixels = new boolean[y_size][x_size];
        Random random = new Random();

        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                boolean bool = (random.nextInt(25) == 1);
                pixels[y][x] = bool;
            }
        }
        GameOfLife conways = new GameOfLife(pixels, x_size, y_size);
        while (true) {
            JPanel pane = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(conways.tick(), 0, 0, null);
                }
           };

            frame.add(pane);
            frame.setVisible(true);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }


    }
    public static void main(String[] args) throws IOException {
        ConwaysGameApp app = new ConwaysGameApp();
        app.start();
    }
}