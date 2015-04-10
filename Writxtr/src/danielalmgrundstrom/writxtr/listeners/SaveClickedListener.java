package danielalmgrundstrom.writxtr.listeners;

import danielalmgrundstrom.writxtr.beans.SaveEvent;

public interface SaveClickedListener {
	public void onSaveClicked(SaveEvent event);
	public void onSaveAsClicked(SaveEvent event);
}
