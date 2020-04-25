package paint.Tools;

import paint.Application;
import paint.Commands.AddDrawableCommand;
import paint.Drawables.DrawableTriangle;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TriangleTool extends Tool{
    private PaintingWindow paintingWindow;
    private DrawableTriangle drawableTriangle;

    public TriangleTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println(color.toString());
            drawableTriangle = new DrawableTriangle(e.getX(), e.getY(), e.getX(), e.getY(), color);
            //paintingWindow.addDrawable(rectangle);
            Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, drawableTriangle));
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawableTriangle != null){
            drawableTriangle.setSecondPoint(e.getX(), e.getY());
        }
        paintingWindow.redraw();
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawableTriangle = null;
        }
    }
}
