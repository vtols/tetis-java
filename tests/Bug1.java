package tetis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Bug1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Bug1: rotate stick near floor");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.add(new BugPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private static class BugPanel extends JPanel {
        private static final int CELL = 24;
        private static final int FIELD_H = 20;
        private static final int FIELD_W = 10;
        private static final int OFFSET_X = 24;
        private static final int OFFSET_Y = 24;

        private final Tetris t = new Tetris(FIELD_H, FIELD_W);
        private final Font font = new Font("Arial", Font.PLAIN, 16);

        BugPanel() {
            t.b = new Block[]{Block.fig7};
            t.next = Block.fig7;
            t.move();

            setFocusable(true);
            setPreferredSize(new Dimension(420, 560));
            setBackground(new Color(238, 238, 238));
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_DOWN)
                        t.move();
                    else if(e.getKeyCode() == KeyEvent.VK_SPACE)
                        t.rotate();
                    repaint();
                }
            });
        }

        @Override
        public void addNotify() {
            super.addNotify();
            requestFocusInWindow();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            ColoredTable table = t.table();
            for(int i = 0; i < FIELD_H; i++)
                for(int j = 0; j < FIELD_W; j++) {
                    int x = OFFSET_X + j * CELL;
                    int y = OFFSET_Y + i * CELL;
                    g.setColor(table.t[i][j] ? table.c[i][j] : Color.DARK_GRAY);
                    g.fillRoundRect(x, y, CELL - 2, CELL - 2, 5, 5);
                }
            drawRotationGhost(g);

            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("Down: step down", 290, 50);
            g.drawString("Space: rotate", 290, 75);
            g.drawString("x=" + t.x + " y=" + t.y, 290, 120);
            if(t.cur != null)
                g.drawString("shape=" + t.cur.h + "x" + t.cur.w, 290, 145);
        }

        private void drawRotationGhost(Graphics g) {
            if(t.cur == null || t.end)
                return;

            Block rot = t.cur.spin();
            int ny = t.y;
            while(rot.w + ny > t.w)
                ny--;
            if(rot.h + t.x > t.h)
                return;

            for(int i = 0; i < rot.h; i++)
                for(int j = 0; j < rot.w; j++) {
                    int row = t.x - rot.h + i;
                    int col = ny + j;
                    if(!rot.block[i][j] || row < 0)
                        continue;
                    if(t.ct.t[row][col])
                        return;
                }

            g.setColor(new Color(255, 255, 255, 150));
            for(int i = 0; i < rot.h; i++)
                for(int j = 0; j < rot.w; j++) {
                    int row = t.x - rot.h + i;
                    int col = ny + j;
                    if(!rot.block[i][j] || row < 0)
                        continue;
                    int x = OFFSET_X + col * CELL;
                    int y = OFFSET_Y + row * CELL;
                    g.fillRoundRect(x + 4, y + 4, CELL - 10, CELL - 10, 5, 5);
                }
        }
    }
}
