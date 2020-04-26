package paint.Drawables;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawableEllipse extends Drawable {
    private Ellipse2D ellipse2D;
    private final int origX, origY;

    public DrawableEllipse(int x1, int y1, int x2, int y2, Color color){
        super();
        ellipse2D = new Ellipse2D.Float(x1, y1, y1, y2);
        origX = x1;
        origY = y2;
        setColor(color);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fill(ellipse2D);
    }

    @Override
    public void translate(int dx, int dy) {
        var frame = ellipse2D.getFrame();
        ellipse2D.setFrame(frame.getX() + dx, frame.getY() + dy, frame.getWidth(), frame.getHeight());
    }

    @Override
    public Drawable clone() throws CloneNotSupportedException {
        DrawableEllipse copy = (DrawableEllipse) super.clone();
        copy.ellipse2D = (Ellipse2D) ellipse2D.clone();
        return copy;
    }

    @Override
    public boolean contains(int x, int y) {
        return ellipse2D.contains(x, y);
    }

    public void setSecondPoint(int x, int y){
        if((x < origX) && (y < origY)){
            ellipse2D.setFrame(x, y, origX - x, origY - y);
            /*rectangle.setSize(origX - x, origY - y);
            rectangle.setLocation(x, y);*/
        }
        else if(x < origX){
            ellipse2D.setFrame(x, origY, origX - x, y - origY);
            /*rectangle.setSize(origX - x, y - origY);
            rectangle.setLocation(x, origY);*/
        }
        else if(y < origY){
            ellipse2D.setFrame(origX, y, x - origX, origY - y);
            /*rectangle.setSize(x - origX, origY - y);
            rectangle.setLocation(origX, y);*/
        }
        else {
            ellipse2D.setFrame(origX, origY, x - origX, y- origY);
            //rectangle.setSize(x - origX, y - origY);
        }
    }
}
