package paint.Drawables;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawableRectangle extends Drawable {
    private final int origX, origY;
    private boolean isFinished = false;
    private Rectangle rectangle;

    public DrawableRectangle(int x1, int y1, int x2, int y2, Color color){
        super();
        rectangle = new Rectangle(x1, y1, x2-x1, y2 -y1);
        origX = x1;
        origY = y1;
        this.color = new Color(color.getRGB());
        affineTransform = new AffineTransform();
    }

    @Override
    public Drawable clone() throws CloneNotSupportedException {
        DrawableRectangle copy = (DrawableRectangle) super.clone();
        copy.rectangle = new Rectangle(rectangle);
        return copy;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fill(isFinished ? shape : rectangle);
    }

    @Override
    public boolean contains(int x, int y) {
        return isFinished ? shape.contains(x, y) : rectangle.contains(x, y);
    }

    public void setSecondPoint(int x, int y){
        if((x < origX) && (y < origY)){
            rectangle.setSize(origX - x, origY - y);
            rectangle.setLocation(x, y);
        }
        else if(x < origX){
            rectangle.setSize(origX - x, y - origY);
            rectangle.setLocation(x, origY);
        }
        else if(y < origY){
            rectangle.setSize(x - origX, origY - y);
            rectangle.setLocation(origX, y);
        }
        else {
            rectangle.setSize(x - origX, y - origY);
        }
    }

    public void setFinished(){
        translationX = rectangle.getX() + rectangle.getWidth()/2;
        translationY = rectangle.getY() + rectangle.getHeight()/2;
        rectangle.translate(-(int)translationX, -(int)translationY);

        createAffineTransform();
        shape = affineTransform.createTransformedShape(rectangle);
        isFinished = true;
    }

    @Override
    protected void updateShape() {
        shape = affineTransform.createTransformedShape(rectangle);
    }
}
