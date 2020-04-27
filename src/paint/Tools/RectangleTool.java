package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.PaintingWindow;
import paint.Drawables.DrawableRectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tool allowing to create a drawable rectangle
 */
public class RectangleTool extends Tool {
    private PaintingWindow paintingWindow;
    private DrawableRectangle drawableRectangle;

    /**
     * Constructor
     * @param paintingWindow painting window in which the rectangle is to be created
     * @param color the color in which the rectangle will be created
     */
    public RectangleTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    /**
     * If Left mouse button was pressed creates rectangle with origin in specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableRectangle = new DrawableRectangle(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableRectangle));
        }
    }

    /**
     * Drags the second point of the rectangle to specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableRectangle != null){
            drawableRectangle.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    /**
     * Finishes creation of rectangle
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableRectangle.setFinished();
            drawableRectangle = null;
        }
    }
}
