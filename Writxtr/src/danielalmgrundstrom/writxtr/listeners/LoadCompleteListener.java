package danielalmgrundstrom.writxtr.listeners;

import danielalmgrundstrom.writxtr.beans.LoadFileEvent;
import danielalmgrundstrom.writxtr.beans.LoadURLEvent;




public interface LoadCompleteListener {
	void onLoadFileComplete(LoadFileEvent event);
	void onLoadURLComplete(LoadURLEvent event);
}
