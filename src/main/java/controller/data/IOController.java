package writxtr.controller.data;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import writxtr.beans.FontsRecievedEvent;
import writxtr.beans.IOErrorEvent;
import writxtr.beans.SaveEvent;
import writxtr.listeners.FontsRecievedListener;
import writxtr.listeners.IOErrorListener;
import writxtr.listeners.LoadClickedListener;
import writxtr.listeners.LoadFileListener;
import writxtr.listeners.NewClickedListener;
import writxtr.listeners.SaveClickedListener;
import writxtr.listeners.SaveListener;
import writxtr.ui.FontWindow;
import writxtr.ui.Window;

public class IOController implements SaveClickedListener,
                                     LoadClickedListener,
                                     NewClickedListener,
                                     IOErrorListener,
                                     FontsRecievedListener {

	private SaveListener saveListener;
	private LoadFileListener loadFileListener;

	private JFileChooser fileChooser;
	private FileNameExtensionFilter txtFilter;
	private File currentFile;

	private Window window;

	public IOController(Window window) {
		this.window = window;
		fileChooser = new JFileChooser();

		txtFilter = new FileNameExtensionFilter("Text files ( .txt .text )",
				"txt", "text");
		fileChooser.setFileFilter(txtFilter);
	}

	@Override
	public void onSaveClicked(SaveEvent event) {
		if (currentFile != null)
			fireSaveEvent(currentFile, event);
		else
			onSaveAsClicked(event);
	}

	@Override
	public void onSaveAsClicked(SaveEvent event) {
		int returnValue = fileChooser.showSaveDialog(window);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (fileChooser.getFileFilter() == txtFilter) {
				if (!file.getName().toLowerCase().endsWith(".txt")) {
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
	public void onLoadFileClicked() {
		int returnValue = fileChooser.showOpenDialog(window);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			currentFile = file;
			fireLoadFileRequest(file);
		}

	}

	public void setSaveListener(SaveListener saveListener) {
		this.saveListener = saveListener;
	}

	public void setLoadFileListener(LoadFileListener loadFileListener) {
		this.loadFileListener = loadFileListener;
	}

	public void fireSaveEvent(File file, SaveEvent event) {
		if (saveListener != null)
			saveListener.onSaveRequest(file, event);
	}

	public void fireLoadFileRequest(File file) {
		if (loadFileListener != null)
			loadFileListener.onLoadFileRequest(file);
	}

	@Override
	public void onIOError(IOErrorEvent event) {
		window.showMessage(event.getErrorMessage());
	}

	@Override
	public void onFontsRecieved(FontsRecievedEvent event) {
		FontWindow.setFontNames(event.getFontNames());
	}

	@Override
	public void onNewClicked() {
		currentFile = null;
	}
}
