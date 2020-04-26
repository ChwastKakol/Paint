package paint.Tools;

import paint.Application;
import paint.Commands.EditDrawableCommand;
import paint.Drawables.Drawable;
import paint.PaintingWindow;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EditTool extends Tool {
    private Drawable drawable;
    private int  x, y;
    private PaintingWindow paintingWindow;

    private EditDrawableCommand command;

    public EditTool(PaintingWindow paintingWindow, Color color){
        this.paintingWindow = paintingWindow;
        this.color = color;
    }

    @Override
    public void processMouseDown(MouseEvent e) {
        drawable = paintingWindow.getCollidedDrawable(e.getX(), e.getY());
        if(drawable != null){
            command = new EditDrawableCommand(paintingWindow, drawable);

            if(e.getButton() == MouseEvent.BUTTON1){
                x = e.getX();
                y = e.getY();
            }
            else if(e.getButton() == MouseEvent.BUTTON3) {
                drawable.setColor(color);
                command.setDrawable(drawable);
                Application.getInstance().addCommand(command);
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
        if(drawable != null && e.getButton() == MouseEvent.BUTTON1 && command != null){
            command.setDrawable(drawable);
            Application.getInstance().addCommand(command);
        }
    }

    @Override
    public Drawable getSelected(){
        return drawable;
    }
}
