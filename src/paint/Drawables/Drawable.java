package paint.Drawables;

import paint.Application;

import java.awt.*;
import java.io.Serializable;

public abstract class Drawable implements Serializable, Cloneable {
    protected Color color;
    protected Long ID;

    public abstract void draw(Graphics2D graphics2D);

    public boolean contains(int x, int y){
        return false;
    }

    public void translate(int dx, int dy){}

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