package danielalmgrundstrom.writxtr.data;

import danielalmgrundstrom.writxtr.data.io.IOHandler;
import danielalmgrundstrom.writxtr.data.system.FontHandler;

public class DataHandler {
	
	private FontHandler fontHandler;
	private IOHandler iOHandler;
	
	public DataHandler() {
		fontHandler = new FontHandler();
		iOHandler = new IOHandler();
	}
	
	public FontHandler getFontHandler(){
		return fontHandler;
	}
	
	public IOHandler getIOHandler(){
		return iOHandler;
	}
}
