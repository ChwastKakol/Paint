package paint.Tools;

import paint.Drawables.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class Tool {
    protected Color color;

    public void setColor(Color color){
        this.color = color;
    }

    public void processMouseDown(MouseEvent e){}
    public void processMouseUp(MouseEvent e){}
    public void processMouseDragged(MouseEvent e){}
    public void processMouseWheel(MouseWheelEvent e){}
    public Drawable getSelected(){return null;}
}
