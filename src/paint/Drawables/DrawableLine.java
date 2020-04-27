package paint.Drawables;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Drawable Line Class
 */
public class DrawableLine extends Drawable {
    private Line2D line2D;
    private boolean isFinished = false;

    /**
     * Constructor
     * @param x1 x coordinate of origin point
     * @param y1 y coordinate of origin point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     * @param color color of the line
     */
    public DrawableLine(int x1, int y1, int x2, int y2, Color color){
        super();
        setColor(color);
        line2D = new Line2D.Double(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.draw(isFinished ? shape : line2D);
    }

    /**
     * sets the position of the second point defining the line (non orign)
     * @param x x coordinate of second point
     * @param y y coordinate of second point
     */
    public void setSecondPoint(int x, int y){
        line2D.setLine(line2D.getP1(), new Point(x, y));
    }

    @Override
    public boolean contains(int x, int y) {
        if(isFinished){
            Rectangle2D rectangle2D = new Rectangle(x-1, y-1, 2, 2);
            return shape.intersects(rectangle2D);
        }
        else{
            return line2D.ptLineDistSq(x,y) < 2;
        }
    }

    /**
     * Declares figure to be complete, ie not editable by <code>setSecondPoint()</code>
     */
    public void setFinished(){
        var frame = line2D.getBounds2D();
        translationX = frame.getX() + frame.getWidth()/2;
        translationY = frame.getY() + frame.getHeight()/2;
        line2D.setLine(line2D.getX1() - translationX, line2D.getY1() - translationY,
                line2D.getX2() - translationX, line2D.getY2() - translationY);

        createAffineTransform();
        shape = affineTransform.createTransformedShape(line2D);
        isFinished = true;
    }

    @Override
    protected void updateShape() {
        shape = affineTransform.createTransformedShape(line2D);
    }
}
