package danielalmgrundstrom.writxtr.controller.ui;

import danielalmgrundstrom.writxtr.beans.MenuEvent;
import danielalmgrundstrom.writxtr.listeners.MenuListener;
import danielalmgrundstrom.writxtr.ui.MainView;

public class MenuController implements MenuListener
{

    private MainView window;
    private WindowController windowController;

    public MenuController(MainView window, WindowController windowController)
    {
        this.window = window;
        this.windowController = windowController;
    }

    @Override
    public void onMenuButtonClicked(MenuEvent event)
    {
        switch (event.getItem())
        {
            case New:
                windowController.onNew();
                break;

            case Save:
                windowController.onSave();
                break;

            case SaveAs:
                windowController.onSaveAs();
                break;

            case OpenFile:
                windowController.onOpenFile();
                break;

            case Print:
                windowController.onPrint();
                break;

            case Quit:
                windowController.onQuit();
                break;

            case Delete:
                onDelete();
                break;

            case SelectAll:
                onSelectAll();
                break;

            case About:
                onAbout();
                break;

            case Font:
                onFont();
                break;

            default:
                break;
        }
    }

    private void onDelete()
    {
        window.getWindowTextArea().deleteText();
    }

    private void onSelectAll()
    {
        window.getWindowTextArea().selectAll();
    }

    private void onFont()
    {
        window.showFontWindow();
    }

    private void onAbout()
    {
        System.out.println("Writxtr\n\nMade by Daniel Alm Grundström");
    }
}
