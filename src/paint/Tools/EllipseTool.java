package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.Drawables.DrawableEllipse;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EllipseTool extends Tool {
    private PaintingWindow paintingWindow;
    private DrawableEllipse drawableEllipse;

    public EllipseTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableEllipse = new DrawableEllipse(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableEllipse));
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableEllipse != null){
            drawableEllipse.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableEllipse.setFinished();
            drawableEllipse = null;
        }
    }
}
