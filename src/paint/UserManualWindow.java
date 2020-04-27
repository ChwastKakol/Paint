package paint;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * User manual dialogue window
 */
public class UserManualWindow extends JDialog {
    private static String manual;

    /**
     * Constructor
     * @param parent component to become the parent of the dialogue window
     */
    public UserManualWindow(Frame parent){
        super(parent);
        readManualFromFile();
        init();
    }

    /**
     * Initialized method called by constructor
     */
    private void init(){
        setTitle("User Manual");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());

        var aboutText = new JLabel(manual);
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

    private void readManualFromFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("src/paint/resources/manual.txt");
            manual = new String(fileInputStream.readAllBytes());
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
