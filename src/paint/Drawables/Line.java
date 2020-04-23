package paint.Drawables;

import java.awt.*;

public class Line extends Drawable {
    public int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2, Color color){
        this.color = new Color(color.getRGB());
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.drawLine(x1, y1, x2, y2);
    }
}
