package danielalmgrundstrom.writxtr.listeners;

import java.io.File;

import danielalmgrundstrom.writxtr.beans.SaveEvent;

public interface SaveListener {
	public void onSaveRequest(File file, SaveEvent event);
}
