package writxtr.beans;

public class FontSelectEvent{
	private String fontName;
	private int size;
    private int style;
	
	public FontSelectEvent(String fontName, int size, int style){
		this.fontName = fontName;
		this.size = size;
        this.style = style;
	}
	
	public String getFontName(){
		return fontName;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getStyle(){
		return style;
	}
}

