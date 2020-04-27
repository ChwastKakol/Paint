package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.Drawables.DrawableEllipse;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tool allowing to create a drawable ellipse
 */
public class EllipseTool extends Tool {
    private PaintingWindow paintingWindow;
    private DrawableEllipse drawableEllipse;

    /**
     * Constructor
     * @param paintingWindow painting window in which the ellipse is to be created
     * @param color the color in which the ellipse will be created
     */
    public EllipseTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    /**
     * If Left mouse button was pressed creates ellipse with origin in specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableEllipse = new DrawableEllipse(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableEllipse));
        }
    }

    /**
     * Drags the second point of the ellipse to specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableEllipse != null){
            drawableEllipse.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    /**
     * Finishes creation of ellipse
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableEllipse.setFinished();
            drawableEllipse = null;
        }
    }
}
