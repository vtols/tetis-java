package tetisconsole;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ИгВа
 */
public interface DrawingInterface {

    public void draw(Graphics g);

    public void keyPressed(KeyEvent e);

    public void mouseMoved(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void repaint();

}
