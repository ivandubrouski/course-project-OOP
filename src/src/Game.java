package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;


public class Game extends JComponent
{
    private Rectangle2D[] gameField;
    public Rectangle2D[] getGameField() {
        return gameField;
    }

    public void setGameField(Rectangle2D[] gameField) {
        this.gameField = gameField;
    }


    public ArrayList<Line2D.Double> getXgame() {
        return X;
    }

    private ArrayList<Line2D.Double> X;

    public ArrayList<Ellipse2D.Double> getO() {
        return O;
    }

    public void setO(ArrayList<Ellipse2D.Double> o) {
        O = o;
    }

    private ArrayList<Ellipse2D.Double> O;

    public ArrayList<Integer> getUsedX() {
        return usedX;
    }

    public void setUsedX(ArrayList<Integer> usedX) {
        this.usedX = usedX;
    }

    private ArrayList<Integer> usedX;

    public ArrayList<Integer> getUsedO() {
        return usedO;
    }

    public void setUsedO(ArrayList<Integer> usedO) {
        this.usedO = usedO;
    }

    private ArrayList<Integer> usedO;
    private Line2D.Double lineThatWin;

    public int getRect() {
        return rect;
    }

    public void setRect(int rect) {
        this.rect = rect;
    }

    private int rect;

    public int getComputerTurn() {
        return computerTurn;
    }

    public void setComputerTurn(int computerTurn) {
        this.computerTurn = computerTurn;
    }

