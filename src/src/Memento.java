package src;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Memento {
    public int getDelay() {
        return delay;
    }

    public Timer getTimer() {
        return timer;
    }

    public ArrayList<Line2D.Double> getX() {
        return X;
    }

    public BasicStroke getBs() {
        return bs;
    }

    public ArrayList<Ellipse2D.Double> getO() {
        return O;
    }

    public ArrayList<Integer> getUsedX() {
        return usedX;
    }

    public ArrayList<Integer> getUsedO() {
        return usedO;
    }

    public boolean isComputer() {
        return computer;
    }

    public Line2D.Double getLineThatWin() {
        return lineThatWin;
    }

    private int delay;
    private Timer timer;
    private BasicStroke bs;
    private ArrayList<Line2D.Double> X;
    private ArrayList<Ellipse2D.Double> O;
    private ArrayList<Integer> usedX ;
    private ArrayList<Integer> usedO ;
    private Line2D.Double lineThatWin ;
    private boolean  computer;

    public Memento(int delay, Timer timer, BasicStroke bs, ArrayList<Line2D.Double> X, ArrayList<Ellipse2D.Double> O, ArrayList<Integer> usedX , ArrayList<Integer> usedO , Line2D.Double lineThatWin ,boolean  computer) {
        this.delay = delay;
        this.timer = timer;
        this.bs = bs;
        this.X = new ArrayList<Line2D.Double>(X.size());
        Collections.copy(this.X, X);
        this.O = new ArrayList<Ellipse2D.Double>(O.size());
        Collections.copy(this.O, O);
        this.usedX = new ArrayList<Integer>(usedX.size());
        Collections.copy(this.usedX, usedX);
        this.usedO = new ArrayList<Integer>(usedO.size());
        Collections.copy(this.usedO, usedO);
        this.usedO.addAll(usedO) ;
        this.lineThatWin = lineThatWin;
        this.computer = computer;
    }


}
