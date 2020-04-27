package paint;

import javax.swing.*;
import java.awt.*;

/**
 * Dialogue window showing information about the program
 */
public class InfoWindow extends JDialog {
    private static final String about  = "This Application was created by Piotr Kakol as part of \"Programowanie obiektowe\" course.";

    /**
     * Constructor
     * @param parent component to become the parent of the dialogue window
     */
    public InfoWindow(Frame parent){
        super(parent);
        init();
    }

    /**
     * Initialized method called by constructor
     */
    private void init(){
        setTitle("About application");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());

        var aboutText = new JLabel(about);
        var okButton = new JButton("OK");
        okButton.addActionListener(event -> dispose());

        var pane = getContentPane();
        var groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
        .addComponent(aboutText).addComponent(okButton));

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(aboutText).addComponent(okButton));
        pack();
    }

}
