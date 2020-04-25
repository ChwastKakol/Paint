package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.PaintingWindow;
import paint.Drawables.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleTool extends Tool {
    private PaintingWindow paintingWindow;
    private Rectangle rectangle;

    public RectangleTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println(color.toString());
            rectangle = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), color);
            //paintingWindow.addDrawable(rectangle);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, rectangle));
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(rectangle != null){
            rectangle.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            rectangle = null;
        }
    }
}
