package paint.Commands;
import paint.Drawables.Drawable;
import paint.PaintingWindow;


public class AddDrawableCommand extends Command {
    protected PaintingWindow paintingWindow;
    int index;

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
