package tetisconsole;

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
