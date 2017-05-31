package writxtr.listeners;

import writxtr.beans.SaveEvent;

public interface SaveClickedListener {
	void onSaveClicked(SaveEvent event);
	void onSaveAsClicked(SaveEvent event);
}
