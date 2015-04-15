package danielalmgrundstrom.writxtr.listeners;

import danielalmgrundstrom.writxtr.beans.IOErrorEvent;


public interface IOErrorListener {
	void onIOError(IOErrorEvent event);
}
