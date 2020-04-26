package paint.Drawables;

import paint.Application;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

public abstract class Drawable implements Serializable, Cloneable {
    protected Color color;
    protected AffineTransform affineTransform = null;
    protected Shape shape;
    protected Long ID;

    public abstract void draw(Graphics2D graphics2D);

    public boolean contains(int x, int y){
        return false;
    }

    public void translate(int dx, int dy){}
    public void scale(double scale){}
    public void setRotation(double rotation){}
    public double getRotation(){return 0;}

    public Point2D getCenter(){
        Point2D point2D = new Point();
        return affineTransform.transform(point2D, point2D);
    }

    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }

    protected Drawable(){
        ID = Application.getInstance().getNewID();
    }

    public Drawable clone() throws CloneNotSupportedException{
        Drawable d = (Drawable) super.clone();
        d.color = new Color(this.color.getRGB());
        return d;
    }

    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
}