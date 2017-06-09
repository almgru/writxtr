package writxtr.controller.ui;

import writxtr.beans.LoadFileEvent;
import writxtr.beans.LoadURLEvent;
import writxtr.beans.SaveCompleteEvent;
import writxtr.beans.SaveEvent;
import writxtr.controller.Controller;
import writxtr.io.system.FontHandler;
import writxtr.listeners.*;
import writxtr.ui.MainView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class WindowController implements SaveCompleteListener,
        LoadCompleteListener
{
    private MainView window;

    public static final String NEW_CONFIRM_MESSAGE = "Document has been changed since last save.\nDo you want to save" +
            " before creating a new document?";

    public static final String QUIT_CONFIRM_MESSAGE = "Document has been changed since last save.\nDo you want to " +
            "save before quitting.";

    public static final String OPEN_CONFIRM_MESSAGE = "Document has been changed since last save.\nDo you want to " +
            "save before loading a new file.";

    private MenuController menuController;
    private TextAreaController textAreaController;
    private ToolBarController toolBarController;

    private FontRequestListener fontRequestListener;

    private NewClickedListener newClickedListener;
    private SaveClickedListener saveRequestListener;
    private LoadClickedListener loadRequestListener;

    public WindowController(MainView window, FontHandler fontHandler)
    {
        this.window = window;
        setFontRequestListener(fontHandler);
        fireFontRequest();
    }

    public void init()
    {
        menuController = new MenuController(window, this);
        textAreaController = new TextAreaController(window, this);
        toolBarController = new ToolBarController(window, this);
    }

    public MenuController getMenuController()
    {
        return menuController;
    }

    public TextAreaController getTextAreaController()
    {
        return textAreaController;
    }

    public ToolBarController getToolBarController()
    {
        return toolBarController;
    }

    public void setNewClickedListener(NewClickedListener newClickedListener)
    {
        this.newClickedListener = newClickedListener;
    }

    public void setSaveClickedListener(SaveClickedListener saveListener)
    {
        this.saveRequestListener = saveListener;
    }

    public void setLoadClickedListener(LoadClickedListener loadListener)
    {
        this.loadRequestListener = loadListener;
    }

    public void setFontRequestListener(FontRequestListener fontRequestListener)
    {
        this.fontRequestListener = fontRequestListener;
        fireFontRequest();
    }

    public void fireNewClickedEvent()
    {
        if (newClickedListener != null)
        {
            newClickedListener.onNewClicked();
        }
    }

    public void fireSaveEvent(SaveEvent event)
    {
        if (saveRequestListener != null)
        {
            saveRequestListener.onSaveClicked(event);
        }
    }

    public void fireSaveAsEvent(SaveEvent event)
    {
        if (saveRequestListener != null)
        {
            saveRequestListener.onSaveAsClicked(event);
        }
    }

    public void fireLoadFileRequest()
    {
        if (loadRequestListener != null)
        {
            loadRequestListener.onLoadFileClicked();
        }
    }

    public void fireFontRequest()
    {
        if (fontRequestListener != null)
        {
            fontRequestListener.onFontRequest();
        }
    }

    @Override
    public void onLoadFileComplete(LoadFileEvent event)
    {
        window.getWindowTextArea().setText(event.getFileContent());
        window.getWindowTextArea().setTextChanged(false);
    }

    @Override
    public void onLoadURLComplete(LoadURLEvent event)
    {
        window.getWindowTextArea().setText(event.getURLContent());
        window.getWindowTextArea().setTextChanged(true);
    }

    public void onNew()
    {
        if (window.getWindowTextArea().getTextChanged())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, NEW_CONFIRM_MESSAGE, ButtonType.YES, ButtonType.NO,
                                    ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
            {
                Controller.newOnSaveCompleted = true;
                onSave();
            }
            else if (alert.getResult() == ButtonType.NO)
            {
                window.getWindowTextArea().setText("");
                window.getWindowTextArea().setTextChanged(false);
                fireNewClickedEvent();
            }
        }
        else
        {
            window.getWindowTextArea().setText("");
            window.getWindowTextArea().setTextChanged(false);
            fireNewClickedEvent();
        }
    }

    public void onSave()
    {
        fireSaveEvent(new SaveEvent(window.getWindowTextArea().getText()));
    }

    public void onSaveAs()
    {
        fireSaveAsEvent(new SaveEvent(window.getWindowTextArea().getText()));
    }

    public void onOpenFile()
    {
        if (window.getWindowTextArea().getTextChanged())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, OPEN_CONFIRM_MESSAGE, ButtonType.YES, ButtonType.NO,
                                    ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
            {
                Controller.openFileOnSaveCompleted = true;
                onSave();
            }
            else if (alert.getResult() == ButtonType.NO)
            {
                fireLoadFileRequest();
            }
        }

        else
        {
            fireLoadFileRequest();
        }
    }

    public void onPrint()
    {
    }

    public void onQuit()
    {
        if (window.getWindowTextArea().getTextChanged())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, QUIT_CONFIRM_MESSAGE, ButtonType.YES, ButtonType.NO,
                                    ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
            {
                Controller.quitOnSaveCompleted = true;
                onSave();
            }

            else if (alert.getResult() == ButtonType.NO)
            {
                System.exit(0);
            }
        }

        else
        {
            System.exit(0);
        }
    }

    @Override
    public void onSaveComplete(SaveCompleteEvent event)
    {
        System.out.println("Save successful!");

        if (Controller.newOnSaveCompleted)
        {
            window.getWindowTextArea().setText("");
            Controller.newOnSaveCompleted = false;
        }

        if (Controller.quitOnSaveCompleted)
        {
            System.exit(0);
            Controller.quitOnSaveCompleted = false;
        }

        if (Controller.openFileOnSaveCompleted)
        {
            fireLoadFileRequest();
            Controller.openFileOnSaveCompleted = false;
        }

        window.getWindowTextArea().setTextChanged(false);
    }
}
