package writxtr.ui;

import writxtr.beans.ToolBarEvent;
import writxtr.enums.ToolBarItem;
import writxtr.listeners.ToolBarListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;


public class WritxtrToolBar extends ToolBar implements EventHandler<ActionEvent>
{

	private static final long serialVersionUID = -8630077100444271207L;

	// Listeners
	private ToolBarListener toolBarListener;
	
	private Button newButton;
    private Button openButton;
	private Button saveButton;
	private Button saveAsButton;
	
	public WritxtrToolBar(){
	}
	
	public void init(){
		newButton = new Button("New");
		newButton.setPrefSize(48, 24);
		newButton.setTooltip(new Tooltip("New file"));

        openButton = new Button("Open");
        openButton.setPrefSize(48, 24);
        openButton.setTooltip(new Tooltip("Open"));
		
		saveButton = new Button("Save");
		saveButton.setPrefSize(48, 24);
		saveButton.setTooltip(new Tooltip("Save"));
		
		saveAsButton = new Button("Save As");
		saveAsButton.setPrefSize(64, 24);
		saveAsButton.setTooltip(new Tooltip("Save as"));
		
		newButton.addEventHandler(ActionEvent.ACTION, this);
		saveButton.addEventHandler(ActionEvent.ACTION, this);
		saveAsButton.addEventHandler(ActionEvent.ACTION, this);
		openButton.addEventHandler(ActionEvent.ACTION, this);
				
		getItems().add(newButton);
        getItems().add(openButton);
        getItems().add(saveButton);
        getItems().add(saveAsButton);
	}
	
	public void setToolBarListener(ToolBarListener toolBarListener){
		this.toolBarListener = toolBarListener;
	}
	
	public void fireToolBarEvent(ToolBarEvent event){
		if(toolBarListener != null)
        {
            toolBarListener.onButtonClicked(event);
        }
	}

    @Override
    public void handle(ActionEvent event)
    {
        if (event.getSource() instanceof Button){
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
