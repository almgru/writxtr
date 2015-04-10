package danielalmgrundstrom.writxtr.listeners;

import danielalmgrundstrom.writxtr.beans.LoadFileEvent;
import danielalmgrundstrom.writxtr.beans.LoadURLEvent;




public interface LoadCompleteListener {
	public void onLoadFileComplete(LoadFileEvent event);
	public void onLoadURLComplete(LoadURLEvent event);
}
