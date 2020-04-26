package paint.Drawables;

import java.awt.*;

public class DrawableTriangle extends Drawable {
    private final int origX, origY;
    private Polygon polygon;
    private boolean isFinished = false;

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

    /*@Override
    public void translate(int dx, int dy) {
        polygon.translate(dx, dy);
    }*/

    /*@Override
    public Drawable clone() throws CloneNotSupportedException {
         DrawableTriangle copy = (DrawableTriangle) super.clone();
         copy.polygon = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
         return copy;
    }*/

    @Override
    public boolean contains(int x, int y) {
        return isFinished ? shape.contains(x,y) : polygon.contains(x, y);
    }

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

    protected Polygon createPolygon(int x1, int y1, int x2, int y2, boolean invert){
        return new Polygon(new int[]{x1, (x1 + x2)/2, x2}, (invert? new int[]{y2, y1, y2} : new int[]{y1, y2, y1}), 3);
    }

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
