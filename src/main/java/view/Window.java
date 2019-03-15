package writxtr.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import writxtr.beans.MenuEvent;
import writxtr.enums.MenuItem;
import writxtr.listeners.FontChangedListener;

public class Window extends JFrame implements View {

	private static final long serialVersionUID = -7559719994412678413L;

	private FontSelectedObserver fontSelectedObserver;
	
	// Menu bar
	private MenuView menu;

	// Tool bar
	private ToolbarView toolbar;

	// Text Area
	private EditorView editor;
	private JScrollPane scrollPane;
	
	// Font window
	private FontSelectorView fontSelector;
	

	public Window() {
	}
	
    @Override
	void setFontSelectedObserver(FontSelectedObserver observer){
		this.fontSelectedObserver = observer;
	}

    @Override
	void init() {
		changeTitle("new text file");
		
		menu = new WritxtrMenuBar();
		menu.init();
		
		toolbar = new WritxtrToolBar();
		toolbar.init();

		editor = new WritxtrTextArea();
		editor.init();
		scrollPane = new JScrollPane(textArea);

		add(toolbar, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		setJMenuBar(menu);
		
		setMinimumSize(new Dimension(240, 120));
		setSize(800, 600);
		centerWindow();
		initWindowEvents();
		
		setVisible(true);
	}

    @Override
	MenuView getMenu() {
		return menu;
	}

    @Override
	EditorView getEditor() {
		return editor;
	}

    @Override
	ToolbarView getToolbar() {
		return toolbar;
	}
	
    @Override
	FontSelectorView getFontSelector(){
		return fontSelector;
	}

    @Override
	void showMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
	
    @Override
	void openFontSelector(){
		fontSelector = new FontWindow(fontChangedListener);
		fontSelector.init(true, this);
	}
	
    @Override
	void changeTitle(String fileName){
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
				editor.requestFocus();
			}
			
			@Override
			public void windowClosing(WindowEvent event){
				menu.fireMenuEvent(new MenuEvent(MenuItem.Quit));
			}
		});
	}
}
