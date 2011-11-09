/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetisconsole;

/**
 *
 * @author mtcomscxstart
 */
public class ProgressionCounter extends Counter {
    
    ProgressionCounter(int scale) {
        super(scale);
    }
    
    @Override
    public void figureStopped() {
    }
    
    @Override
    public void lineCleared(int combo) { //combo in range [1..n]
        pts += combo * scale;
        lines++;
    }
    
}
