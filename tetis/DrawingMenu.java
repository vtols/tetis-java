package tetis;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class DrawingMenu implements DrawingInterface {

    TetrisPanel owner;

    FontMetrics fm;

    String[] menuEntries =
            new String[]{"New game", "Resume", "Help", "Exit"};
    
    boolean[] enabledEntry =
            new boolean[]{true, false, true, true};
    
    int enCount = 0;
    
    HashMap<String, Boolean> entries = new HashMap<String, Boolean>();

    Font drFont = new Font("Arial", Font.PLAIN, 30);

    boolean started = false;

    int selected = 0;

     public DrawingMenu(TetrisPanel ow) {
        owner = ow;
        for(int i = 0; i < menuEntries.length; i++)
            if(enabledEntry[i])
                enCount++;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g.setColor(new Color(238, 238, 238));
        g.fillRect(0, 0, 800, 500);
        g.setFont(drFont);
        fm = g.getFontMetrics();
        int h = fm.getHeight(), nh = enCount * h;
        for(int i = 0, dc = 0; i < menuEntries.length; i++) {
            if(!enabledEntry[i])
                continue;
            if(dc == selected)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);
            int ws = fm.stringWidth(menuEntries[i]);
            int x = 800 / 2 - ws / 2;
            int y = 500 / 2 - nh / 2 + h * dc;
            g.drawString(menuEntries[i], x, y);
            dc++;
        }
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            selected = (selected + enCount - 1) % enCount;
            repaint();
        }
        else if(code == KeyEvent.VK_DOWN) {
            selected = (selected + 1) % enCount;
            repaint();
        }
        else if(code == KeyEvent.VK_ENTER) {
            openSelected();
        }
    }
    
    public void setEntryEnabled(String name, boolean st) {
        selected = 0;
        for(int i = 0; i < menuEntries.length; i++) {
            if(name.equals(menuEntries[i])) {
                if(enabledEntry[i] && !st)
                    enCount--;
                else if(!enabledEntry[i] && st)
                    enCount++;
                enabledEntry[i] = st;
                break;
            }
        }
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
        int h = fm.getHeight(), nh = enCount * h;
        int dy = 500 / 2 - nh / 2 - h;
        int my = e.getY();
        if(my >= dy && my < dy + nh)
            selected = (my - dy) / h;
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        openSelected();
    }

    public void openSelected() {
        int rselected = 0;
        for(int i = 0, rc = -1; rc < selected; i++) {
            if(enabledEntry[i]) {
                rc++;
                if(rc == selected)
                    break;
            }
            rselected++;
        }
        if(menuEntries[rselected].equals("New game")) {
            setStarted(true);
            owner.restartTetris();
        }
        else if(menuEntries[rselected].equals("Resume"))
            owner.openTetris();
        else if(menuEntries[rselected].equals("Exit")) {
            System.exit(0);
        }
    }

    public void repaint() {
        owner.repaint();
    }

    public void setStarted(boolean st) {
        started = st;
        setEntryEnabled("Resume", st);
//        if(st)
//            menuEntries[0] = "Resume";
//        else
//            menuEntries[0] = "Start Game";
    }

}
