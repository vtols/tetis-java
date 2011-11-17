package tetis;

public class StandardCounter extends Counter {
    
    StandardCounter(int scale) {
        super(scale);
    }
    
    @Override
    public void figureStopped() {
        pts += scale;
    }
    
    @Override
    public void lineCleared(int combo) { //combo in range [1..n]
        pts += scale;
        lines++;
    }
    
}
