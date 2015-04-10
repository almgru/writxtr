package danielalmgrundstrom.writxtr.controller.data;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import danielalmgrundstrom.writxtr.beans.FontsRecievedEvent;
import danielalmgrundstrom.writxtr.beans.IOErrorEvent;
import danielalmgrundstrom.writxtr.beans.SaveEvent;
import danielalmgrundstrom.writxtr.listeners.FontsRecievedListener;
import danielalmgrundstrom.writxtr.listeners.IOErrorListener;
import danielalmgrundstrom.writxtr.listeners.LoadClickedListener;
import danielalmgrundstrom.writxtr.listeners.LoadFileListener;
import danielalmgrundstrom.writxtr.listeners.LoadURLListener;
import danielalmgrundstrom.writxtr.listeners.NewClickedListener;
import danielalmgrundstrom.writxtr.listeners.SaveClickedListener;
import danielalmgrundstrom.writxtr.listeners.SaveListener;
import danielalmgrundstrom.writxtr.ui.FontWindow;
import danielalmgrundstrom.writxtr.ui.Window;

public class IOController implements SaveClickedListener,
										LoadClickedListener,
										NewClickedListener,
										IOErrorListener,
										FontsRecievedListener {

	private SaveListener saveListener;
	private LoadFileListener loadFileListener;
	private LoadURLListener loadURLListener;

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

	@Override
	public void onLoadURLClicked() {
		try {
			String input = JOptionPane.showInputDialog("Enter url to open: ");

			URL url = null;

			if (input != null) {
				url = new URL(input);
				// fileHandler.requestLoad(url);
				fireLoadURLRequest(url);
			}

		} catch (MalformedURLException e) {

		}
	}

	public void setSaveListener(SaveListener saveListener) {
		this.saveListener = saveListener;
	}

	public void setLoadFileListener(LoadFileListener loadFileListener) {
		this.loadFileListener = loadFileListener;
	}

	public void setLoadURLListener(LoadURLListener loadURLListener) {
		this.loadURLListener = loadURLListener;
	}

	public void fireSaveEvent(File file, SaveEvent event) {
		if (saveListener != null)
			saveListener.onSaveRequest(file, event);
	}

	public void fireLoadFileRequest(File file) {
		if (loadFileListener != null)
			loadFileListener.onLoadFileRequest(file);
	}

	public void fireLoadURLRequest(URL url) {
		if (loadURLListener != null)
			loadURLListener.onLoadURLRequest(url);
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
