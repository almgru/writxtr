package writxtr.listeners;

import writxtr.beans.LoadFileEvent;
import writxtr.beans.LoadURLEvent;


public interface LoadCompleteListener {
	void onLoadFileComplete(LoadFileEvent event);
	void onLoadURLComplete(LoadURLEvent event);
}
