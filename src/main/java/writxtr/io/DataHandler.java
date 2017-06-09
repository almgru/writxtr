package writxtr.io;

import writxtr.io.io.IOHandler;
import writxtr.io.system.FontHandler;

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
