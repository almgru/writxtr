package writxtr.io.system;

import writxtr.beans.FontsRecievedEvent;
import writxtr.listeners.FontRequestListener;
import writxtr.listeners.FontsRecievedListener;

import java.awt.*;

public class FontHandler implements FontRequestListener {

	private FontsRecievedListener fontsRecievedListener;

	@Override
	public void onFontRequest() {
		fireFontsRecievedEvent(new FontsRecievedEvent(getFontNames()));
	}
	
	private String[] getFontNames(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		return e.getAvailableFontFamilyNames();
	}
	
	public void setFontsRecievedListener(FontsRecievedListener fontsRecievedListener){
		this.fontsRecievedListener = fontsRecievedListener;
	}
	
	public void fireFontsRecievedEvent(FontsRecievedEvent event){
		if(fontsRecievedListener != null)
			fontsRecievedListener.onFontsRecieved(event);
	}

	
}
