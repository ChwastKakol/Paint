package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.Drawables.DrawableLine;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tool allowing to create a drawable line
 */
public class LineTool extends Tool {
    private PaintingWindow paintingWindow;
    private DrawableLine drawableLine;

    /**
     * Constructor
     * @param paintingWindow painting window in which the line is to be created
     * @param color the color in which the line will be created
     */
    public LineTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    /**
     * If Left mouse button was pressed creates line with origin in specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableLine = new DrawableLine(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableLine));
        }
    }

    /**
     * Drags the second point of the line to specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableLine != null){
            drawableLine.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    /**
     * Finishes creation of line
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableLine.setFinished();
            drawableLine = null;
        }
    }
}
