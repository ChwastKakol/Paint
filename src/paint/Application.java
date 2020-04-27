package paint;

import paint.Commands.Command;

import java.awt.*;
import java.io.File;
import java.util.Stack;

/**
 * Singleton class acting as the application master
 */
public class Application {
    private static final Application instance = new Application();
    private static Long ID = 1L;
    private String currentFileName;

    private int undoRedoPointer = -1;
    private Stack<Command> commandStack = new Stack<Command>();

    /**
     * Default constructor
     */
    private Application(){
        currentFileName = "Untitled.img";
        EventQueue.invokeLater(()->{
            MainDisplay display = new MainDisplay();
            display.setVisible(true);
        });
    }

    /**
     * Distributes unique IDs
     * @return ID
     */
    public Long getNewID(){
        ID++;
        return ID;
    }

    /**
     * Deletes all command chronologically after the pointer
     */
    private void deleteElementsAfterPointer(){
        if(commandStack.size() < 1) return;
        for(int i = commandStack.size() - 1; i > undoRedoPointer; i--){
            commandStack.pop();
        }
    }

    /**
     * Cancels the last command
     */
    public void undo(){
        if(undoRedoPointer > -1) {
            commandStack.get(undoRedoPointer).unExecute();
            undoRedoPointer--;
        }
    }

    /**
     * Repeats the last command
     */
    public void redo(){
        if(undoRedoPointer >= commandStack.size()-1) return;
        undoRedoPointer++;
        commandStack.get(undoRedoPointer).execute();

    }

    /**
     * Adds command to the stack
     * @param command command to be added
     */
    public void addCommand(Command command){
        deleteElementsAfterPointer();
        command.execute();
        commandStack.push(command);
        undoRedoPointer++;
    }

    /**
     * Gets instance of the application
     * @return instance of the application
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     * Gets name of the file that is being worked on
     * @return name of the file that is being worked on
     */
    public String getCurrentFileName() {
        return currentFileName;
    }

    /**
     * Sets the name of the file that is being worked on
     * @param currentFileName name of the file that is being worked on
     */
    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
    }

    /**
     * Method called during file saving
     * @param file file to which current work is saved
     */
    public void saveFile(File file){
        setCurrentFileName(file.getName());
    }

    /**
     * Method called during file loading
     * @param file file from which content is loaded
     */
    public void loadFile(File file){
        setCurrentFileName(file.getName());
    }
}
