package writxtr.controller.ui;

import java.awt.Font;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import writxtr.beans.FontChangedEvent;
import writxtr.listeners.FontChangedListener;
import writxtr.ui.Window;

public class TextAreaController implements CaretListener,
							   	               DocumentListener,
												  FontChangedListener {

	private Window window;
	//private WindowController windowController;
	
	public TextAreaController(Window window, WindowController windowController) {
		this.window = window;
		//this.windowController = windowController;
	}
	
	@Override
	public void caretUpdate(CaretEvent caretEvent) {
		int dot = caretEvent.getDot();
		int mark = caretEvent.getMark();
		
		if (dot == mark)
			window.getWindowMenuBar().setCCPEnabled(false);
		
		else
			window.getWindowMenuBar().setCCPEnabled(true);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		window.getWindowTextArea().setTextChanged(true);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		window.getWindowTextArea().setTextChanged(true);
	}
	
	public void onFontChanged(FontChangedEvent event){
		String fontName = event.getFontName();
		int fontStyle = Font.PLAIN;
		int size = event.getSize();
		
		if(!event.getBold() && !event.getItalic()){
			fontStyle = Font.PLAIN;
		}
		
		else {
			if(event.getBold() && event.getItalic()){
				fontStyle = Font.BOLD + Font.ITALIC;
			}
			
			else if (event.getBold()){
				fontStyle = Font.BOLD;
			}
			
			else if (event.getItalic()){
				fontStyle = Font.ITALIC;
			}
		}
		
		window.getWindowTextArea().setFont(new Font(fontName, fontStyle, size));
	}

}
