package danielalmgrundstrom.writxtr.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import danielalmgrundstrom.writxtr.beans.MenuEvent;
import danielalmgrundstrom.writxtr.enums.MenuItem;
import danielalmgrundstrom.writxtr.listeners.FontChangedListener;

public class Window extends JFrame {

	private static final long serialVersionUID = -7559719994412678413L;

	private FontChangedListener fontChangedListener;
	
	// Menu bar
	private WritxtrMenuBar menuBar;

	// Tool bar
	private WritxtrToolBar toolBar;

	// Text Area
	private WritxtrTextArea textArea;
	private JScrollPane scrollPane;
	
	// Font window
	private FontWindow fontWindow;
	

	public Window() {
	}
	
	public void setFontChangedListener(FontChangedListener fontChangedListener){
		this.fontChangedListener = fontChangedListener;
	}

	public void init() {
		changeTitle("new text file");
		
		menuBar = new WritxtrMenuBar();
		menuBar.init();
		
		toolBar = new WritxtrToolBar();
		toolBar.init();

		textArea = new WritxtrTextArea();
		textArea.init();
		scrollPane = new JScrollPane(textArea);

		add(toolBar, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		setJMenuBar(menuBar);
		
		setMinimumSize(new Dimension(240, 120));
		setSize(800, 600);
		centerWindow();
		initWindowEvents();
		
		setVisible(true);
	}

	public WritxtrMenuBar getWindowMenuBar() {
		return menuBar;
	}

	public WritxtrTextArea getWindowTextArea() {
		return textArea;
	}

	public WritxtrToolBar getWindowToolBar() {
		return toolBar;
	}
	
	public FontWindow getFontWindow(){
		return fontWindow;
	}

	public void showMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
	
	public void showFontWindow(){
		fontWindow = new FontWindow(fontChangedListener);
		fontWindow.init(true, this);
	}
	
	public void changeTitle(String fileName){
		setTitle(String.format("Writxtr - %s", fileName));
	}
	
	private void centerWindow() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	}
	
	private void initWindowEvents() {
		setDefaultCloseOperation(Window.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){	
			@Override
			public void windowOpened(WindowEvent event) {
				textArea.requestFocus();
			}
			
			@Override
			public void windowClosing(WindowEvent event){
				menuBar.fireMenuEvent(new MenuEvent(MenuItem.Quit));
			}
		});
	}
}
