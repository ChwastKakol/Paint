package paint.Drawables;

import java.awt.*;

public class Rectangle extends Drawable {
    private int origX, origY;
    private int x1, y1, width, height;

    public Rectangle(int x1, int y1, int x2, int y2, Color color){
        super();
        this.x1 = x1;
        this.y1 = y1;
        origX = x1;
        origY = y1;
        this.height = y2 - y1;
        this.width = x2 - x1;
        this.color = new Color(color.getRGB());
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillRect(x1, y1, width, height);
    }

    public void setSecondPoint(int x, int y){
        if(x < origX){
            width = origX - x;
            x1 = x;
        }
        else{
            width = x - x1;
        }
        if(y < origY){
            height = origY - y;
            y1 = y;
        }
        else{
            height = y - y1;
        }
    }
}
