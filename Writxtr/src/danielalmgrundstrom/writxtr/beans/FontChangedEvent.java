package danielalmgrundstrom.writxtr.beans;

public class FontChangedEvent{
	private String fontName;
	private int size;
	private boolean italic = false;
	private boolean bold = false;
	
	public FontChangedEvent(String fontName, int size,
							 boolean italic, boolean bold){
		this.fontName = fontName;
		this.size = size;
		this.italic = italic;
		this.bold = bold;
	}
	
	public void setFontName(String fontName){
		this.fontName = fontName;
	}
	
	public String getFontName(){
		return fontName;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setItalic(boolean italic){
		this.italic = italic;
	}
	
	public boolean getItalic(){
		return italic;
	}
	
	public void setBold(boolean bold){
		this.bold = bold;
	}
	
	public boolean getBold(){
		return bold;
	}
}
