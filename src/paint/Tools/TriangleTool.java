package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.Drawables.DrawableTriangle;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tool allowing to create a drawable triangle
 */
public class TriangleTool extends Tool{
    private PaintingWindow paintingWindow;
    private DrawableTriangle drawableTriangle;

    /**
     * Constructor
     * @param paintingWindow painting window in which the triangle is to be created
     * @param color the color in which the triangle will be created
     */
    public TriangleTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    /**
     * If Left mouse button was pressed creates triangle with origin in specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableTriangle = new DrawableTriangle(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableTriangle));
        }
    }

    /**
     * Drags the second point of the triangle to specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableTriangle != null){
            drawableTriangle.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    /**
     * Finishes creation of triangle
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableTriangle.setFinished();
            drawableTriangle = null;
        }
    }
}
