package paint.Commands;

import paint.Drawables.Drawable;
import paint.PaintingWindow;

/**
 * Command wrapping editing a drawable figure
 */
public class EditDrawableCommand extends Command {
    PaintingWindow paintingWindow;
    int index;

    /**
     * Constructor
     * @param paintingWindow <code>PaintingWindow</code> in which the object is to be edited
     * @param drawable object to be edited
     */
    public EditDrawableCommand(PaintingWindow paintingWindow, Drawable drawable){
        this.memento = new Memento(drawable);
        this.paintingWindow = paintingWindow;
        index = paintingWindow.getIndexByID(drawable.getID());
    }

    /**
     * Sets the state of object after the edit
     * @param drawable
     */
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
