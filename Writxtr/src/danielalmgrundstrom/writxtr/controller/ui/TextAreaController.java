package danielalmgrundstrom.writxtr.controller.ui;

import danielalmgrundstrom.writxtr.beans.FontChangedEvent;
import danielalmgrundstrom.writxtr.listeners.FontChangedListener;
import danielalmgrundstrom.writxtr.ui.MainView;

public class TextAreaController implements FontChangedListener {

	private MainView window;
	//private WindowController windowController;
	
	public TextAreaController(MainView window, WindowController windowController) {
		this.window = window;
		//this.windowController = windowController;
	}

	public void insertUpdate() {
		window.getWindowTextArea().setTextChanged(true);
	}

	public void removeUpdate() {
		window.getWindowTextArea().setTextChanged(true);
	}
	
	public void onFontChanged(FontChangedEvent event){
	}

}
