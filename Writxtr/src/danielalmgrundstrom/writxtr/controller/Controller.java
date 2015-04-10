package danielalmgrundstrom.writxtr.controller;

import danielalmgrundstrom.writxtr.controller.data.IOController;
import danielalmgrundstrom.writxtr.controller.ui.WindowController;
import danielalmgrundstrom.writxtr.data.DataHandler;
import danielalmgrundstrom.writxtr.ui.Window;

public class Controller{
	
	private DataHandler dataHandler;
	private Window window;
	
	private IOController iOController;
	private WindowController windowController;
	
	public static boolean newOnSaveCompleted = false;
	public static boolean quitOnSaveCompleted = false;
	public static boolean openFileOnSaveCompleted = false;
	public static boolean openURLOnSaveCompleted = false;
	
	public Controller(DataHandler dataHandler, Window window){	
		this.dataHandler = dataHandler;
		this.window = window;
		
		iOController = new IOController(window);
		dataHandler.getFontHandler().setFontsRecievedListener(iOController);
		windowController = new WindowController(window, dataHandler.getFontHandler());
	}
	
	public void init(){	
		windowController.init();
		iOController.setSaveListener(dataHandler.getIOHandler().getFileHandler());
		iOController.setLoadFileListener(dataHandler.getIOHandler().getFileHandler());
		iOController.setLoadURLListener(dataHandler.getIOHandler().getURLHandler());

		dataHandler.getIOHandler().setLoadCompleteListener(windowController);
		dataHandler.getIOHandler().setSaveCompleteListener(windowController);
		dataHandler.getIOHandler().setIOErrorListener(iOController);
		
		window.getWindowToolBar().setToolBarListener(windowController.getToolBarController());
		window.getWindowMenuBar().setMenuListener(windowController.getMenuController());
		
		windowController.setNewClickedListener(iOController);
		windowController.setSaveClickedListener(iOController);
		windowController.setLoadClickedListener(iOController);
		window.setFontChangedListener(windowController.getTextAreaController());
	}
}