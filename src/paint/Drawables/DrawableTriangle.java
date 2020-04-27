package paint.Drawables;

import java.awt.*;

/**
 * Drawable Triangle Class
 */
public class DrawableTriangle extends Drawable {
    private final int origX, origY;
    private Polygon polygon;
    private boolean isFinished = false;

    /**
     * Constructor
     * @param x1 x coordinate of south-west bounding rectangle corner, origin x
     * @param y1 y coordinate of south-west bounding rectangle corner, origin y
     * @param x2 x coordinate of north-east bounding rectangle corner
     * @param y2 y coordinate of north-east bounding rectangle corner
     * @param color color of the triangle
     */
    public DrawableTriangle(int x1, int y1, int x2, int y2, Color color){
        super();
        polygon = createPolygon(x1, y1, x2, y2, false);
        origX = x1;
        origY = y1;
        this.color = new Color(color.getRGB());
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fill(isFinished ? shape : polygon);
    }

    @Override
    public boolean contains(int x, int y) {
        return isFinished ? shape.contains(x,y) : polygon.contains(x, y);
    }

    /**
     * sets the position of the second point defining the triangle (non orign)
     * @param x x coordinate of second bounding rectangle corner
     * @param y y coordinate of second bounding rectangle corner
     */
    public void setSecondPoint(int x, int y){
        if((x < origX) && (y < origY)){
            polygon = createPolygon(x, y, origX, origY, true);
        }
        else if(x < origX){
            polygon = createPolygon(x, origY, origX, y, false);
        }
        else if(y < origY){
            polygon = createPolygon(origX, y, x, origY, true);
        }
        else {
            polygon = createPolygon(origX, origY, x, y, false);
        }
    }

    /**
     * Creates triangle fitting defined rectangle
     * @param x1 x coordinate of south-west bounding rectangle corner
     * @param y1 y coordinate of south-west bounding rectangle corner
     * @param x2 x coordinate of north-east bounding rectangle corner
     * @param y2 y coordinate of north-east bounding rectangle corner
     * @param invert specifies whether or not the triangle is flipped vertically
     * @return
     */
    protected Polygon createPolygon(int x1, int y1, int x2, int y2, boolean invert){
        return new Polygon(new int[]{x1, (x1 + x2)/2, x2}, (invert? new int[]{y2, y1, y2} : new int[]{y1, y2, y1}), 3);
    }

    /**
     * Declares figure to be complete, ie not editable by <code>setSecondPoint()</code>
     */
    public void setFinished() {
        var frame = polygon.getBounds2D();
        translationX = frame.getX() + frame.getWidth()/2;
        translationY = frame.getY() + frame.getHeight()/2;
        polygon.translate(-(int)translationX, -(int)translationY);

        createAffineTransform();
        shape = affineTransform.createTransformedShape(polygon);
        isFinished = true;
    }

    @Override
    protected void updateShape() {
        shape = affineTransform.createTransformedShape(polygon);
    }
}
