package paint;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JDialog {
    private static final String about  = "This Application was created by Piotr Kakol as part of \"Programowanie obiektowe\" course.";

    public InfoWindow(Frame parent){
        super(parent);
        init();
    }

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
