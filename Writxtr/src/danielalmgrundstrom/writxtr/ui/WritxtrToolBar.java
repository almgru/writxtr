package danielalmgrundstrom.writxtr.ui;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import danielalmgrundstrom.writxtr.beans.ToolBarEvent;
import danielalmgrundstrom.writxtr.enums.ToolBarItem;
import danielalmgrundstrom.writxtr.listeners.ToolBarListener;


public class WritxtrToolBar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = -8630077100444271207L;

	// Listeners
	private ToolBarListener toolBarListener;
	
	private JButton newButton;
    private JButton openButton;
	private JButton saveButton;
	private JButton saveAsButton;
	
	public WritxtrToolBar(){
	}
	
	public void init(){
		newButton = new JButton(new ImageIcon(getClass().getResource("new16.png")));
		newButton.setPreferredSize(new Dimension(24, 24));
		newButton.setBorderPainted(false);
		newButton.setOpaque(false);
		newButton.setToolTipText("New file");

        openButton = new JButton(new ImageIcon(getClass().getResource("open16.png")));
        openButton.setPreferredSize(new Dimension(24, 24));
        openButton.setBorderPainted(false);
        openButton.setOpaque(false);
        openButton.setToolTipText("Open");
		
		saveButton = new JButton(new ImageIcon(getClass().getResource("save16.png")));
		saveButton.setPreferredSize(new Dimension(24, 24));
		saveButton.setBorderPainted(false);
		saveButton.setOpaque(false);
		saveButton.setToolTipText("Save");
		
		saveAsButton = new JButton(new ImageIcon(getClass().getResource("saveAs16.png")));
		saveAsButton.setPreferredSize(new Dimension(24, 24));
		saveAsButton.setBorderPainted(false);
		saveAsButton.setOpaque(false);
		saveAsButton.setToolTipText("Save as");
		
		newButton.addActionListener(this);
		saveButton.addActionListener(this);
		saveAsButton.addActionListener(this);
		openButton.addActionListener(this);
				
		add(newButton);
        add(openButton);
		add(saveButton);
		add(saveAsButton);
		
		setFloatable(false);
	}
	
	public void setToolBarListener(ToolBarListener toolBarListener){
		this.toolBarListener = toolBarListener;
	}
	
	public void fireToolBarEvent(ToolBarEvent event){
		if(toolBarListener != null)
			toolBarListener.onButtonClicked(event);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() instanceof JButton){
			if(event.getSource() == newButton)
				fireToolBarEvent(new ToolBarEvent(ToolBarItem.New));
			
			else if(event.getSource() == saveButton)
				fireToolBarEvent(new ToolBarEvent(ToolBarItem.Save));
			
			else if(event.getSource() == saveAsButton)
				fireToolBarEvent(new ToolBarEvent(ToolBarItem.SaveAs));
			
			else if(event.getSource() == openButton)
				fireToolBarEvent(new ToolBarEvent(ToolBarItem.OpenFile));
			
		}
	}
}
