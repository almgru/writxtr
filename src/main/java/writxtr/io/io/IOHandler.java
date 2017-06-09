package writxtr.io.io;

import writxtr.beans.IOErrorEvent;
import writxtr.beans.LoadFileEvent;
import writxtr.beans.SaveCompleteEvent;
import writxtr.listeners.IOErrorListener;
import writxtr.listeners.LoadCompleteListener;
import writxtr.listeners.SaveCompleteListener;

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
