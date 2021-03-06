package paint.Tools;

import paint.Application;
import paint.Commands.EditDrawableCommand;
import paint.Drawables.Drawable;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

/**
 * Tool allowing to edit existing figures
 */
public class EditTool extends Tool {
    private static final double mouseWheelSensitivity = .1d;
    private Drawable drawable;
    private int  x, y;
    private PaintingWindow paintingWindow;

    private EditDrawableCommand command;

    private int mouseButton = 0;
    private double rotation = 0;
    private Point2D rotationOrigin;

    /**
     * Construcotr
     * @param paintingWindow painting window in which the figure is to be edited
     * @param color primary color of the tool
     */
    public EditTool(PaintingWindow paintingWindow, Color color){
        this.paintingWindow = paintingWindow;
        this.color = color;
    }

    /**
     * Initializes action depending on what mouse button was pressed over existing figure
     * left mouse button - movement
     * right mouse button - changes color
     * middle mouse button - rotation
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDown(MouseEvent e) {
        drawable = paintingWindow.getCollidedDrawable(e.getX(), e.getY());
        if(drawable != null){
            command = new EditDrawableCommand(paintingWindow, drawable);
            mouseButton = e.getButton();

            if(e.getButton() == MouseEvent.BUTTON1){
                x = e.getX();
                y = e.getY();
            }
            else if(mouseButton == MouseEvent.BUTTON2){
                rotationOrigin = drawable.getCenter();
                rotation = drawable.getRotation() - Math.atan2(e.getY() - rotationOrigin.getY(), e.getX() - rotationOrigin.getX());
            }
            else if(e.getButton() == MouseEvent.BUTTON3) {
                drawable.setColor(color);
                command.setDrawable(drawable);
                Application.getInstance().addCommand(command);
            }
        }
    }

    /**
     * if left mouse is dragged continues movement, if middle continues rotation
     * @param e mouseEvent
     */
    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawable != null && mouseButton == MouseEvent.BUTTON1){
            drawable.translate(e.getX() - x, e.getY() - y);
            x = e.getX();
            y = e.getY();
            paintingWindow.redraw();
        }
        else if(drawable != null && mouseButton == MouseEvent.BUTTON2){
            double angle = Math.atan2(rotationOrigin.getY() - e.getY(), rotationOrigin.getX() - e.getX()) + Math.PI;
            drawable.setRotation(angle + rotation);
            paintingWindow.redraw();
        }
    }

    /**
     * finishes editing action
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseUp(MouseEvent e) {
        if(drawable != null && command != null) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                command.setDrawable(drawable);
                Application.getInstance().addCommand(command);
            }
            else if(e.getButton() == MouseEvent.BUTTON2){
                command.setDrawable(drawable);
                Application.getInstance().addCommand(command);
            }
        }
        mouseButton = 0;
    }

    /**
     * scales figure over which the mouse was hovering while it was rotated
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseWheel(MouseWheelEvent e) {
        if(drawable != null){
            drawable.scale(- e.getPreciseWheelRotation() * mouseWheelSensitivity );
            paintingWindow.redraw();
        }
    }

    @Override
    public Drawable getSelected(){
        return drawable;
    }
}
