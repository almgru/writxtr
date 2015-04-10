package danielalmgrundstrom.writxtr.controller.ui;

import javax.swing.JOptionPane;

import danielalmgrundstrom.writxtr.beans.MenuEvent;
import danielalmgrundstrom.writxtr.controller.Controller;
import danielalmgrundstrom.writxtr.listeners.MenuListener;
import danielalmgrundstrom.writxtr.ui.Window;

public class MenuController implements MenuListener{

	private Window window;
	private WindowController windowController;
	
	public MenuController(Window window, WindowController windowController) {
		this.window = window;
		this.windowController = windowController;
	}

	@Override
	public void onMenuButtonClicked(MenuEvent event) {
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
			
		case OpenURL:
			onOpenURL();
			break;
			
		case Print:
			windowController.onPrint();
			break;
			
		case Quit:
			windowController.onQuit();
			break;
			
		case Delete:
			onDelete();
			break;
			
		case SelectAll:
			onSelectAll();
			break;
			
		case About:
			onAbout();
			break;
			
		case Font:
			onFont();
			break;
			
		default:
			break;
		}
	}
	
	private void onOpenURL(){
		if(window.getWindowTextArea().getTextChanged()){
			int returnValue = JOptionPane.showConfirmDialog
					(null, WindowController.OPEN_CONFIRM_MESSAGE);
			
			if(returnValue == 0){
				Controller.openFileOnSaveCompleted = true;
				windowController.onSave();
			}
			
			else if (returnValue == 1){
				windowController.fireLoadURLRequest();
			}
		}
		
		else
			windowController.fireLoadURLRequest();
	}
	
	private void onDelete(){
		window.getWindowTextArea().deleteText();
	}
	
	private void onSelectAll(){
		window.getWindowTextArea().selectAll();
	}
	
	private void onFont(){
		window.showFontWindow();
	}
	
	private void onAbout(){
		window.showMessage(String.format
				("Writxtr\n\nMade by Daniel Alm Grundström"));
	}
}
