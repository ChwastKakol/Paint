package paint.Commands;

import paint.Drawables.Drawable;

/**
 * Abstract class wrapping actions in <code>PaintingWindow</code> to provide undo, redo functionality
 */
public abstract class Command {
    protected Drawable drawable;
    protected Memento memento;

    /**
     * executes the command (for redo)
     */
    public abstract void execute();

    /**
     * cancels execution of the command (for undo)
     */
    public abstract void  unExecute();
}
