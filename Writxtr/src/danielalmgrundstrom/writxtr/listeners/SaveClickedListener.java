package danielalmgrundstrom.writxtr.listeners;

import danielalmgrundstrom.writxtr.beans.SaveEvent;

public interface SaveClickedListener {
	void onSaveClicked(SaveEvent event);
	void onSaveAsClicked(SaveEvent event);
}
