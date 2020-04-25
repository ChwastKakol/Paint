package paint.Tools;

import paint.Drawables.Drawable;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EditTool extends Tool {
    private Drawable drawable;
    private PaintingWindow paintingWindow;

    public EditTool(PaintingWindow paintingWindow, Color color){
        this.paintingWindow = paintingWindow;
        this.color = color;
    }

    @Override
    public void processMouseDown(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            drawable = paintingWindow.getCollidedDrawable(e.getX(), e.getY());
            if (drawable != null) {
                drawable.setColor(color);
                paintingWindow.redraw();
            }
        }
    }
}
