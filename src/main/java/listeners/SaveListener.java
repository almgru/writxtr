package writxtr.listeners;

import java.io.File;

import writxtr.beans.SaveEvent;

public interface SaveListener {
	void onSaveRequest(File file, SaveEvent event);
}
