package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.PaintingWindow;
import paint.Drawables.DrawableRectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleTool extends Tool {
    private PaintingWindow paintingWindow;
    private DrawableRectangle drawableRectangle;

    public RectangleTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableRectangle = new DrawableRectangle(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableRectangle));
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableRectangle != null){
            drawableRectangle.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableRectangle.setFinished();
            drawableRectangle = null;
        }
    }
}
