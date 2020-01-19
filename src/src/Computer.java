package src;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Computer implements Observable{
    private Game game;
    private Random r;
    private List<Observer> observers;
    private boolean computer;
    public Computer(Game game)
    {
        this.game = game;
        this.computer = game.isComputer();
        r = new Random();
        observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(computer);
    }

    public void drawO()
    {
        double x = game.getGameField()[game.getComputerTurn()].getCenterX();
        double y = game.getGameField()[game.getComputerTurn()].getCenterY();
        Ellipse2D.Double circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(x, y, x + 40, y + 40);
        game.getO().add(circle);
        game.getUsedO().add(game.getComputerTurn());
        game.repaint();
        game.getTimer().stop();
        game.checkIfWin("Oturn");
        game.setComputer(false);
    }


    public void computerTurn() // make it smarter
    {
        if (!game.getUsedX().contains(4) && !game.getUsedO().contains(4))
            game.setComputerTurn(4);

        else if (game.getUsedX().contains(4)) {
            if (game.getUsedX().contains(0) && !game.getUsedO().contains(8)) {
                game.setComputerTurn(8);
            }
            else if (game.getUsedX().contains(1) && !game.getUsedO().contains(7)) {
                game.setComputerTurn(7);
            }
            else if (game.getUsedX().contains(2) && !game.getUsedO().contains(6)) {
                game.setComputerTurn(6);
            }
            else if (game.getUsedX().contains(5) && !game.getUsedO().contains(3)) {
                game.setComputerTurn(3);
            }
            else if (game.getUsedX().contains(8) && !game.getUsedO().contains(0)) {
                game.setComputerTurn(0);
            }
            else if (game.getUsedX().contains(7) && !game.getUsedO().contains(1)) {
                game.setComputerTurn(1);
            }
            else if (game.getUsedX().contains(6) && !game.getUsedO().contains(2)) {
                game.setComputerTurn(2);
            }
            else if (game.getUsedX().contains(3) && !game.getUsedO().contains(5)) {
                game.setComputerTurn(5);
            }
            else if (game.getUsedX().contains(0) || game.getUsedO().contains(0) && game.getUsedX().contains(2) || game.getUsedO().contains(2) &&
                    game.getUsedX().contains(8) || game.getUsedO().contains(8) && game.getUsedX().contains(6) || game.getUsedO().contains(6)) {
                int i = 0;
                while (game.getUsedX().contains(i) || game.getUsedO().contains(i)) // just random
                    i = r.nextInt(9);
                game.setComputerTurn(i);
            }
            else {
                int i = 0;
                while (game.getUsedX().contains(i) || game.getUsedO().contains(i) || i == 4 || i == 1 || i == 5 || i == 7 || i == 3) {
                    i = r.nextInt(9);
                }
                game.setComputerTurn(i);
            }
        }

        else if (game.getUsedO().contains(4)) {
            if (game.getUsedO().contains(0) && !game.getUsedO().contains(8) && !game.getUsedX().contains(8))
                game.setComputerTurn(8);
            else if (game.getUsedO().contains(2) && !game.getUsedO().contains(6) && !game.getUsedX().contains(6)) {
                game.setComputerTurn(6);
            }
            else if (game.getUsedO().contains(8) && !game.getUsedO().contains(0) && !game.getUsedX().contains(0)) {
            }
            else if (game.getUsedO().contains(6) && !game.getUsedO().contains(2) && !game.getUsedX().contains(2)) {
                game.setComputerTurn(2);
            }

            else if (game.getUsedX().contains(0) || game.getUsedO().contains(0) && game.getUsedX().contains(2) || game.getUsedO().contains(2) &&
                    game.getUsedX().contains(8) || game.getUsedO().contains(8) && game.getUsedX().contains(6) || game.getUsedO().contains(6)) {
                int i = 0;
                while (game.getUsedX().contains(i) || game.getUsedO().contains(i)) // just random
                    i = r.nextInt(9);
                game.setComputerTurn(i);
            }
            else {
                int i = 0;
                System.out.println(i);
                while (game.getUsedX().contains(i) || game.getUsedO().contains(i) || i == 4 || i == 1 || i == 5 || i == 7 || i == 3) {
                    i = r.nextInt(9);
                }
                game.setComputerTurn(i);
            }
        }
        game.getTimer().start();
    }
}
