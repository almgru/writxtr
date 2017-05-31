package writxtr.ui;

import writxtr.beans.MenuEvent;
import writxtr.enums.MenuAction;
import writxtr.listeners.MenuListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class WritxtrMenuBar extends MenuBar implements EventHandler<ActionEvent>
{

    private static final long serialVersionUID = -839100811136182450L;

    // Listeners
    private MenuListener menuListener;

    // File Menu
    private Menu fileMenu;
    private MenuItem newItem;
    private MenuItem saveItem;
    private MenuItem saveAsItem;
    private MenuItem openItem;
    private MenuItem printItem;
    private MenuItem quitItem;

    // Edit Menu
    private Menu editMenu;
    private MenuItem copyItem;
    private MenuItem cutItem;
    private MenuItem pasteItem;
    private MenuItem deleteItem;
    private MenuItem selectAllItem;

    // View Menu
    private Menu viewMenu;
    private MenuItem fontItem;

    // Help Menu
    private Menu helpMenu;
    private MenuItem aboutItem;

    public WritxtrMenuBar()
    {
    }

    public void init()
    {
        newItem = new MenuItem("New");
        openItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");
        saveAsItem = new MenuItem("Save as...");
        printItem = new MenuItem("Print");
        quitItem = new MenuItem("Exit");

        newItem.addEventHandler(ActionEvent.ACTION, this);
        newItem.addEventHandler(ActionEvent.ACTION, this);
        saveItem.addEventHandler(ActionEvent.ACTION, this);
        saveAsItem.addEventHandler(ActionEvent.ACTION, this);
        openItem.addEventHandler(ActionEvent.ACTION, this);
        printItem.addEventHandler(ActionEvent.ACTION, this);
        quitItem.addEventHandler(ActionEvent.ACTION, this);

        fileMenu = new Menu("File");
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(saveAsItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(printItem);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(quitItem);

        copyItem = new MenuItem("Copy");
        cutItem = new MenuItem("Cut");
        pasteItem = new MenuItem("Paste");
        deleteItem = new MenuItem("Delete");
        selectAllItem = new MenuItem("Select all");

        copyItem.addEventHandler(ActionEvent.ACTION, this);
        cutItem.addEventHandler(ActionEvent.ACTION, this);
        pasteItem.addEventHandler(ActionEvent.ACTION, this);
        deleteItem.addEventHandler(ActionEvent.ACTION, this);
        selectAllItem.addEventHandler(ActionEvent.ACTION, this);

        editMenu = new Menu("Edit");
        editMenu.getItems().add(copyItem);
        editMenu.getItems().add(cutItem);
        editMenu.getItems().add(pasteItem);
        editMenu.getItems().add(deleteItem);
        editMenu.getItems().add(new SeparatorMenuItem());
        editMenu.getItems().add(selectAllItem);

        aboutItem = new MenuItem("About");
        aboutItem.addEventHandler(ActionEvent.ACTION, this);

        fontItem = new MenuItem("Change font");
        fontItem.addEventHandler(ActionEvent.ACTION, this);

        viewMenu = new Menu("View");
        viewMenu.getItems().add(fontItem);

        helpMenu = new Menu("Help");
        helpMenu.getItems().add(aboutItem);

        getMenus().add(fileMenu);
        getMenus().add(editMenu);
        getMenus().add(viewMenu);
        getMenus().add(helpMenu);
    }

    public void setMenuListener(MenuListener menuListener)
    {
        this.menuListener = menuListener;
    }

    public void fireMenuEvent(MenuEvent event)
    {
        if (menuListener != null)
        {
            menuListener.onMenuButtonClicked(event);
        }
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (event.getSource() instanceof MenuItem)
        {
            MenuEvent eventToFire = null;

            // File Menu
            if (event.getSource() == newItem)
            {
                eventToFire = new MenuEvent(MenuAction.New);
            }
            else if (event.getSource() == saveItem)
            {
                eventToFire = new MenuEvent(MenuAction.Save);
            }
            else if (event.getSource() == saveAsItem)
            {
                eventToFire = new MenuEvent(MenuAction.SaveAs);
            }
            else if (event.getSource() == openItem)
            {
                eventToFire = new MenuEvent(MenuAction.OpenFile);
            }
            else if (event.getSource() == printItem)
            {
                eventToFire = new MenuEvent(MenuAction.Print);
            }
            else if (event.getSource() == quitItem)
            {
                eventToFire = new MenuEvent(MenuAction.Quit);
            }

            // Edit Menu
            else if (event.getSource() == deleteItem)
            {
                eventToFire = new MenuEvent(MenuAction.Delete);
            }
            else if (event.getSource() == selectAllItem)
            {
                eventToFire = new MenuEvent(MenuAction.SelectAll);
            }

            // View Menu
            else if (event.getSource() == fontItem)
            {
                eventToFire = new MenuEvent(MenuAction.Font);
            }

            // About Menu
            else if (event.getSource() == aboutItem)
            {
                eventToFire = new MenuEvent(MenuAction.About);
            }

            if (eventToFire != null)
            {
                fireMenuEvent(eventToFire);
            }
        }
    }
}
