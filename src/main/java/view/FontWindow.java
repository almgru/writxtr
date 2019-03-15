package writxtr.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import writxtr.beans.FontChangedEvent;
import writxtr.listeners.FontChangedListener;

public class FontWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3407769020921041025L;

	private FontChangedListener fontChangedListener;
	
	// Top panel
	private JPanel topPanel;
	private JLabel fontListLabel;
	private JLabel sizeListLabel;
	
	// Center panel
	private JPanel centerPanel;
	private JScrollPane fontScrollPane;
	
	private static DefaultListModel<String> fontNames  = new DefaultListModel<String>();
	private JList<String> fontList;
	
	private JScrollPane sizeScrollPane;
	
	private DefaultListModel<Integer> fontSizes;
	private JList<Integer> sizeList;
	
	// Bottom panel
	private JPanel bottomPanel;
	
	private JToggleButton italicButton;
	private JToggleButton boldButton;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public FontWindow(FontChangedListener fontChangedListener){
		this.fontChangedListener = fontChangedListener;
	}
	
	public void init(boolean b, Window window){
		setTitle("Writxtr - Font selection screen");
		
		fontSizes = new DefaultListModel<Integer>();
		
		fontSizes.addElement(8);
		fontSizes.addElement(10);
		fontSizes.addElement(12);
		fontSizes.addElement(14);
		fontSizes.addElement(16);
		fontSizes.addElement(18);
		fontSizes.addElement(20);
		fontSizes.addElement(24);
		fontSizes.addElement(36);
		fontSizes.addElement(72);
		
		// Top panel
		fontListLabel = new JLabel("Fonts");
		sizeListLabel = new JLabel("Font sizes");
		
		// Center panel
		fontList = new JList<String>(fontNames);
		fontList.setFixedCellHeight(15);
		fontList.setPreferredSize(new Dimension(200, fontNames.getSize() * 15));
		
		sizeList = new JList<Integer>(fontSizes);
		sizeList.setFixedCellHeight(15);
		sizeList.setPreferredSize(new Dimension(50, fontSizes.getSize() * 15));
		
		fontScrollPane = new JScrollPane();
		fontScrollPane.setViewportView(fontList);
		
		sizeScrollPane = new JScrollPane();
		sizeScrollPane.setViewportView(sizeList);
		
		// Top panel
		italicButton = new JToggleButton("I");
		//italicButton.setPreferredSize(new Dimension(36, 24));
		italicButton.setToolTipText("Italic");

		boldButton = new JToggleButton("B");
		//boldButton.setPreferredSize(new Dimension(36, 24));
		boldButton.setToolTipText("Bold");
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		
		// Add panels
		topPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		topPanel.setLayout(new BorderLayout());
		topPanel.add(fontListLabel, BorderLayout.WEST);
		topPanel.add(sizeListLabel, BorderLayout.EAST);
		
		centerPanel.add(fontScrollPane, BorderLayout.WEST);
		centerPanel.add(sizeScrollPane, BorderLayout.EAST);
		
		bottomPanel.add(italicButton);
		bottomPanel.add(boldButton);
		bottomPanel.add(cancelButton);
		bottomPanel.add(confirmButton);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setSize(320,200);
		setLocationRelativeTo(window);
		setVisible(b);
		
		Font font = window.getWindowTextArea().getFont();
		fontList.setSelectedIndex(getCurrentFontIndex(font));
		sizeList.setSelectedIndex(getCurrentSizeIndex(font));
		italicButton.setSelected(getItalic(font));
		boldButton.setSelected(getBold(font));
	}
	
	public static void setFontNames(String[] fontNames) {
		for (String s : fontNames){
			FontWindow.fontNames.addElement(s);
		}
	}
	
	public void fireFontChangedEvent() {
		if(fontChangedListener != null){
			FontChangedEvent fontChangedEvent = new FontChangedEvent(
				fontList.getSelectedValue(),
				sizeList.getSelectedValue(),
				italicButton.isSelected(),
				boldButton.isSelected());

			fontChangedListener.onFontChanged(fontChangedEvent);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton){
			
			if(event.getSource() == confirmButton){
				fireFontChangedEvent();
				setVisible(false);
			}
			
			else if (event.getSource() == cancelButton){
				setVisible(false);
			}
		}
		
	}
	
	private int getCurrentFontIndex(Font font){
		String fontName = font.getName();
		
		for (int i = 0; i < fontNames.size(); i++){
			if(fontName.toLowerCase().equals(fontNames.get(i).toLowerCase()))
				return i;
		}
		
		return 0;
	}
	
	private int getCurrentSizeIndex(Font font){
		int size = font.getSize();
		
		for (int i = 0; i < fontSizes.size(); i++){
			if(size == fontSizes.get(i))
				return i;
		}
		
		return 0;
	}
	
	private boolean getItalic(Font font){
		return font.isItalic();
	}
	
	private boolean getBold(Font font){
		return font.isBold();
	}
}
