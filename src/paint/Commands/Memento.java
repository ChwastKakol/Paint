package paint.Commands;

import paint.Drawables.Drawable;

/**
 * Class storing the state of the drawable object
 */
public class Memento {
    protected Drawable drawable;

    /**
     * Default constructor
     */
    public Memento(){
        drawable = null;
    }

    /**
     * Constructor
     * @param object object to be remembered (via copy)
     */
    public Memento(Drawable object) {
        try{
            this.drawable = object.clone();
        }
        catch (CloneNotSupportedException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Gets state of the object
     * @return state of the object
     */
    public Drawable getState(){
        return drawable;
    }
}
