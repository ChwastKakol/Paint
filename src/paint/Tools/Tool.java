package paint.Tools;

import paint.Drawables.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Tool {
    protected Color color;

    public void setColor(Color color){
        this.color = color;
    }

    public void processMouseDown(MouseEvent e){}
    public void processMouseUp(MouseEvent e){}
    public void processMouseDragged(MouseEvent e){}
    public Drawable getSelected(){return null;}
}
