package paint;

import paint.Commands.Command;
import paint.Drawables.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class PaintingWindow extends JPanel {
    private ArrayList<Drawable> drawables;

    HashMap<Long, Drawable> drawableMap;
    private Stack<Command> commands;

    public  PaintingWindow(){
        drawables = new ArrayList<Drawable>();
        drawableMap = new HashMap<Long, Drawable>();
        commands = Application.getInstance().getCommandStack();
    }

    public void CreateDrawableMap(){
        /*for(Command c : commands){
            if(drawableMap.containsKey(c.))
        }*/
    }

    private void draw(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D)graphics;

        for(Drawable d : drawables){
            d.draw(graphics2D);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void redraw(){
        //revalidate();
        repaint();
    }

    public void addDrawable(Drawable drawable){
        drawables.add(drawable);
        revalidate();
        repaint();
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
