package tetis;

import java.awt.Color;

public class ColoredTable {
    boolean[][] t;
    int[][] code;
    Color[][] c;

    ColoredTable(int h, int w) {
        t = new boolean[h][w];
        code = new int[h][w];
        c = new Color[h][w];
        for(int i = 0; i < h; i++)
            for(int j = 0; j < w; j++)
                c[i][j] = Color.BLACK;
    }
}
