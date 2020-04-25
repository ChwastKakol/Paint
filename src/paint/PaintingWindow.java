package paint;

import paint.Commands.Command;
import paint.Drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintingWindow extends JPanel {
    private ArrayList<Drawable> drawables;

    public  PaintingWindow(){
        drawables = new ArrayList<Drawable>();
    }

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

    public void redraw(){
        revalidate();
        repaint();
    }

    public int addDrawable(Drawable drawable){
        drawables.add(drawable);
        redraw();
        return drawables.size()-1;
    }

    public void setDrawable(int index, Drawable drawable){
        drawables.set(index, drawable);
        redraw();
    }

    public Drawable getCollidedDrawable(int x, int y){
        for(int i = drawables.size()-1; i >= 0; i--){
            Drawable drawable = drawables.get(i);
            if(drawable != null && drawable.contains(x,y)) return drawable;
        }
        return null;
    }

    public void resetCanvas(){
        drawables = new ArrayList<Drawable>();
        redraw();
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public void setDrawables(ArrayList<Drawable> drawables) {
        this.drawables = drawables;
        redraw();
    }
}
