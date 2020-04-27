package paint.Drawables;

import java.awt.*;

/**
 * Drawable Point
 */
public class Point extends Drawable {
    private int x, y;

    /**
     * Constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param color color of the point
     */
    public Point(int x, int y, Color color){
        super();
        this.color = new Color(color.getRGB());
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawLine(x,y,x,y);
    }
}
