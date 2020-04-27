package paint;
import paint.Drawables.Drawable;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Window in which Drawables may be drawn
 */
public class PaintingWindow extends JPanel {
    private ArrayList<Drawable> drawables;

    /**
     * Default Constructor
     */
    public  PaintingWindow(){
        drawables = new ArrayList<Drawable>();
    }

    /**
     * Method describing how the panel is drawn
     * @param graphics graphics to draw to
     */
    private void draw(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D)graphics;

        for(Drawable d : drawables){
            if(d != null){
                d.draw(graphics2D);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Method for redrawing the content of the panel
     */
    public void redraw(){
        repaint();
    }

    /**
     * Adds drawable to list of drawables
     * @param drawable object to be added
     * @return index of the newly added drawable in the array
     */
    public int addDrawable(Drawable drawable){
        drawables.add(drawable);
        redraw();
        return drawables.size()-1;
    }

    /**
     * Sets specified drawable object
     * @param index index of the object to set
     * @param drawable object to set the drawable to
     */
    public void setDrawable(int index, Drawable drawable){
        drawables.set(index, drawable);
        redraw();
    }

    /**
     * Gets the topmost drawable object containing specified point or null if no such object exists
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return result of the quarry
     */
    public Drawable getCollidedDrawable(int x, int y){
        for(int i = drawables.size()-1; i >= 0; i--){
            Drawable drawable = drawables.get(i);
            if(drawable != null && drawable.contains(x,y)) return drawable;
        }
        return null;
    }

    /**
     * Resets the content of the panel
     */
    public void resetCanvas(){
        drawables = new ArrayList<Drawable>();
        redraw();
    }

    /**
     * Gets the list of all drawables
     * @return list of all drawables
     */
    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * Sets the drawables list to specified one
     * @param drawables drawables list to set
     */
    public void setDrawables(ArrayList<Drawable> drawables) {
        this.drawables = drawables;
        redraw();
    }

    /**
     * return index of the object with specified ID or -1 if no such object exists
     * @param ID to quarry
     * @return result of the quarry
     */
    public int getIndexByID(Long ID){
        for(int i = 0; i < drawables.size(); i++){
            if(drawables.get(i) != null && drawables.get(i).getID() == ID) return i;
        }
        return -1;
    }
}
