package tetisconsole;

public abstract class Counter {
    
    int scale, pts = 0, lines = 0;
    
    Counter(int scale) {
        this.scale = scale;
    }
    
    public void figureStopped() {
    }
    
    public void lineCleared(int combo) { //combo in range [1..n]
    }
    
    public int getPoints() {
        return pts;
    }
    
    public int getLines() {
        return lines;
    }
    
}
