package paint.Drawables;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Drawable Ellipse Class
 */
public class DrawableEllipse extends Drawable {
    private Ellipse2D ellipse2D;
    private final int origX, origY;
    private boolean isFinished = false;

    /**
     * Constructor
     * @param x1 x coordinate of south-west bounding rectangle corner, origin x
     * @param y1 y coordinate of south-west bounding rectangle corner, origin y
     * @param x2 x coordinate of north-east bounding rectangle corner
     * @param y2 y coordinate of north-east bounding rectangle corner
     * @param color color of the ellipse
     */
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
        graphics2D.fill(isFinished ? shape : ellipse2D);
    }

    @Override
    public Drawable clone() throws CloneNotSupportedException {
        DrawableEllipse copy = (DrawableEllipse) super.clone();
        copy.ellipse2D = (Ellipse2D) ellipse2D.clone();
        return copy;
    }

    @Override
    public boolean contains(int x, int y) {
        return isFinished ? shape.contains(x, y) : ellipse2D.contains(x, y);
    }

    /**
     * sets the position of the second point defining the ellipse (non orign)
     * @param x x coordinate of second bounding rectangle corner
     * @param y y coordinate of second bounding rectangle corner
     */
    public void setSecondPoint(int x, int y){
        if((x < origX) && (y < origY)){
            ellipse2D.setFrame(x, y, origX - x, origY - y);
        }
        else if(x < origX){
            ellipse2D.setFrame(x, origY, origX - x, y - origY);
        }
        else if(y < origY){
            ellipse2D.setFrame(origX, y, x - origX, origY - y);
        }
        else {
            ellipse2D.setFrame(origX, origY, x - origX, y- origY);
        }
    }

    /**
     * Declares figure to be complete, ie not editable by <code>setSecondPoint()</code>
     */
    public void setFinished(){
        var frame = ellipse2D.getFrame();
        translationX = frame.getX() + frame.getWidth()/2;
        translationY = frame.getY() + frame.getHeight()/2;
        ellipse2D.setFrame(-frame.getWidth()/2, -frame.getHeight()/2, frame.getWidth(), frame.getHeight());

        createAffineTransform();
        shape = affineTransform.createTransformedShape(ellipse2D);
        isFinished = true;
    }

    @Override
    protected void updateShape() {
        shape = affineTransform.createTransformedShape(ellipse2D);
    }
}
