package paint.Commands;

import paint.Drawables.Drawable;

public abstract class Command {
    protected Drawable drawable;
    protected Memento memento;

    public abstract void execute();

    public abstract void  unExecute();
}
