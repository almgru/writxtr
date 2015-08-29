package danielalmgrundstrom.writxtr.data.io;

import danielalmgrundstrom.writxtr.beans.IOErrorEvent;
import danielalmgrundstrom.writxtr.beans.LoadFileEvent;
import danielalmgrundstrom.writxtr.beans.LoadURLEvent;
import danielalmgrundstrom.writxtr.beans.SaveCompleteEvent;
import danielalmgrundstrom.writxtr.listeners.IOErrorListener;
import danielalmgrundstrom.writxtr.listeners.LoadCompleteListener;
import danielalmgrundstrom.writxtr.listeners.SaveCompleteListener;

public class IOHandler {
	private FileHandler fileHandler;

	private SaveCompleteListener saveCompleteListener;
	private LoadCompleteListener loadCompleteListener;
	private IOErrorListener iOErrorListener;

	public IOHandler() {
		fileHandler = new FileHandler(this);
	}

	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public void setSaveCompleteListener(
			SaveCompleteListener saveCompleteListener) {
		this.saveCompleteListener = saveCompleteListener;
	}

	public void setLoadCompleteListener(
			LoadCompleteListener loadCompleteListener) {
		this.loadCompleteListener = loadCompleteListener;
	}
	
	public void setIOErrorListener(IOErrorListener listener) {
		iOErrorListener = listener;
	}

	public void fireSaveCompleteEvent(SaveCompleteEvent event) {
		if (saveCompleteListener != null)
			saveCompleteListener.onSaveComplete(event);
	}

	public void fireLoadCompleteEvent(LoadFileEvent event) {
		if (loadCompleteListener != null)
			loadCompleteListener.onLoadFileComplete(event);
	}

	public void fireIOErrorEvent(IOErrorEvent event) {
		if (iOErrorListener != null)
			iOErrorListener.onIOError(event);
	}
}
