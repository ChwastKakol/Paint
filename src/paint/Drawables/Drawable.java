package paint.Drawables;

import paint.Application;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * An abstract class used to draw figures in <code>PaintingWindow</code>
 */
public abstract class Drawable implements Serializable, Cloneable {
    /**
     * The Color.
     */
    protected Color color;
    /**
     * The Affine transform.
     */
    protected AffineTransform affineTransform = null;
    /**
     * The Shape.
     */
    protected Shape shape;
    /**
     * The Id.
     */
    protected Long ID;

    /**
     * The Scale.
     */
    protected double scale = 1;
    /**
     * The Translation x.
     */
    protected double translationX = 0, /**
     * The Translation y.
     */
    translationY = 0;
    /**
     * The Rotation.
     */
    protected double rotation = 0;

    /**
     * Abstract method describing how a drawable object is drawn
     *
     * @param graphics2D graphics to be drawn to
     */
    public abstract void draw(Graphics2D graphics2D);

    /**
     * Method checking if drawable object contains point (x, y)
     *
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return true if object contains point (x,y) false otherwise
     */
    public boolean contains(int x, int y){
        return false;
    }

    /**
     * Translates drawable object on the XY plane
     *
     * @param dx movement along X axis
     * @param dy movement along Y axis
     */
    public void translate(int dx, int dy){
        translationX += dx;
        translationY += dy;
        createAffineTransform();
        updateShape();
    }

    /**
     * Scales drawable object
     *
     * @param scale change of scale
     */
    public void scale(double scale){
        this.scale += scale;
        createAffineTransform();
        updateShape();
    }

    /**
     * Sets rotation of drawable object to specified angle
     *
     * @param rotation counter-clockwise angle measured from positive X axis
     */
    public void setRotation(double rotation){
        this.rotation = rotation;
        createAffineTransform();
        updateShape();
    }

    /**
     * Gets Rotation of drawable object
     *
     * @return counter -clockwise angle measured from positive X axis
     */
    public double getRotation(){
        return rotation;
    }

    /**
     * Gets center of drawable object
     *
     * @return center of drawable object
     */
    public Point2D getCenter(){
        Point2D point2D = new Point();
        return affineTransform.transform(point2D, point2D);
    }

    /**
     * Sets color of drawable object
     *
     * @param color colour of drawable object
     */
    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }

    /**
     * Default constructor
     */
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

    /**
     * Gets object's ID
     *
     * @return object 's ID
     */
    public Long getID() {
        return ID;
    }

    /**
     * Sets object's ID
     *
     * @param ID the id
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * Creates sets <code>affineTransform</code> to one created from current position, rotation and scale
     */
    protected void createAffineTransform(){
        affineTransform = new AffineTransform();
        affineTransform.translate(translationX, translationY);
        affineTransform.rotate(rotation);
        affineTransform.scale(scale, scale);
    }

    /**
     * Updates <code>shape</code> with current affineTransform
     */
    protected void updateShape(){};
}