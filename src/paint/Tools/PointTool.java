package paint.Tools;

import paint.PaintingWindow;
import paint.Drawables.Point;

import java.awt.*;
import java.awt.event.MouseEvent;


public class PointTool extends Tool {
    private PaintingWindow window;
    public PointTool(PaintingWindow window, Color color){
        this.window = window;
        this.color = color;
    }

    @Override
    public void processMouseDown(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            window.addDrawable(new Point(e.getX(), e.getY(), color));
        }
    }
}
