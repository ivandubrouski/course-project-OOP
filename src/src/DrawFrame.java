package src;

import javax.swing.*;


public class DrawFrame extends JFrame
{
    private static DrawFrame drawFrame;
    public static final int WIDTH = 307;
    public static final int HEIGHT = 327;

    private DrawFrame()
    {
        setTitle("Tic-Tac-Toe");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        Game game = new Game();
        add(game);
    }

    public static synchronized DrawFrame getDrawFrame() {
        if (drawFrame == null) {
            drawFrame = new DrawFrame();
        }
        return drawFrame;
    }
}