package danielalmgrundstrom.writxtr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.text.DefaultEditorKit;

import danielalmgrundstrom.writxtr.beans.MenuEvent;
import danielalmgrundstrom.writxtr.enums.MenuItem;
import danielalmgrundstrom.writxtr.listeners.MenuListener;

public class WritxtrMenuBar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = -839100811136182450L;

	// Listeners
	private MenuListener menuListener;

	// File Menu
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem openFileItem;
	private JMenuItem openURLItem;
	private JMenuItem printItem;
	private JMenuItem quitItem;

	// Edit Menu
	private JMenu editMenu;
	private JMenuItem copyItem;
	private JMenuItem cutItem;
	private JMenuItem pasteItem;
	private JMenuItem deleteItem;
	private JMenuItem selectAllItem;

	// View Menu
	private JMenu viewMenu;
	private JMenuItem fontItem;

	// Help Menu
	private JMenu helpMenu;
	private JMenuItem aboutItem;

	public WritxtrMenuBar() {
	}

	public void init() {
		newItem = new JMenuItem("New");
		openFileItem = new JMenuItem("Open file");
		openURLItem = new JMenuItem("Open URL");
		saveItem = new JMenuItem("Save");
		saveAsItem = new JMenuItem("Save as...");
		printItem = new JMenuItem("Print");
		quitItem = new JMenuItem("Exit");

		newItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		openFileItem.addActionListener(this);
		openURLItem.addActionListener(this);
		printItem.addActionListener(this);
		quitItem.addActionListener(this);

		fileMenu = new JMenu("File");
		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(openFileItem);
		fileMenu.add(openURLItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(printItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(quitItem);

		copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		cutItem = new JMenuItem(new DefaultEditorKit.CutAction());
		pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		deleteItem = new JMenuItem("Delete");
		selectAllItem = new JMenuItem("Select all");

		copyItem.setEnabled(false);
		cutItem.setEnabled(false);
		deleteItem.setEnabled(false);

		copyItem.setText("Copy");
		cutItem.setText("Cut");
		pasteItem.setText("Paste");

		copyItem.addActionListener(this);
		cutItem.addActionListener(this);
		pasteItem.addActionListener(this);
		deleteItem.addActionListener(this);
		selectAllItem.addActionListener(this);

		editMenu = new JMenu("Edit");
		editMenu.add(copyItem);
		editMenu.add(cutItem);
		editMenu.add(pasteItem);
		editMenu.add(deleteItem);
		editMenu.add(new JSeparator());
		editMenu.add(selectAllItem);

		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);

		fontItem = new JMenuItem("Change font");
		fontItem.addActionListener(this);
		
		viewMenu = new JMenu("View");
		viewMenu.add(fontItem);
		
		helpMenu = new JMenu("Help");
		helpMenu.add(aboutItem);

		add(fileMenu);
		add(editMenu);
		add(viewMenu);
		add(helpMenu);
	}

	public void setMenuListener(MenuListener menuListener) {
		this.menuListener = menuListener;
	}

	public void fireMenuEvent(MenuEvent event) {
		if (menuListener != null)
			menuListener.onMenuButtonClicked(event);
	}

	public void setCCPEnabled(boolean b) {
		copyItem.setEnabled(b);
		cutItem.setEnabled(b);
		deleteItem.setEnabled(b);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() instanceof JMenuItem) {

			// File Menu
			if (event.getSource() == newItem)
				fireMenuEvent(new MenuEvent(MenuItem.New));

			else if (event.getSource() == saveItem)
				fireMenuEvent(new MenuEvent(MenuItem.Save));

			else if (event.getSource() == saveAsItem)
				fireMenuEvent(new MenuEvent(MenuItem.SaveAs));

			else if (event.getSource() == openFileItem)
				fireMenuEvent(new MenuEvent(MenuItem.OpenFile));

			else if (event.getSource() == openURLItem)
				fireMenuEvent(new MenuEvent(MenuItem.OpenURL));
			
			else if (event.getSource() == printItem) {
				fireMenuEvent(new MenuEvent(MenuItem.Print));
			}

			else if (event.getSource() == quitItem)
				fireMenuEvent(new MenuEvent(MenuItem.Quit));

			// Edit Menu
			else if (event.getSource() == deleteItem)
				fireMenuEvent(new MenuEvent(MenuItem.Delete));

			else if (event.getSource() == selectAllItem)
				fireMenuEvent(new MenuEvent(MenuItem.SelectAll));

			// View Menu
			else if (event.getSource() == fontItem){
				fireMenuEvent(new MenuEvent(MenuItem.Font));
			}
			
			// About Menu
			else if (event.getSource() == aboutItem)
				fireMenuEvent(new MenuEvent(MenuItem.About));
			
		}
	}
}
