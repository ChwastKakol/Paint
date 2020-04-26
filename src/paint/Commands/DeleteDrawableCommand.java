package paint.Commands;

import paint.Drawables.Drawable;
import paint.PaintingWindow;

public class DeleteDrawableCommand extends Command{
    PaintingWindow paintingWindow;
    int index;

    public DeleteDrawableCommand(PaintingWindow paintingWindow, Drawable drawable){
        this.paintingWindow = paintingWindow;
        this.memento = new Memento(drawable);
        this.drawable = null;
        index = paintingWindow.getIndexByID(drawable.getID());
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
