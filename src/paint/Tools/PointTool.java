package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.PaintingWindow;
import paint.Drawables.Point;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tool allowing to create a drawable point
 */
public class PointTool extends Tool {
    private PaintingWindow window;

    /**
     * Constructor
     * @param window painting window in which the point is to be created
     * @param color the color in which the point will be created
     */
    public PointTool(PaintingWindow window, Color color){
        this.window = window;
        this.color = color;
    }

    /**
     * If Left mouse button was pressed creates point in specified location
     * @param e mouseEvent to be processed
     */
    @Override
    public void processMouseDown(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            Application.getInstance().addCommand(new AddDrawableCommand(window, new Point(e.getX(), e.getY(), color)));
        }
    }
}