    private int computerTurn;

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }



    public void setX(ArrayList<Line2D.Double> x) {
        X = x;
    }

    private boolean computer;
    private int delay;
    private  CheckComputer checkComputer;
    private Caretaker caretaker;

    public Timer getTimer() {
        return timer;
    }

    private Timer timer;
    private BasicStroke bs;

    public Computer getComp() {
        return comp;
    }

    private Computer comp;
    private Player player;

    public Game()
    {
        initialize();
        makeGameField();
        addMouseListener(new MouseHandler(this, player));
        comp = new Computer(this);
        checkComputer = new CheckComputer(comp);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(bs);
        for (Rectangle2D gf : gameField) { // drawing the field
            g2.setPaint(Color.WHITE);
            g2.fill(gf);
            g2.setPaint(Color.BLACK);
            g2.draw(gf);
        }
        for (Line2D.Double x : X) { // drawing X
            g2.draw(x);
        }
        for (Ellipse2D.Double o : O) { // drawing O
            g2.draw(o);
        }
        if (lineThatWin != null) {
            g2.setPaint(Color.RED);
            g2.setStroke(new BasicStroke(5));
            g2.draw(lineThatWin);
        }
    }

    public void initialize()
    {
        delay = 1000;
        timer = new Timer(delay, taskPerformer);
        bs = new BasicStroke(3); // making lines fatter
        X = new ArrayList<Line2D.Double>();
        O = new ArrayList<Ellipse2D.Double>();
        usedX = new ArrayList<Integer>();
        usedO = new ArrayList<Integer>();
        lineThatWin = null;
        computer = false;
        startDialog();
        repaint();
        caretaker = new Caretaker();
        caretaker.setMemento(saveGameField());
    }

    public void makeGameField()
    {
        double leftX = 0;
        double topY = 0;
        double width = 100;
        double height = 100;

        gameField = new Rectangle2D[9];
        // draw the field
        for (int i = 0, u = 0; i < 9; ++i) {
            if (u >= 3) {
                u = 0;
                leftX = 0;
                topY += 100;
            }
            gameField[i] = new Rectangle2D.Double(leftX, topY, width, height);
            ++u;
            leftX += 100;
        }
    }

    public int find(Point2D p)
    {
        int i = 0;
        for (Rectangle2D r : gameField) {
            if (r.contains(p)) return i;
            ++i;
        }
        return -1;
    }





    ActionListener taskPerformer = new ActionListener()
    {
        public void actionPerformed(ActionEvent evt)
        {
            comp.drawO();
        }
    };

    public void checkIfWin(String who)
    {
        if (who.equals("Xturn")) {
            if (usedX.contains(0) && usedX.contains(1) && usedX.contains(2))
                win(1);
            else if (usedX.contains(3) && usedX.contains(4) && usedX.contains(5))
                win(2);
            else if (usedX.contains(6) && usedX.contains(7) && usedX.contains(8))
                win(3);
            else if (usedX.contains(0) && usedX.contains(3) && usedX.contains(6))
                win(4);
            else if (usedX.contains(1) && usedX.contains(4) && usedX.contains(7))
                win(5);
            else if (usedX.contains(2) && usedX.contains(5) && usedX.contains(8))
                win(6);
            else if (usedX.contains(0) && usedX.contains(4) && usedX.contains(8))
                win(7);
            else if (usedX.contains(6) && usedX.contains(4) && usedX.contains(2))
                win(8);
            else
                checkIfDraw();
        }
        else if (who.equals("Oturn")) {
            if (usedO.contains(0) && usedO.contains(1) && usedO.contains(2))
                win(1);
            else if (usedO.contains(3) && usedO.contains(4) && usedO.contains(5))
                win(2);
            else if (usedO.contains(6) && usedO.contains(7) && usedO.contains(8))
                win(3);
            else if (usedO.contains(0) && usedO.contains(3) && usedO.contains(6))
                win(4);
            else if (usedO.contains(1) && usedO.contains(4) && usedO.contains(7))
                win(5);
            else if (usedO.contains(2) && usedO.contains(5) && usedO.contains(8))
                win(6);
            else if (usedO.contains(0) && usedO.contains(4) && usedO.contains(8))
                win(7);
            else if (usedO.contains(6) && usedO.contains(4) && usedO.contains(2))
                win(8);
            else
                checkIfDraw();
        }
    }

    public void checkIfDraw()
    {
        if (usedX.size() + usedO.size() >= 9) {
            messageDialog(0);
        }
    }

    public void win(int whatTurnWin)
    {
        drawWinLine(whatTurnWin);
    }

    public void drawWinLine(int whatTurnWin)
    {
        int n = 20; // this makes the winline looks nicer
        if (whatTurnWin == 1)
            lineThatWin = new Line2D.Double(0 + n, 50, 300 - n, 50);
        else if (whatTurnWin == 2)
            lineThatWin = new Line2D.Double(0 + n, 150, 300 - n, 150);
        else if (whatTurnWin == 3)
            lineThatWin = new Line2D.Double(0 + n, 250, 300 - n, 250);
        else if (whatTurnWin == 4)
            lineThatWin = new Line2D.Double(50, 0 + n, 50, 300 - n);
        else if (whatTurnWin == 5)
            lineThatWin = new Line2D.Double(150 , 0 + n, 150, 300 - n);
        else if (whatTurnWin == 6)
            lineThatWin = new Line2D.Double(250, 0 + n, 250, 300 - n);
        else if (whatTurnWin == 7)
            lineThatWin = new Line2D.Double(0 + n, 0 + n, 300 - n, 300 - n);
        else if (whatTurnWin == 8)
            lineThatWin = new Line2D.Double(0 + n, 300 - n, 300 - n, 0 + n);
        repaint();
        if (computer)
            messageDialog(2);
        else
            messageDialog(1);
    }

    public void messageDialog(int who)
    {
        String s, s2, s3;
        if (who == 2) { // computer is winner
            s = "Do you want to start a new game?\n" +
                    "If 'yes' You'll own the first turn";
            s2 = "Computer has won!";
        }
        else if (who == 1) { // we's won
            s = "Do you want to start a new game?\n" +
                    "If 'yes' the computer'll own the first turn";
            s2 = "You're a winner!";
        }
        else { // it's draw
            if (computer) {
                s3 = "You'll";
            }
            else {
                s3 = "the computer'll";
            }
            s = "Do you want to start a new game?\n" +
                    "If 'yes' " + s3 + " own the first turn";
            s2 = "It's DRAW!";
        }
        int n = JOptionPane.showConfirmDialog(
                Game.this, s, s2, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION) {
            restoreGameField(caretaker.getMemento());
            caretaker.setMemento(saveGameField());
        }
        else if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    public Player startDialog(){
        String s ,s2;
        s="Да - первый игрок. Нет - второй игрок.";
        s2= "Выберите игрока";
        int n = JOptionPane.showConfirmDialog(Game.this, s, s2, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION) {
            player = new Player1(this);
        }
        else if (n == JOptionPane.NO_OPTION) {
            player = new Player2(this);
        }
        return player;
    }

    public Memento saveGameField() {
        return new Memento(delay, timer, bs, X, O, usedX , usedO , lineThatWin , computer);
    }

    public void restoreGameField(Memento memento) {
        this.delay = memento.getDelay();
        this.timer = memento.getTimer();
        this.bs = memento.getBs();
        this.X = memento.getX();
        this.O = memento.getO();
        this.usedX = memento.getUsedX();
        this.usedO = memento.getUsedO();
        this.lineThatWin = memento.getLineThatWin();
        this.computer = memento.isComputer();
        repaint();
    }
}