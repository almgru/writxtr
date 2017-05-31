package writxtr.listeners;

import writxtr.beans.SaveEvent;

import java.io.File;

public interface SaveListener {
	void onSaveRequest(File file, SaveEvent event);
}
