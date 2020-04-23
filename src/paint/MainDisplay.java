package paint;

import paint.Drawables.Drawable;
import paint.Drawables.EmptyDrawable;
import paint.Tools.LineTool;
import paint.Tools.PointTool;
import paint.Tools.RectangleTool;
import paint.Tools.Tool;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainDisplay extends JFrame{

    JPopupMenu popupMenu;
    Color color;
    PaintingWindow paintingWindow;
    Tool tool;

    public MainDisplay(){
        init();
    }

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

        add(paintingWindow, BorderLayout.CENTER);

        tool = new LineTool(paintingWindow, color);

        createToolBar();
    }

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

    private void createMenus(){
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        addMenuItem(fileMenu, "New", KeyEvent.VK_N, "src/paint/resources/new.png", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                paintingWindow.resetCanvas();
            }
        });
        addMenuItem(fileMenu, "Save", KeyEvent.VK_S, "src/paint/resources/save-24px.png", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var panel = new JPanel();
                var fileChooser= new JFileChooser();
                var filter = new FileNameExtensionFilter("Paint files", "pnt");
                fileChooser.addChoosableFileFilter(filter);

                int ret = fileChooser.showSaveDialog(panel);
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
        });
        addMenuItem(fileMenu, "Open", KeyEvent.VK_O, "src/paint/resources/open.png", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                var panel = new JPanel();
                var fileChooser = new JFileChooser();
                var filter = new FileNameExtensionFilter("Paint files", "pnt");
                fileChooser.addChoosableFileFilter(filter);

                int ret = fileChooser.showOpenDialog(panel);
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
        });
        fileMenu.addSeparator();
        addMenuItem(fileMenu,"Exit", KeyEvent.VK_E, "src/paint/resources/close.png", null);

        var editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        addMenuItem(editMenu, "Undo", KeyEvent.VK_Z, null, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Application.getInstance().undo();
            }
        });
        addMenuItem(editMenu, "Redo", KeyEvent.VK_Y, null, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Application.getInstance().redo();
            }
        });
        editMenu.addSeparator();
        addMenuItem(editMenu, "Cut", KeyEvent.VK_X, null, null);
        addMenuItem(editMenu, "Copy", KeyEvent.VK_C, null, null);
        addMenuItem(editMenu, "Paste", KeyEvent.VK_V, null, null);
        editMenu.addSeparator();
        addMenuItemNoCtrl(editMenu, "Delete", KeyEvent.VK_DELETE);

        var helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        var infoMenuItem = new JMenuItem("Info");
        helpMenu.add(infoMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void createToolBar(){
        var toolBar = new JToolBar(JToolBar.VERTICAL);

        var pointButton = new JButton();
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

        add(toolBar, BorderLayout.WEST);
    }

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
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, InputEvent.CTRL_DOWN_MASK));
        menu.add(jMenuItem);
    }

    void addMenuItemNoCtrl(JMenu menu, String text, int accelerator){
        var jMenuItem = new JMenuItem(text);
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, 0));
        menu.add(jMenuItem);
    }

}
