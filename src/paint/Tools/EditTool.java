package paint.Tools;

import paint.Drawables.Drawable;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EditTool extends Tool {
    private Drawable drawable;
    private int  x, y;
    private PaintingWindow paintingWindow;

    public EditTool(PaintingWindow paintingWindow, Color color){
        this.paintingWindow = paintingWindow;
        this.color = color;
    }

    @Override
    public void processMouseDown(MouseEvent e) {
        drawable = paintingWindow.getCollidedDrawable(e.getX(), e.getY());

        if(e.getButton() == MouseEvent.BUTTON1){
            if(drawable != null){
                x = e.getX();
                y = e.getY();
            }
        }

        else if(e.getButton() == MouseEvent.BUTTON3) {
            if (drawable != null) {
                drawable.setColor(color);
                paintingWindow.redraw();
            }
        }
    }

    @Override
    public void processMouseDragged(MouseEvent e) {
        if(drawable != null) {
            int dx = e.getX() - x;
            drawable.translate(dx, e.getY() - y);
            x = e.getX();
            y = e.getY();
            paintingWindow.redraw();
        }
    }

    @Override
    public void processMouseUp(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            drawable = null;
        }
    }
}
