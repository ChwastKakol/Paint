package paint.Tools;

import paint.Application;
import paint.Drawables.Line;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends Tool {
    private PaintingWindow paintingWindow;
    private Line line;

    public LineTool(PaintingWindow paintingWindow, Color color){
        this.color = color;
        this.paintingWindow = paintingWindow;
    }

    @Override
    public void processMouseDown(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println(color.toString());
            line = new Line(e.getX(), e.getY(), e.getX(), e.getY(), color);
            //Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow, line));
            paintingWindow.addDrawable(line);
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(line != null){
            line.x2 = e.getX();
            line.y2 = e.getY();
        }
        paintingWindow.redraw();
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            line = null;
        }
    }
}
