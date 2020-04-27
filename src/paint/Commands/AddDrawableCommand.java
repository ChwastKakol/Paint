package paint.Commands;
import paint.Drawables.Drawable;
import paint.PaintingWindow;

/**
 * Command wrapping adding new Drawable Figure
 */
public class AddDrawableCommand extends Command {
    protected PaintingWindow paintingWindow;
    int index;

    /**
     * Constructor
     * @param paintingWindow <code>PaintingWindow</code> to which object is to be added
     * @param drawable object to be added
     */
    public AddDrawableCommand(PaintingWindow paintingWindow, Drawable drawable){
        this.paintingWindow = paintingWindow;
        this.drawable = drawable;
        this.memento = new Memento();
        index = paintingWindow.addDrawable(null);
    }

    @Override
    public void execute() {
        paintingWindow.setDrawable(index, drawable);
    }

    @Override
    public void unExecute() {
        paintingWindow.setDrawable(index, memento.getState());
    }
}
