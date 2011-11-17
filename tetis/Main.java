package tetisconsole;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static JFrame mframe;
    public static TetrisPanel pan;

    private static void createAndShowGUI() {
        mframe = new JFrame("JTetis");
        pan = new TetrisPanel();
        mframe.add(pan);
        mframe.setResizable(false);
        mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mframe.pack();
        mframe.setVisible(true);
        mframe.addWindowFocusListener(new WindowFocusListener() {

            public void windowGainedFocus(WindowEvent e) {
            }

            public void windowLostFocus(WindowEvent e) {
                pan.pause();
            }
        });
        //DarwingTetris di = new DarwingTetris(pan);
        //di.start();
        //pan.setDrawingInterface(di);
    }
}