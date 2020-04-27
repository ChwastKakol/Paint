package paint;

import paint.Commands.AddDrawableCommand;
import paint.Commands.DeleteDrawableCommand;
import paint.Drawables.Drawable;
import paint.Drawables.DrawableRectangle;
import paint.Tools.*;

import javax.management.openmbean.InvalidOpenTypeException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Main display window of the application
 */
public class MainDisplay extends JFrame{

    JPopupMenu popupMenu;
    Drawable clipBoard;
    Color color;
    PaintingWindow paintingWindow;
    Tool tool;

    /**
     * Default constructor
     */
    public MainDisplay(){
        init();
    }

    /**
     * Initializer method called by the constructor
     */
    private void init(){
        setTitle("Paint | " + Application.getInstance().getCurrentFileName());

        setSize(1280, 720);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        color = Color.RED;

        createMenus();
        createPopupMenu();

        paintingWindow = new PaintingWindow();

        paintingWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tool.processMouseDown(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                tool.processMouseUp(e);
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        paintingWindow.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                tool.processMouseDragged(e);
            }
        });
        paintingWindow.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                tool.processMouseWheel(mouseWheelEvent);
            }
        });

        add(paintingWindow, BorderLayout.CENTER);

        tool = new LineTool(paintingWindow, color);

        createToolBar();
    }

    /**
     * Creates color selector popup menu
     */
    private void createPopupMenu(){
        popupMenu = new JPopupMenu();
        var chooseColorMenuItem = new JMenuItem("Choose Color");
        chooseColorMenuItem.addActionListener(event ->{
            JPanel colorPanel = new JPanel();
            color = JColorChooser.showDialog(colorPanel, "Choose color", color);
            tool.setColor(color);
        });
        popupMenu.add(chooseColorMenuItem);
    }

    /**
     * Creates Menu Bar
     */
    private void createMenus(){
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        addMenuItem(fileMenu, "New", KeyEvent.VK_N, "src/paint/resources/new.png", event -> paintingWindow.resetCanvas());
        addMenuItem(fileMenu, "Save", KeyEvent.VK_S, "src/paint/resources/save-24px.png", event -> save());
        addMenuItem(fileMenu, "Open", KeyEvent.VK_O, "src/paint/resources/open.png", event -> open());
        fileMenu.addSeparator();
        addMenuItem(fileMenu,"Exit", KeyEvent.VK_E, "src/paint/resources/close.png", event -> dispose());

        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        addMenuItem(editMenu, "Undo", KeyEvent.VK_Z, null, event -> Application.getInstance().undo());
        addMenuItem(editMenu, "Redo", KeyEvent.VK_Y, null, event -> Application.getInstance().redo());
        editMenu.addSeparator();
        addMenuItem(editMenu, "Cut", KeyEvent.VK_X, null, event -> cut());
        addMenuItem(editMenu, "Copy", KeyEvent.VK_C, null, event -> setClipBoard(tool.getSelected()));
        addMenuItem(editMenu, "Paste", KeyEvent.VK_V, null, event -> paste());
        editMenu.addSeparator();
        addMenuItemNoCtrl(editMenu, "Delete", KeyEvent.VK_DELETE, event -> delete());

        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        addMenuItem(helpMenu, "Info", 0, null, event -> showInfoWindow());
        addMenuItem(helpMenu, "User Manual", 0 ,null, event -> showUserManualWindow());

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Creates Tool Bar
     */
    private void createToolBar(){
        var toolBar = new JToolBar(JToolBar.VERTICAL);

        var pointButton = new JButton(new ImageIcon("src/paint/resources/pointTool.png"));
        pointButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tool = new PointTool(paintingWindow, color);
            }
        });
        pointButton.setToolTipText("Draws a single point");
        toolBar.add(pointButton);

        var lineButton = new JButton(new ImageIcon("src/paint/resources/lineTool.png"));
        lineButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tool = new LineTool(paintingWindow, color);
            }
        });
        lineButton.setToolTipText("Draws a line");
        toolBar.add(lineButton);

        var rectangleButton = new JButton(new ImageIcon("src/paint/resources/rectangleTool.png"));
        rectangleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tool = new RectangleTool(paintingWindow, color);
            }
        });
        rectangleButton.setToolTipText("Draws a filled rectangle");
        toolBar.add(rectangleButton);

        var ellipseButton = new JButton(new ImageIcon("src/paint/resources/ellipseTool.png"));
        ellipseButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tool = new EllipseTool(paintingWindow, color);
            }
        });
        ellipseButton.setToolTipText("Draws a filled ellipse");
        toolBar.add(ellipseButton);

        var triangleButton = new JButton(new ImageIcon("src/paint/resources/triangleTool.png"));
        triangleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tool = new TriangleTool(paintingWindow, color);
            }
        });

        triangleButton.setToolTipText("Draws a filled triangle");
        toolBar.add(triangleButton);

        var editButton = new JButton(new ImageIcon("src/paint/resources/editTool.png"));
        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tool = new EditTool(paintingWindow, color);
            }
        });
        editButton.setToolTipText("Allows to select figure for copying, move, rotate, scale it and change its colour");
        toolBar.add(editButton);

        add(toolBar, BorderLayout.WEST);
    }

    /**
     * Method for adding button to menu
     * @param menu menu to add button to
     * @param text button text
     * @param accelerator keyboard shortcut to access button's function, 0 sets no shortcut
     * @param iconName address of icon for the button, null sets no icon
     * @param actionListener action to be performed after clicking, null sets it to create dialogue message
     */
    void addMenuItem(JMenu menu, String text, int accelerator, String iconName, ActionListener actionListener){
        var jMenuItem = new JMenuItem(text);
        if(actionListener != null){
            jMenuItem.addActionListener(actionListener);
        }
        else {
            jMenuItem.addActionListener(event -> JOptionPane.showMessageDialog(this, text + " has been pressed", "Information", JOptionPane.INFORMATION_MESSAGE));
        }
        if(iconName != null){
            Icon icon = new ImageIcon(iconName);
            jMenuItem.setIcon(icon);
        }
        if(accelerator != 0) {
            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, InputEvent.CTRL_DOWN_MASK));
        }
        menu.add(jMenuItem);
    }

    /**
     * Method for adding button to menu, with keyboard shortcut without CTRL
     * @param menu menu to add button to
     * @param text button text
     * @param accelerator keyboard shortcut to access button's function
     * @param actionListener action to be performed after clicking, null sets it to create dialogue message
     */
    void addMenuItemNoCtrl(JMenu menu, String text, int accelerator, ActionListener actionListener){
        var jMenuItem = new JMenuItem(text);
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, 0));
        if(actionListener != null){
            jMenuItem.addActionListener(actionListener);
        }
        menu.add(jMenuItem);
    }

    private void setClipBoard(Drawable drawable){
        try {
            if(drawable != null) {
                clipBoard = drawable.clone();
            }
        }
        catch (CloneNotSupportedException e){
            System.out.println(e);
        }
    }

    /**
     * Shows Information Dialogue window
     */
    private void showInfoWindow(){
        var infoWindow = new InfoWindow(this);
        infoWindow.setVisible(true);
    }

    /**
     * Shows User Manual Window
     */
    private void showUserManualWindow(){
        var manualWindow = new UserManualWindow(this);
        manualWindow.setVisible(true);
    }

    /**
     * Saves the content of paintingWindow
     */
    private void save(){
        var fileChooser= new JFileChooser();
        var filter = new FileNameExtensionFilter("Paint files", "pnt");
        fileChooser.addChoosableFileFilter(filter);

        int ret = fileChooser.showSaveDialog(this);
        if(ret == JFileChooser.APPROVE_OPTION){
            var file = fileChooser.getSelectedFile();

            try{
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(paintingWindow.getDrawables());
            }
            catch (IOException e){
                System.out.println(e.getLocalizedMessage());
            }

            Application.getInstance().saveFile(file);
            setTitle("Paint | " + Application.getInstance().getCurrentFileName());
        }
    }

    /**
     * Loads content of the painting window from file
     */
    private void open(){
        var fileChooser = new JFileChooser();
        var filter = new FileNameExtensionFilter("Paint files", "pnt");
        fileChooser.addChoosableFileFilter(filter);

        int ret = fileChooser.showOpenDialog(this);
        if(ret == JFileChooser.APPROVE_OPTION){
            var file = fileChooser.getSelectedFile();

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                paintingWindow.setDrawables((ArrayList<Drawable>)objectInputStream.readObject());
            }
            catch(IOException e){
                System.out.println(e.getLocalizedMessage());
            }
            catch (ClassNotFoundException e){
                System.out.println(e.getLocalizedMessage());
            }

            Application.getInstance().saveFile(file);
            setTitle("Paint | " + Application.getInstance().getCurrentFileName());
        }
    }

    /**
     * Cuts the drawable figure from painting window
     */
    private void cut(){
        setClipBoard(tool.getSelected());
        if(tool.getSelected() != null){
            Application.getInstance().addCommand(new DeleteDrawableCommand(paintingWindow, tool.getSelected()));
        }
    }

    /**
     * Pastes the drawable figure from painting window
     */
    private void paste(){
        if(clipBoard != null){
            try {
                Drawable copy = clipBoard.clone();
                copy.setID(Application.getInstance().getNewID());
                copy.translate(5,5);
                Application.getInstance().addCommand(new AddDrawableCommand(paintingWindow,copy));
            }
            catch (CloneNotSupportedException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    /**
     * Deletes the drawable figure from painting window
     */
    private void delete(){
        if(tool.getSelected() != null){
            Application.getInstance().addCommand(new DeleteDrawableCommand(paintingWindow, tool.getSelected()));
        }
    }

}
