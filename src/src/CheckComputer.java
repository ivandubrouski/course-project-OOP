package src;

import javax.swing.*;


public class CheckComputer implements Observer {
    private Computer comp;
    private DrawFrame frame;
    public CheckComputer(Computer comp){
        this.comp = comp;
        comp.registerObserver(this);
    }
    public void update(boolean computer){
        if(computer) {
            JOptionPane.showMessageDialog(null, "Ur turn");
        }
    }
}
