package paint.Commands;

import paint.Drawables.Drawable;

public class Memento {
    protected Drawable drawable;

    public Memento(){
        drawable = null;
    }

    public Memento(Drawable object) {
        try{
            this.drawable = object.clone();
        }
        catch (CloneNotSupportedException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public Drawable getState(){
        return drawable;
    }

    public void setState(Drawable object){
        this.drawable = object;
    }
}
