package danielalmgrundstrom.writxtr.listeners;

import danielalmgrundstrom.writxtr.beans.IOErrorEvent;


public interface IOErrorListener {
	public void onIOError(IOErrorEvent event);
}
