package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.Drawables.DrawableLine;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends Tool {
    private PaintingWindow paintingWindow;
    private DrawableLine drawableLine;

    public LineTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableLine = new DrawableLine(e.getX(), e.getY(), e.getX(), e.getY(), color);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableLine));
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableLine != null){
            drawableLine.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableLine.setFinished();
            drawableLine = null;
        }
    }
}
