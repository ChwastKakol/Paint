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

    protected double scale = 1;
    protected double translationX = 0, translationY = 0;
    protected double rotation = 0;

    public abstract void draw(Graphics2D graphics2D);

    public boolean contains(int x, int y){
        return false;
    }

    public void translate(int dx, int dy){
        translationX += dx;
        translationY += dy;
        createAffineTransform();
        updateShape();
    }
    public void scale(double scale){
        this.scale += scale;
        createAffineTransform();
        updateShape();
    }
    public void setRotation(double rotation){
        this.rotation = rotation;
        createAffineTransform();
        updateShape();
    }

    public double getRotation(){
        return rotation;
    }

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
        d.affineTransform = new AffineTransform(this.affineTransform);
        d.updateShape();
        return d;
    }

    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }

    protected void createAffineTransform(){
        affineTransform = new AffineTransform();
        affineTransform.translate(translationX, translationY);
        affineTransform.rotate(rotation);
        affineTransform.scale(scale, scale);
    }

    protected void updateShape(){};
}