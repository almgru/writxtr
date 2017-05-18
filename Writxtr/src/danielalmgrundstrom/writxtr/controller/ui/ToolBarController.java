package danielalmgrundstrom.writxtr.controller.ui;

import danielalmgrundstrom.writxtr.beans.ToolBarEvent;
import danielalmgrundstrom.writxtr.listeners.ToolBarListener;
import danielalmgrundstrom.writxtr.ui.MainView;

public class ToolBarController implements ToolBarListener{

	MainView window;
	WindowController windowController;
	
	public ToolBarController(MainView window, WindowController windowController) {
		this.window = window;
		this.windowController = windowController;
	}
	
	@Override
	public void onButtonClicked(ToolBarEvent event) {
		switch (event.getItem()){
		case New:
			windowController.onNew();
			break;
			
		case Save:
			windowController.onSave();
			break;
			
		case SaveAs:
			windowController.onSaveAs();
			break;
			
		case OpenFile:
			windowController.onOpenFile();
			break;
		}
	}
}
