package paint.Tools;

import paint.Drawables.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Abstract class providing currently active tool access to mouse events
 */
public abstract class Tool {
    protected Color color;

    /**
     * Sets the primary color of the tool
     * @param color
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Processes the MouseButtonDownEvent
     * @param e mouseEvent
     */
    public void processMouseDown(MouseEvent e){}

    /**
     * Processes the MouseButtonUpEvent
     * @param e mouseEvent
     */
    public void processMouseUp(MouseEvent e){}

    /**
     * Processes the MouseDraggedEvent
     * @param e mouseEvent
     */
    public void processMouseDragged(MouseEvent e){}

    /**
     * Processes the MouseWheelEvent
     * @param e mouseEvent
     */
    public void processMouseWheel(MouseWheelEvent e){}

    /**
     * Gets the object selected by the tool
     * @return selected object
     */
    public Drawable getSelected(){return null;}
}
