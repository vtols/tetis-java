/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tetisconsole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author ИгВа
 */
public class TetrisPanel extends JPanel{

    DrawingInterface current = null;

    DrawingMenu menu;
    DrawingTetris tetrisd;

    public TetrisPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        KeyListener kl = new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if(current != null)
                    current.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
            }

        };
        MouseMotionListener ml = new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {
                current.mouseMoved(e);
            }
        };
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                current.mousePressed(e);
            }
        };
        this.addKeyListener(kl);
        this.addMouseMotionListener(ml);
        this.addMouseListener(ma);
        this.setFocusable(true);
        menu = new DrawingMenu(this);
        tetrisd = new DrawingTetris(this);
        current = menu;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        //System.out.println("repainting");
        current.draw(g);
    }

    public void setDrawingInterface(DrawingInterface dif) {
        current = dif;
    }

    void openTetris() {
        current = tetrisd;
        tetrisd.paused = false;
        repaint();
    }

    void openMenu() {
        current = menu;
        repaint();
    }

    void restartTetris() {
        current = tetrisd;
        if(tetrisd.started)
            tetrisd.stopTetris();
        tetrisd.start();
        tetrisd.paused = false;
        repaint();
    }

    void pause() {
        if(current == tetrisd)
            tetrisd.paused = true;
    }

}
