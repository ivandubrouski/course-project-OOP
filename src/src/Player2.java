package src;

import java.awt.geom.Line2D;


public class Player2 implements Player {
    private Game game;
    public Player2(Game game){
        this.game = game;
    }
    public void draw(){
        double x = game.getGameField()[game.getRect()].getX();
        double y = game.getGameField()[game.getRect()].getY();
        game.getXgame().add(new Line2D.Double(x + 10, y + 90, x +50, y - 1));
        game.getXgame().add(new Line2D.Double(x + 10, y + 90, x + 90, y + 10));
        game.getUsedX().add(game.getRect());
        game.repaint();
        game.checkIfWin("Xturn");
        game.setComputer(true);
        game.getComp().notifyObservers();
        game.getComp().computerTurn();
    }
}
