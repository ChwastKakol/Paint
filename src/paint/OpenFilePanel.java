package paint;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class OpenFilePanel extends JPanel {
    private class OpenFileAction extends AbstractAction{
        JPanel parent;
        public OpenFileAction(JPanel parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            var fileChooser = new JFileChooser();
            var filter = new FileNameExtensionFilter("Paint files", "pnt");
            fileChooser.addChoosableFileFilter(filter);

            int ret = fileChooser.showDialog(parent, "Open file");

        }
    }
}
