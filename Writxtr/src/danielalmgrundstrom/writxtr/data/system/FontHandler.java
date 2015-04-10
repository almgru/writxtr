package danielalmgrundstrom.writxtr.data.system;

import java.awt.GraphicsEnvironment;

import danielalmgrundstrom.writxtr.beans.FontsRecievedEvent;
import danielalmgrundstrom.writxtr.listeners.FontRequestListener;
import danielalmgrundstrom.writxtr.listeners.FontsRecievedListener;

public class FontHandler implements FontRequestListener {

	private FontsRecievedListener fontsRecievedListener;

	private String[] fontNames;
	
	@Override
	public void onFontRequest() {
		fontNames = getFontNames();
		fireFontsRecievedEvent(new FontsRecievedEvent(fontNames));
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
