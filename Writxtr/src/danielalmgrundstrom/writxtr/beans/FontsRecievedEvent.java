package danielalmgrundstrom.writxtr.beans;

public class FontsRecievedEvent {

	private String[] fontNames;
	
	public FontsRecievedEvent(String[] fontNames){
		this.fontNames = fontNames;
	}
	
	public String[] getFontNames(){
		return fontNames;
	}
}
