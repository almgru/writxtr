package writxtr.ui;

import writxtr.beans.MenuEvent;
import writxtr.enums.MenuAction;
import writxtr.listeners.FontChangedListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

import javax.swing.*;

/**
 * Root pane of the application. Contains the following components:
 * <ul>
 *     <li>Menu bar</li>
 *     <li>Tool bar</li>
 *     <li>Text area</li>
 * </ul>
 */
public class MainView extends BorderPane
{
    private static final long serialVersionUID = -7559719994412678413L;

    private FontChangedListener fontChangedListener;

    // Menu bar
    private WritxtrMenuBar menuBar;

    // Tool bar
    private WritxtrToolBar toolBar;

    // Text Area
    private WritxtrTextArea textArea;
    private ScrollPane scrollPane;

    // Font window
    private FontWindow fontWindow;

    public void setFontChangedListener(FontChangedListener fontChangedListener)
    {
        this.fontChangedListener = fontChangedListener;
    }

    public void init()
    {
        menuBar = new WritxtrMenuBar();
        menuBar.init();

        toolBar = new WritxtrToolBar();
        toolBar.init();

        scrollPane = new ScrollPane();

        textArea = new WritxtrTextArea();
        textArea.init();

        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(menuBar, toolBar);
        setTop(topContainer);

        setCenter(textArea);

        setMinSize(240, 120);
        setPrefSize(800, 600);

        setVisible(true);
    }

    public WritxtrMenuBar getWindowMenuBar()
    {
        return menuBar;
    }

    public WritxtrTextArea getWindowTextArea()
    {
        return textArea;
    }

    public WritxtrToolBar getWindowToolBar()
    {
        return toolBar;
    }

    public FontWindow getFontWindow()
    {
        return fontWindow;
    }

    // TODO: Move this to Controller
    public void showFontWindow()
    {
        fontWindow = new FontWindow(fontChangedListener);
        fontWindow.init(true, this);
    }

    /**
     * Called just after the main window has been shown
     */
    public void onShown(WindowEvent event)
    {
        textArea.requestFocus();
    }

    /**
     * Called when the main window has received a request to close
     */
    public void onClose(WindowEvent event)
    {
        menuBar.fireMenuEvent(new MenuEvent(MenuAction.Quit));
    }
}
