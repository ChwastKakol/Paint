package paint.Commands;

import paint.Drawables.Drawable;
import paint.PaintingWindow;

public class EditDrawableCommand extends Command {
    PaintingWindow paintingWindow;
    int index;

    public EditDrawableCommand(PaintingWindow paintingWindow, Drawable drawable){
        this.memento = new Memento(drawable);
        this.paintingWindow = paintingWindow;
        index = paintingWindow.getIndexByID(drawable.getID());
    }

    public void setDrawable(Drawable drawable){
        this.drawable = drawable;
    }

    @Override
    public void execute() {
        paintingWindow.setDrawable(index, drawable);
        paintingWindow.redraw();
    }

    @Override
    public void unExecute() {
        paintingWindow.setDrawable(index, memento.getState());
        paintingWindow.redraw();
    }
}
