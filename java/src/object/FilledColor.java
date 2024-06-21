package object;

import java.awt.*;

public class FilledColor {
    public final int r;
    public final int g;
    public final int b;
    public Shape shape;

    public FilledColor(int r, int g, int b, Shape shape) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.shape = shape;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public Shape getShape() {
        return shape;
    }
}
