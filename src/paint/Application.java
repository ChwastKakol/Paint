package paint;

import paint.Commands.Command;

import java.awt.*;
import java.io.File;
import java.util.Stack;

public class Application {
    private static final Application instance = new Application();
    private static Long ID = 1L;
    private String currentFileName;

    private int undoRedoPointer = -1;
    private Stack<Command> commandStack = new Stack<Command>();

    private Application(){
        currentFileName = "Untitled.img";
        EventQueue.invokeLater(()->{
            MainDisplay display = new MainDisplay();
            display.setVisible(true);
        });
    }

    public Stack<Command> getCommandStack() {
        return commandStack;
    }

    public Long getNewID(){
        ID++;
        return ID;
    }

    private void deleteElementsAfterPointer(){
        if(commandStack.size() < 1) return;
        for(int i = commandStack.size() - 1; i > undoRedoPointer; i--){
            commandStack.pop();
        }
    }

    public void undo(){
        commandStack.get(undoRedoPointer).unExecute();
        undoRedoPointer--;
    }

    public void redo(){
        if(undoRedoPointer >= commandStack.size() - 1) return;
        undoRedoPointer++;
        commandStack.get(undoRedoPointer).execute();
    }

    public void addCommand(Command command){
        deleteElementsAfterPointer();
        command.execute();
        commandStack.push(command);
        undoRedoPointer++;
    }

    public static Application getInstance() {
        return instance;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
    }

    public void saveFile(File file){
        setCurrentFileName(file.getName());
    }

    public void loadFile(File file){
        setCurrentFileName(file.getName());
    }
}
