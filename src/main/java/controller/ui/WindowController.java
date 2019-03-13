package writxtr.controller.ui;

import java.awt.print.PrinterException;

import javax.swing.JOptionPane;

import writxtr.beans.LoadFileEvent;
import writxtr.beans.LoadURLEvent;
import writxtr.beans.SaveCompleteEvent;
import writxtr.beans.SaveEvent;
import writxtr.controller.Controller;
import writxtr.data.system.FontHandler;
import writxtr.listeners.FontRequestListener;
import writxtr.listeners.LoadClickedListener;
import writxtr.listeners.LoadCompleteListener;
import writxtr.listeners.NewClickedListener;
import writxtr.listeners.SaveClickedListener;
import writxtr.listeners.SaveCompleteListener;
import writxtr.ui.Window;

public class WindowController implements SaveCompleteListener,
		LoadCompleteListener {
	private Window window;

	public static final String NEW_CONFIRM_MESSAGE = "Document has been changed since last save.\nDo you want to save before creating a new document?";

	public static final String QUIT_CONFIRM_MESSAGE = "Document has been changed since last save.\nDo you want to save before quitting.";

	public static final String OPEN_CONFIRM_MESSAGE = "Document has been changed since last save.\nDo you want to save before loading a new file.";

	private MenuController menuController;
	private TextAreaController textAreaController;
	private ToolBarController toolBarController;

	private FontRequestListener fontRequestListener;

	private NewClickedListener newClickedListener;
	private SaveClickedListener saveRequestListener;
	private LoadClickedListener loadRequestListener;

	public WindowController(Window window, FontHandler fontHandler) {
		this.window = window;
		setFontRequestListener(fontHandler);
		fireFontRequest();
	}

	public void init() {
		menuController = new MenuController(window, this);
		textAreaController = new TextAreaController(window, this);
		toolBarController = new ToolBarController(window, this);

		window.getWindowTextArea().setDocumenetListener(textAreaController);
		window.getWindowTextArea().setCaretListener(textAreaController);
	}

	public MenuController getMenuController() {
		return menuController;
	}

	public TextAreaController getTextAreaController() {
		return textAreaController;
	}

	public ToolBarController getToolBarController() {
		return toolBarController;
	}

	public void setNewClickedListener(NewClickedListener newClickedListener) {
		this.newClickedListener = newClickedListener;
	}

	public void setSaveClickedListener(SaveClickedListener saveListener) {
		this.saveRequestListener = saveListener;
	}

	public void setLoadClickedListener(LoadClickedListener loadListener) {
		this.loadRequestListener = loadListener;
	}

	public void setFontRequestListener(FontRequestListener fontRequestListener) {
		this.fontRequestListener = fontRequestListener;
		fireFontRequest();
	}

	public void fireNewClickedEvent() {
		if (newClickedListener != null)
			newClickedListener.onNewClicked();
	}

	public void fireSaveEvent(SaveEvent event) {
		if (saveRequestListener != null) {
			saveRequestListener.onSaveClicked(event);
		}
	}

	public void fireSaveAsEvent(SaveEvent event) {
		if (saveRequestListener != null) {
			saveRequestListener.onSaveAsClicked(event);
		}
	}

	public void fireLoadFileRequest() {
		if (loadRequestListener != null)
			loadRequestListener.onLoadFileClicked();
	}

	public void fireFontRequest() {
		if (fontRequestListener != null)
			fontRequestListener.onFontRequest();
	}

	@Override
	public void onLoadFileComplete(LoadFileEvent event) {
		window.getWindowTextArea().setText(event.getFileContent());
		window.getWindowTextArea().setTextChanged(false);
		window.changeTitle(event.getName());
	}

	@Override
	public void onLoadURLComplete(LoadURLEvent event) {
		window.getWindowTextArea().setText(event.getURLContent());
		window.getWindowTextArea().setTextChanged(true);
		window.changeTitle(event.getURL());
	}

	public void onNew() {
		if (window.getWindowTextArea().getTextChanged()) {
			int returnValue = JOptionPane.showConfirmDialog(null,
					NEW_CONFIRM_MESSAGE);

			if (returnValue == 0) {
				Controller.newOnSaveCompleted = true;
				onSave();
			}

			else if (returnValue == 1) {
				window.getWindowTextArea().setText("");
				window.getWindowTextArea().setTextChanged(false);
				window.changeTitle("new textfile");
				fireNewClickedEvent();
			}
		}

		else {
			window.getWindowTextArea().setText("");
			window.getWindowTextArea().setTextChanged(false);
			window.changeTitle("new textfile");
			fireNewClickedEvent();
		}
	}

	public void onSave() {
		fireSaveEvent(new SaveEvent(window.getWindowTextArea().getText()));
	}

	public void onSaveAs() {
		fireSaveAsEvent(new SaveEvent(window.getWindowTextArea().getText()));
	}

	public void onOpenFile() {
		if (window.getWindowTextArea().getTextChanged()) {
			int returnValue = JOptionPane.showConfirmDialog(null,
					OPEN_CONFIRM_MESSAGE);

			if (returnValue == 0) {
				Controller.openFileOnSaveCompleted = true;
				onSave();
			}

			else if (returnValue == 1) {
				fireLoadFileRequest();
			}
		}

		else
			fireLoadFileRequest();
	}

	public void onPrint() {
		try {
			window.getWindowTextArea().print();
		} catch (PrinterException e) {
			System.out.printf(
					"Could not print text. Error message: %s",
					e.getMessage());
		}
	}

	public void onQuit() {
		if (window.getWindowTextArea().getTextChanged()) {
			int returnValue = JOptionPane.showConfirmDialog(null,
					QUIT_CONFIRM_MESSAGE);

			if (returnValue == 0) {
				Controller.quitOnSaveCompleted = true;
				onSave();
			}

			else if (returnValue == 1) {
				System.exit(0);
			}
		}

		else
			System.exit(0);
	}

	@Override
	public void onSaveComplete(SaveCompleteEvent event) {
		window.showMessage("Save successful!");

		window.changeTitle(event.getName());

		if (Controller.newOnSaveCompleted) {
			window.getWindowTextArea().setText("");
			Controller.newOnSaveCompleted = false;
		}

		if (Controller.quitOnSaveCompleted) {
			System.exit(0);
			Controller.quitOnSaveCompleted = false;
		}

		if (Controller.openFileOnSaveCompleted) {
			fireLoadFileRequest();
			Controller.openFileOnSaveCompleted = false;
		}

		window.getWindowTextArea().setTextChanged(false);
	}
}
