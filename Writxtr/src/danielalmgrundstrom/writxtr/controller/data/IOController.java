package danielalmgrundstrom.writxtr.controller.data;

import java.io.File;

import javafx.stage.FileChooser;

import danielalmgrundstrom.writxtr.beans.FontsRecievedEvent;
import danielalmgrundstrom.writxtr.beans.IOErrorEvent;
import danielalmgrundstrom.writxtr.beans.SaveEvent;
import danielalmgrundstrom.writxtr.listeners.FontsRecievedListener;
import danielalmgrundstrom.writxtr.listeners.IOErrorListener;
import danielalmgrundstrom.writxtr.listeners.LoadClickedListener;
import danielalmgrundstrom.writxtr.listeners.LoadFileListener;
import danielalmgrundstrom.writxtr.listeners.NewClickedListener;
import danielalmgrundstrom.writxtr.listeners.SaveClickedListener;
import danielalmgrundstrom.writxtr.listeners.SaveListener;
import danielalmgrundstrom.writxtr.ui.FontWindow;
import danielalmgrundstrom.writxtr.ui.MainView;

public class IOController implements SaveClickedListener,
                                     LoadClickedListener,
                                     NewClickedListener,
                                     IOErrorListener,
                                     FontsRecievedListener
{

    private SaveListener saveListener;
    private LoadFileListener loadFileListener;

    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter txtFilter;
    private File currentFile;

    private MainView window;

    public IOController(MainView window)
    {
        this.window = window;
        fileChooser = new FileChooser();

        txtFilter = new FileChooser.ExtensionFilter("Text files ( .txt .text )",
                                                    "txt", "text");
        fileChooser.getExtensionFilters().add(txtFilter);
    }

    @Override
    public void onSaveClicked(SaveEvent event)
    {
        if (currentFile != null)
        { fireSaveEvent(currentFile, event); }
        else
        { onSaveAsClicked(event); }
    }

    @Override
    public void onSaveAsClicked(SaveEvent event)
    {
        File file = fileChooser.showSaveDialog(window.getScene().getWindow());

        if (file != null)
        {
            if (fileChooser.getSelectedExtensionFilter() == txtFilter)
            {
                if (!file.getName().toLowerCase().endsWith(".txt"))
                {
                    File newFile = new File(file.getPath() + ".txt");
                    file = newFile;
                    System.out.println(newFile);
                }
            }

            currentFile = file;

            fireSaveEvent(file, event);
        }
    }

    @Override
    public void onLoadFileClicked()
    {
        File file = fileChooser.showOpenDialog(window.getScene().getWindow());

        if (file != null)
        {
            currentFile = file;
            fireLoadFileRequest(file);
        }

    }

    public void setSaveListener(SaveListener saveListener)
    {
        this.saveListener = saveListener;
    }

    public void setLoadFileListener(LoadFileListener loadFileListener)
    {
        this.loadFileListener = loadFileListener;
    }

    public void fireSaveEvent(File file, SaveEvent event)
    {
        if (saveListener != null)
        { saveListener.onSaveRequest(file, event); }
    }

    public void fireLoadFileRequest(File file)
    {
        if (loadFileListener != null)
        { loadFileListener.onLoadFileRequest(file); }
    }

    @Override
    public void onIOError(IOErrorEvent event)
    {
        System.out.println(event.getErrorMessage());
    }

    @Override
    public void onFontsRecieved(FontsRecievedEvent event)
    {
        FontWindow.setFontNames(event.getFontNames());
    }

    @Override
    public void onNewClicked()
    {
        currentFile = null;
    }
}
