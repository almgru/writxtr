package writxtr.controller.ui;

import writxtr.beans.ToolBarEvent;
import writxtr.listeners.ToolBarListener;
import writxtr.ui.Window;

public class ToolBarController implements ToolBarListener{

	Window window;
	WindowController windowController;
	
	public ToolBarController(Window window, WindowController windowController) {
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
