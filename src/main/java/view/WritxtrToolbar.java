package writxtr.view;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import writxtr.beans.ToolbarEvent;
import writxtr.enums.ToolbarItem;
import writxtr.listeners.ToolbarObserver;

class WritxtrToolbar extends JToolBar implements Toolbar, ActionListener {

	private static final long serialVersionUID = -8630077100444271207L;

	// Listeners
	private ToolbarObserver toolbarObserver;
	
	private JButton newButton;
    private JButton openButton;
	private JButton saveButton;
	private JButton saveAsButton;
	
	WritxtrToolbar() { }
	
    @Override
	void init(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

		newButton = new JButton(new ImageIcon(loader.getResource("new16.png")));
		newButton.setPreferredSize(new Dimension(24, 24));
		newButton.setBorderPainted(false);
		newButton.setOpaque(false);
		newButton.setToolTipText("New file");

        openButton = new JButton(new ImageIcon(loader.getResource("open16.png")));
        openButton.setPreferredSize(new Dimension(24, 24));
        openButton.setBorderPainted(false);
        openButton.setOpaque(false);
        openButton.setToolTipText("Open");
		
		saveButton = new JButton(new ImageIcon(loader.getResource("save16.png")));
		saveButton.setPreferredSize(new Dimension(24, 24));
		saveButton.setBorderPainted(false);
		saveButton.setOpaque(false);
		saveButton.setToolTipText("Save");
		
		saveAsButton = new JButton(new ImageIcon(loader.getResource("saveAs16.png")));
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
	
    @Override
	public void setToolbarObserver(ToolbarObserver observer) {
		this.toolbarObserver = observer;
	}
	
	@Override
	void actionPerformed(ActionEvent event) {
		
		if (event.getSource() instanceof JButton) {
			if(event.getSource() == newButton) {
				dispatchToolbarEvent(new ToolBarEvent(ToolBarItem.New));
            }
			else if(event.getSource() == saveButton) {
				dispatchToolbarEvent(new ToolBarEvent(ToolBarItem.Save));
            }
			else if(event.getSource() == saveAsButton) {
				dispatchToolbarEvent(new ToolBarEvent(ToolBarItem.SaveAs));
            }
			else if(event.getSource() == openButton) {
				dispatchToolbarEvent(new ToolBarEvent(ToolBarItem.OpenFile));
            }
		}
	}

	private void dispatchToolbarEvent(ToolbarEvent event) {
		if(toolbarObserver != null)
			toolbarObserver.onButtonClicked(event);
	}
}

